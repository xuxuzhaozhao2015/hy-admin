package top.xuxuzhaozhao.demo.service.impl;

import top.xuxuzhaozhao.demo.dao.db1.RolePermMapper;
import top.xuxuzhaozhao.demo.domain.RolePerm;
import top.xuxuzhaozhao.demo.service.RolePermService;
import top.xuxuzhaozhao.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: RolePermService接口实现类
* @author xcy
* @date 2019/12/09 14:10
*/
@Service
public class RolePermServiceImpl extends AbstractService<RolePerm> implements RolePermService {
    @Resource
    private RolePermMapper rolePermMapper;

    @Override
    public List<String> getPermsByUserId(String userId) {
        return rolePermMapper.getPermsByUserId(userId);
    }
}