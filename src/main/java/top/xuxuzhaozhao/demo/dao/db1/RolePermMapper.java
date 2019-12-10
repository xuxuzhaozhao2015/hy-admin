package top.xuxuzhaozhao.demo.dao.db1;

import top.xuxuzhaozhao.demo.core.universal.Mapper;
import top.xuxuzhaozhao.demo.domain.RolePerm;

import java.util.List;

public interface RolePermMapper extends Mapper<RolePerm> {
    List<String> getPermsByUserId(String userId);
}