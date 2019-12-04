package top.xuxuzhaozhao.demo.controller;

import top.xuxuzhaozhao.demo.core.ret.RetResult;
import top.xuxuzhaozhao.demo.core.ret.RetResponse;
import top.xuxuzhaozhao.demo.domain.RoleInfo;
import top.xuxuzhaozhao.demo.service.RoleInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: RoleInfoController类
* @author xcy
* @date 2019/12/04 16:36
*/
@RestController
@RequestMapping("/roleInfo")
public class RoleInfoController {

    @Resource
    private RoleInfoService roleInfoService;

    @PostMapping("/insert")
    public Object insert(RoleInfo roleInfo) throws Exception{
        Integer state = roleInfoService.insert(roleInfo);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public Object deleteById(@RequestParam String id) throws Exception {
        Integer state = roleInfoService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public Object update(RoleInfo roleInfo) throws Exception {
        Integer state = roleInfoService.update(roleInfo);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public Object selectById(@RequestParam String id) throws Exception {
        RoleInfo roleInfo = roleInfoService.selectById(id);
        return RetResponse.makeOKRsp(roleInfo);
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
        List<RoleInfo> list = roleInfoService.selectAll();
        PageInfo<RoleInfo> pageInfo = new PageInfo<RoleInfo>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}