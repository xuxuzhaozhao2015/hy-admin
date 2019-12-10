package top.xuxuzhaozhao.demo.core.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.xuxuzhaozhao.demo.domain.SysPermissionInit;
import top.xuxuzhaozhao.demo.service.SysPermissionInitService;

import java.util.List;

@Configuration
public class ShiroConfigure {

    @Autowired
    private SysPermissionInitService sysPermissionInitService;

    /**
     * 注入自定义的realm，告诉shiro如何获取用户信息来做登录或权限控制
     */
    @Bean
    public Realm realm(){
        return new CustomRealm();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        creator.setUsePrefix(true);
        return creator;
    }

    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        List<SysPermissionInit> sysPermissionInits = sysPermissionInitService.selectAll();
        for (SysPermissionInit sysp:sysPermissionInits){
            chain.addPathDefinition(sysp.getUrl(),sysp.getPermissionInit());
        }
//        chain.addPathDefinition( "/api/selectById", "authc, roles[cw]");
//        chain.addPathDefinition( "/logout", "anon");
//        chain.addPathDefinition( "/api/selectAll", "anon");
//        chain.addPathDefinition( "/api/login", "anon");
//        chain.addPathDefinition( "/**", "authc");
        return chain;
    }
}
