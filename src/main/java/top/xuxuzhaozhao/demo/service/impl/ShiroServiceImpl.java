package top.xuxuzhaozhao.demo.service.impl;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.xuxuzhaozhao.demo.domain.SysPermissionInit;
import top.xuxuzhaozhao.demo.service.ShiroService;
import top.xuxuzhaozhao.demo.service.SysPermissionInitService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    SysPermissionInitService sysPermissionInitService;

    @Override
    public Map<String, String> loadFilterChainDefinition() {
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        List<SysPermissionInit> list = sysPermissionInitService.selectAll();
        for (SysPermissionInit sysPermissionInit : list) {
            filterChainDefinitionMap.put(sysPermissionInit.getUrl(), sysPermissionInit.getPermissionInit());
        }
        return filterChainDefinitionMap;
    }

    /**
     * 动态修改权限
     */
    @Override
    public void updatePermission() {
        synchronized (shiroFilterFactoryBean){
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                        .getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                    .getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinition());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            System.out.println("更新权限成功！！");
        }
    }
}
