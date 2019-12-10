package top.xuxuzhaozhao.demo.controller;

import top.xuxuzhaozhao.demo.core.ret.RetResult;
import top.xuxuzhaozhao.demo.core.ret.RetResponse;
import top.xuxuzhaozhao.demo.domain.UserRole;
import top.xuxuzhaozhao.demo.service.UserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: UserRoleController类
* @author xcy
* @date 2019/12/09 14:10
*/
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/insert")
    public Object insert(UserRole userRole) throws Exception{
        Integer state = userRoleService.insert(userRole);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public Object deleteById(@RequestParam String id) throws Exception {
        Integer state = userRoleService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public Object update(UserRole userRole) throws Exception {
        Integer state = userRoleService.update(userRole);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public Object selectById(@RequestParam String id) throws Exception {
        UserRole userRole = userRoleService.selectById(id);
        return RetResponse.makeOKRsp(userRole);
    }

    /**
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult
    */
    @PostMapping("/list")
    public Object list(@RequestParam(defaultValue = "0") Integer page,
    @RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<UserRole> list = userRoleService.selectAll();
        PageInfo<UserRole> pageInfo = new PageInfo<UserRole>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}