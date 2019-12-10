package top.xuxuzhaozhao.demo.service.impl;

import top.xuxuzhaozhao.demo.dao.db1.SysRoleMapper;
import top.xuxuzhaozhao.demo.domain.SysRole;
import top.xuxuzhaozhao.demo.service.SysRoleService;
import top.xuxuzhaozhao.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: SysRoleService接口实现类
* @author xcy
* @date 2019/12/09 14:10
*/
@Service
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;
}