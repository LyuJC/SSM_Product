package com.juncheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.juncheng.dao.ISysLogDao;
import com.juncheng.domain.SysLog;
import com.juncheng.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {
    private ISysLogDao sysLogDao;

    @Autowired
    public SysLogServiceImpl(ISysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }
}
