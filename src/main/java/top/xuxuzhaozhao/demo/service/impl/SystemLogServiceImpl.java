package top.xuxuzhaozhao.demo.service.impl;

import top.xuxuzhaozhao.demo.dao.db1.SystemLogMapper;
import top.xuxuzhaozhao.demo.domain.SystemLog;
import top.xuxuzhaozhao.demo.service.SystemLogService;
import top.xuxuzhaozhao.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: SystemLogService接口实现类
* @author xcy
* @date 2019/12/04 16:09
*/
@Service
public class SystemLogServiceImpl extends AbstractService<SystemLog> implements SystemLogService {
    @Resource
    private SystemLogMapper systemLogMapper;
}