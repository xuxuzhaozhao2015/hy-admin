package top.xuxuzhaozhao.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.xuxuzhaozhao.demo.dao.db2.RoleInfoMapper;
import top.xuxuzhaozhao.demo.domain.RoleInfo;
import top.xuxuzhaozhao.demo.service.RoleInfoService;
import top.xuxuzhaozhao.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @Description: RoleInfoService接口实现类
* @author xcy
* @date 2019/12/04 16:36
*/
@Service
public class RoleInfoServiceImpl extends AbstractService<RoleInfo> implements RoleInfoService {
    @Resource
    private RoleInfoMapper roleInfoMapper;
}