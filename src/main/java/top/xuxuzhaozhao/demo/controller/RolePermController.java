package top.xuxuzhaozhao.demo.controller;

import top.xuxuzhaozhao.demo.core.ret.RetResult;
import top.xuxuzhaozhao.demo.core.ret.RetResponse;
import top.xuxuzhaozhao.demo.domain.RolePerm;
import top.xuxuzhaozhao.demo.service.RolePermService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: RolePermController类
* @author xcy
* @date 2019/12/09 14:10
*/
@RestController
@RequestMapping("/rolePerm")
public class RolePermController {

    @Resource
    private RolePermService rolePermService;

    @PostMapping("/insert")
    public Object insert(RolePerm rolePerm) throws Exception{
        Integer state = rolePermService.insert(rolePerm);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public Object deleteById(@RequestParam String id) throws Exception {
        Integer state = rolePermService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public Object update(RolePerm rolePerm) throws Exception {
        Integer state = rolePermService.update(rolePerm);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public Object selectById(@RequestParam String id) throws Exception {
        RolePerm rolePerm = rolePermService.selectById(id);
        return RetResponse.makeOKRsp(rolePerm);
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
        List<RolePerm> list = rolePermService.selectAll();
        PageInfo<RolePerm> pageInfo = new PageInfo<RolePerm>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}