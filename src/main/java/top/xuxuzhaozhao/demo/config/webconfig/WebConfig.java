package top.xuxuzhaozhao.demo.config.webconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import top.xuxuzhaozhao.demo.core.interceptor.Interceptor1;
import top.xuxuzhaozhao.demo.core.ret.RetCode;
import top.xuxuzhaozhao.demo.core.ret.RetResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
    private static final String IZATION = "CHUCHEN";

    /**
     * 重写json序列化
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(Objects.requireNonNull(getSupportedMediaTypes()));
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                // String null -> ""
                SerializerFeature.WriteNullStringAsEmpty,
                // Number null -> 0
                SerializerFeature.WriteNullNumberAsZero,
                //禁止循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(converter);
        logger.info("Json消息转换完成！");
    }

    private List<MediaType> getSupportedMediaTypes() {
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.ALL);
        return mediaTypes;
    }

    /**
     * 继承WebMvcConfigurationSupport之后，静态文件映射会出现问题，需要重新指定静态资源
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/META-INF/resources/favicon.ico");
        super.addResourceHandlers(registry);
    }

    /**
     * 自定义拦截器：权限控制
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 这里可以填写自定义的拦截器
        registry.addInterceptor(new Interceptor1() {
            /**
             * This implementation always returns {@code true}.
             *
             */
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String ization = request.getHeader("ization");
                if (IZATION.equals(ization)) return true;

                RetResult result = RetResult.build().setCode(RetCode.UNAUTHORIZED).setMsg("签名认证失败");
                responseResult(response, result);
                return false;
            }
            // /** 表示所有请求都必须加
        }).addPathPatterns("/api/selectAll");;
    }

    // 独立出来使用GlobalExceptionResolver处理
//    /**
//     * 重写异常处理逻辑
//     */
//    @Override
//    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        exceptionResolvers.add(getHandlerExceptionResolver());
//    }
//
//    private HandlerExceptionResolver getHandlerExceptionResolver() {
//        return new HandlerExceptionResolver() {
//            @Override
//            public ModelAndView resolveException(HttpServletRequest httpServletRequest,
//                                                 HttpServletResponse httpServletResponse,
//                                                 Object handler, Exception e) {
//                RetResult<Object> result = getResultByHandleException(httpServletRequest, handler, e);
//                responseResult(httpServletResponse, result);
//                return new ModelAndView();
//            }
//        };
//    }
//
//    /**
//     * 根据异常类型确定返回数据
//     */
//    private RetResult<Object> getResultByHandleException(HttpServletRequest request, Object handler, Exception e) {
//        RetResult<Object> result = new RetResult<>();
//        if (e instanceof ServiceException) {
//            result.setCode(RetCode.FAIL).setMsg(e.getMessage()).setData(null);
//            return result;
//        }
//        if (e instanceof NoHandlerFoundException) {
//            result.setCode(RetCode.NOTFOUND).setMsg("接口 [" + request.getRequestURI() + "] 不存在");
//            return result;
//        }
//        result.setCode(RetCode.INTERNAL_SERVER_ERROR).setMsg("接口 [" + request.getRequestURI() + "] 内部错误:" + e.getMessage() + "，请联系管理员");
//        String message;
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
//                    handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(), e.getMessage());
//        } else {
//            message = e.getMessage();
//        }
//        //LOGGER.error(message, e);
//        return result;
//    }
//

    /**
     * @Description: 响应结果
     */
    private void responseResult(HttpServletResponse response, RetResult result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
