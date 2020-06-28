package com.juncheng.controller;


import com.fasterxml.jackson.databind.ser.std.ClassSerializer;
import com.juncheng.domain.SysLog;
import com.juncheng.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {


    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;

    private Date visitTime;//开始的时间
    private Class clazz;//访问的类
    private Method method;//调用的方法

    /**
     * 前置通知：主要是获取开始时间，执行的类是哪一个，执行的哪个方法
     * @param joinPoint
     */
    @Before("execution(* com.juncheng.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime=new Date();//当前的时间就是开始访问的时间
        clazz=joinPoint.getTarget().getClass(); //具体要访问的类
        String methodName=joinPoint.getSignature().getName(); //获取访问方法的名称


        if (clazz==SysLogController.class) return;

        //获取具体执行方法的Method对象
        Object[] args=joinPoint.getArgs();//获取访问方法的参数
        if (args==null || args.length==0){
            method=clazz.getMethod(methodName);//只能获取无参的
        }
        else{
            Class[] classeArgs=new Class[args.length];
            for(int i=0;i<args.length;i++){
                classeArgs[i]=args[i].getClass();
            }
            method=clazz.getMethod(methodName,classeArgs);
        }


    }


    /**
     * 注意：本来after是最终通知，after-returning 才是后置通知，但这里直接用after了
     */
    @After("execution(* com.juncheng.controller.*.*(..))")
    public void doAfter(){
        if (clazz==SysLogController.class) return;
        long time=new Date().getTime()-visitTime.getTime();//获取访问的时长

        //获取URL,通过反射
        String url="";
        if (clazz!=null&& method!=null && clazz !=LogAop.class){

                //获取类上的@RequestMapping("/user")
                //查看RequestMapping源码能发现，它有一个值叫values或者path，代表的URL
               RequestMapping classAnnotation= (RequestMapping) clazz.getAnnotation(RequestMapping.class);
               if(classAnnotation!=null){
                   String[] classValue=classAnnotation.value();
                   //获取方法上的@RequestMapping("/findById.do")
                   RequestMapping methodAnnotation=method.getAnnotation(RequestMapping.class);
                   if (methodAnnotation !=null){
                       String[] methodValue=methodAnnotation.value();
                       url=classValue[0]+methodValue[0];
                   }
               }


        }

        //获取访问的IP地址
        String ip=request.getRemoteAddr();

        //获取当前操作的用户
        SecurityContext context= SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
        User principal= (User) context.getAuthentication().getPrincipal();
        String username=principal.getUsername();


        //将日志封装到SysLog对象
        SysLog sysLog=new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setVisitTime(visitTime);
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());

        //调用Service完成日志操作
        sysLogService.save(sysLog);

        System.out.println("===========================================");;
        System.out.println("===========================================");;
        System.out.println("===========================================");;
        System.out.println(request.getRequestURL()+"HHH"+request.getRequestURI());
        System.out.println("===========================================");;
        System.out.println("===========================================");;
        System.out.println("===========================================");;

    }
}
