package top.xuxuzhaozhao.demo.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidMonitorConfig {

    @Bean
    public ServletRegistrationBean registrationBean(){
        ServletRegistrationBean rBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        rBean.addInitParameter("allow","127.0.0.1");
        // deny的优先级是高于allow的
        rBean.addInitParameter("deny","192.168.2.4");
        rBean.addInitParameter("loginUsername","root");
        rBean.addInitParameter("loginPassword","1234");
        // 能否重置数据
        rBean.addInitParameter("resetEnable","false");
        return rBean;
    }

    @Bean
    public FilterRegistrationBean duridStatFilter(){
        FilterRegistrationBean fBean = new FilterRegistrationBean<>(new WebStatFilter());
        fBean.addUrlPatterns("/*");
        fBean.addInitParameter("exclusions","*.js,*.css,*.jpg,*.ico,/druid/*");
        return fBean;
    }
}
