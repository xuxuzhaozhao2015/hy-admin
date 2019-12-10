package top.xuxuzhaozhao.demo.controller;

import top.xuxuzhaozhao.demo.core.ret.RetResult;
import top.xuxuzhaozhao.demo.core.ret.RetResponse;
import top.xuxuzhaozhao.demo.domain.SysPermissionInit;
import top.xuxuzhaozhao.demo.service.SysPermissionInitService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: SysPermissionInitController类
* @author xcy
* @date 2019/12/09 16:28
*/
@RestController
@RequestMapping("/sysPermissionInit")
public class SysPermissionInitController {

    @Resource
    private SysPermissionInitService sysPermissionInitService;

    @PostMapping("/insert")
    public Object insert(SysPermissionInit sysPermissionInit) throws Exception{
        Integer state = sysPermissionInitService.insert(sysPermissionInit);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public Object deleteById(@RequestParam String id) throws Exception {
        Integer state = sysPermissionInitService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public Object update(SysPermissionInit sysPermissionInit) throws Exception {
        Integer state = sysPermissionInitService.update(sysPermissionInit);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public Object selectById(@RequestParam String id) throws Exception {
        SysPermissionInit sysPermissionInit = sysPermissionInitService.selectById(id);
        return RetResponse.makeOKRsp(sysPermissionInit);
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
        List<SysPermissionInit> list = sysPermissionInitService.selectAll();
        PageInfo<SysPermissionInit> pageInfo = new PageInfo<SysPermissionInit>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}