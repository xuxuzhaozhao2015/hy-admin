package top.xuxuzhaozhao.demo.service;

import java.util.Map;

public interface ShiroService {
    Map<String,String> loadFilterChainDefinition();

    /**
     * 动态修改权限
     */
    void updatePermission();
}
