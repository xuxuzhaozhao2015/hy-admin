package top.xuxuzhaozhao.demo.service.impl;

import top.xuxuzhaozhao.demo.dao.db1.SysPermMapper;
import top.xuxuzhaozhao.demo.domain.SysPerm;
import top.xuxuzhaozhao.demo.service.SysPermService;
import top.xuxuzhaozhao.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: SysPermService接口实现类
* @author xcy
* @date 2019/12/09 14:10
*/
@Service
public class SysPermServiceImpl extends AbstractService<SysPerm> implements SysPermService {
    @Resource
    private SysPermMapper sysPermMapper;
}