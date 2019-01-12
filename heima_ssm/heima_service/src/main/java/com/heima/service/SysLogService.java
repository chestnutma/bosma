package com.heima.service;

import com.heima.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {
    void save(SysLog sysLog);

    List<SysLog> findAll();
}
