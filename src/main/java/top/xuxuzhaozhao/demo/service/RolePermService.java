package top.xuxuzhaozhao.demo.service;

import top.xuxuzhaozhao.demo.domain.RolePerm;
import top.xuxuzhaozhao.demo.core.universal.Service;

import java.util.List;

/**
* @Description: RolePermService接口
* @author xcy
* @date 2019/12/09 14:10
*/
public interface RolePermService extends Service<RolePerm> {
    List<String> getPermsByUserId(String userId);
}