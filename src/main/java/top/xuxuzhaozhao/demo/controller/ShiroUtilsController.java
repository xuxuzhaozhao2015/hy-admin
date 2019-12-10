package top.xuxuzhaozhao.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xuxuzhaozhao.demo.domain.User;
import top.xuxuzhaozhao.demo.service.ShiroService;

@RestController
@RequestMapping("shiroUtils")
public class ShiroUtilsController {

    @Autowired
    private ShiroService shiroService;

    @GetMapping("/noLogin")
    public void noLogin() {
        throw new UnauthenticatedException();
    }

    @GetMapping("/noAuthorize")
    public void noAuthorize() {
        throw new UnauthorizedException();
    }


    @PostMapping("/getNowUser")
    public User getNowUser() {
        User u = (User) SecurityUtils.getSubject().getPrincipal();
        return u;
    }

    @PostMapping("/updatePermission")
    public void updatePermission() throws Exception{
        shiroService.updatePermission();
    }
}
