package top.xuxuzhaozhao.demo.dao.db1;

import top.xuxuzhaozhao.demo.core.universal.Mapper;
import top.xuxuzhaozhao.demo.domain.UserRole;

import java.util.List;

public interface UserRoleMapper extends Mapper<UserRole> {
    List<String> getRolesByUserId(String userId);
}