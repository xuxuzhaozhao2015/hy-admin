package top.xuxuzhaozhao.demo.service.impl;

import top.xuxuzhaozhao.demo.dao.db1.SysPermissionInitMapper;
import top.xuxuzhaozhao.demo.domain.SysPermissionInit;
import top.xuxuzhaozhao.demo.service.SysPermissionInitService;
import top.xuxuzhaozhao.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: SysPermissionInitService接口实现类
* @author xcy
* @date 2019/12/09 16:28
*/
@Service
public class SysPermissionInitServiceImpl extends AbstractService<SysPermissionInit> implements SysPermissionInitService {
    @Resource
    private SysPermissionInitMapper sysPermissionInitMapper;
}