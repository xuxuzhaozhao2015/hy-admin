package top.xuxuzhaozhao.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import top.xuxuzhaozhao.demo.core.ret.ServiceException;
import top.xuxuzhaozhao.demo.core.universal.AbstractService;
import top.xuxuzhaozhao.demo.dao.IUserDao;
import top.xuxuzhaozhao.demo.domain.User;
import top.xuxuzhaozhao.demo.service.IUserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends AbstractService<User> implements IUserService {
    @Resource
    private IUserDao userDao;

    @Override
    public User selectById(String id) {
        User user = userDao.selectByPrimaryKey(id);
        if(user==null){
            throw new ServiceException("暂无该用户");
        }
        return user;
    }

    //    @Override
//    public User selectById(int id) {
//        return userDao.selectById(id);
//    }
//
//    @Override
//    public PageInfo<User> selectAll(Integer page, Integer size) {
//
//        //startPage只会影响第一条select语句
//        PageHelper.startPage(page,size);
//        List<User> users = userDao.selectAll();
//        return new PageInfo<>(users);
//    }
}
