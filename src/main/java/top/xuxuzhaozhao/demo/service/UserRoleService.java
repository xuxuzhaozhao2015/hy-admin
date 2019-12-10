package top.xuxuzhaozhao.demo.service;

import top.xuxuzhaozhao.demo.domain.UserRole;
import top.xuxuzhaozhao.demo.core.universal.Service;

import java.util.List;

/**
* @Description: UserRoleService接口
* @author xcy
* @date 2019/12/09 14:10
*/
public interface UserRoleService extends Service<UserRole> {
    List<String> getRolesByUserId(String userId);
}