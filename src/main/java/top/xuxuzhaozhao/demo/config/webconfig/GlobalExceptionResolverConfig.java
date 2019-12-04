package top.xuxuzhaozhao.demo.config.webconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import top.xuxuzhaozhao.demo.core.ret.RetCode;
import top.xuxuzhaozhao.demo.core.ret.RetResult;
import top.xuxuzhaozhao.demo.core.ret.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionResolverConfig {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionResolverConfig.class);

    @ExceptionHandler(ServiceException.class)
    public void serviceExceptionHandler(HttpServletResponse resp, ServiceException e) {
        RetResult result = RetResult.build().setCode(RetCode.FAIL).setMsg(e.getMessage()).setData(null);
        responseResult(resp,result);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public void notFoundExceptionHandler(HttpServletRequest request, HttpServletResponse resp, NoHandlerFoundException e) {
        RetResult result = RetResult.build().setCode(RetCode.NOTFOUND).setMsg("接口 [" + request.getRequestURI() + "] 不存在");
        responseResult(resp,result);
    }

    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse resp, Exception e) {
        RetResult result = RetResult.build().setCode(RetCode.INTERNAL_SERVER_ERROR).setMsg("接口 [" + request.getRequestURI() + "] 内部错误:" + e.getMessage() + "，请联系管理员");
        responseResult(resp,result);
    }

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
