package top.xuxuzhaozhao.demo.core.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor1 implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(Interceptor1.class);

    /**
     * controller调用之前会执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(">>>>>【MyInterceptor1】>>>>>> 【preHandle】");
        // 只有返回true才会继续往下执行，返回false就是取消当前的请求；
        return true;
    }

    /**
     * controller调用之后执行，视图渲染之前执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info(">>>>>【MyInterceptor1】>>>>>> 【postHandle】");
    }

    /**
     * DispatchServlet渲染完视图之后执行，主要用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info(">>>>>【MyInterceptor1】>>>>>> 【afterCompletion】");
    }
}
