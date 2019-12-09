package top.xuxuzhaozhao.demo.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import top.xuxuzhaozhao.demo.core.ret.RetResponse;
import top.xuxuzhaozhao.demo.core.ret.ServiceException;
import top.xuxuzhaozhao.demo.domain.User;
import top.xuxuzhaozhao.demo.service.IUserService;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"用户操作接口"}, description = "描述")
@RestController
public class UserController {
    @Resource
    private IUserService userService;

@Autowired
StringRedisTemplate redisTemplate;

@ApiOperation(value = "查询用户", notes = "根据用户ID查询用户")
@GetMapping("/api/user")
public Object selectById(@RequestParam String id) {
    User info = userService.selectById(id);

    ValueOperations ops = redisTemplate.opsForValue();
    ops.set("名字",info.getUsername());
    return RetResponse.makeOKRsp(info);
}

    @PostMapping("/api/exception")
    public void testException(@RequestBody User user) {
        List<Integer> x = null;
        x.get(9);
    }

    @ApiOperation(value = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码"),
            @ApiImplicitParam(name = "size", value = "每页显示数量")
    })
    @PostMapping("/api/selectAll")
    public Object selectAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page,size);
        List<User> users = userService.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);

        return RetResponse.makeOKRsp(pageInfo);
    }
}
