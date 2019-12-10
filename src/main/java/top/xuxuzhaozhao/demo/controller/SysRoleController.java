package top.xuxuzhaozhao.demo.controller;

import top.xuxuzhaozhao.demo.core.ret.RetResult;
import top.xuxuzhaozhao.demo.core.ret.RetResponse;
import top.xuxuzhaozhao.demo.domain.SysRole;
import top.xuxuzhaozhao.demo.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: SysRoleController类
* @author xcy
* @date 2019/12/09 14:10
*/
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/insert")
    public Object insert(SysRole sysRole) throws Exception{
        Integer state = sysRoleService.insert(sysRole);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public Object deleteById(@RequestParam String id) throws Exception {
        Integer state = sysRoleService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public Object update(SysRole sysRole) throws Exception {
        Integer state = sysRoleService.update(sysRole);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public Object selectById(@RequestParam String id) throws Exception {
        SysRole sysRole = sysRoleService.selectById(id);
        return RetResponse.makeOKRsp(sysRole);
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
        List<SysRole> list = sysRoleService.selectAll();
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}