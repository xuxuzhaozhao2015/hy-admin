package top.xuxuzhaozhao.demo.service.impl;

import top.xuxuzhaozhao.demo.dao.SystemLogMapper;
import top.xuxuzhaozhao.demo.domain.SystemLog;
import top.xuxuzhaozhao.demo.service.SystemLogService;
import top.xuxuzhaozhao.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: SystemLogService接口实现类
* @author xcy
* @date 2019/12/04 15:51
*/
@Service
public class SystemLogServiceImpl extends AbstractService<SystemLog> implements SystemLogService {
    @Resource
    private SystemLogMapper systemLogMapper;
}