package com.juncheng.service;

import com.juncheng.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    public void save(SysLog sysLog);

    public List<SysLog> findAll(Integer page,Integer size);
}
