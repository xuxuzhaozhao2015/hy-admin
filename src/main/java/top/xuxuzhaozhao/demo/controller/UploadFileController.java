package top.xuxuzhaozhao.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.xuxuzhaozhao.demo.core.ret.RetResponse;
import top.xuxuzhaozhao.demo.core.util.UploadActionUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/uploadFile")
public class UploadFileController {

    @PostMapping("upload")
    public Object upload(HttpServletRequest request, @RequestParam String orderId) throws Exception {
        System.out.println(orderId);
        return RetResponse.makeOKRsp(UploadActionUtil.uploadFile(request));
    }
}
