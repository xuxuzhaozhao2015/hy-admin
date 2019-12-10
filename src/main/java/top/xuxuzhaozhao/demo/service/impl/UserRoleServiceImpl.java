package top.xuxuzhaozhao.demo.service.impl;

import top.xuxuzhaozhao.demo.dao.db1.UserRoleMapper;
import top.xuxuzhaozhao.demo.domain.UserRole;
import top.xuxuzhaozhao.demo.service.UserRoleService;
import top.xuxuzhaozhao.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: UserRoleService接口实现类
* @author xcy
* @date 2019/12/09 14:10
*/
@Service
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> getRolesByUserId(String userId) {
        return userRoleMapper.getRolesByUserId(userId);
    }
}