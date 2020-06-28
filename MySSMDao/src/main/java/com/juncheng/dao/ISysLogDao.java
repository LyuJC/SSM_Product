package com.juncheng.dao;

import com.juncheng.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{sys.visitTime},#{sys.username},#{sys.ip},#{sys.url},#{sys.executionTime},#{sys.method})  ")
    public void save(@Param("sys") SysLog sysLog);

    @Select("select * from syslog order by id desc ")
    public List<SysLog> findAll();
}
