package com.juncheng.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转化为字符转
     * @param date
     * @param pattern 希望的格式 例子"yyyy-mm-dd hh:mm:ss"
     * @return
     */
    public static String date2String(Date date,String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    //字符串转换为日期
    public static Date String2Date(String str,String pattern) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.parse(str);
    }
}
