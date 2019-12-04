# 通用Mapper处理
### 1、引入pom包
```xml
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.0.1</version>
</dependency>
```

### 2、新建core.universal包
- 创建通用Mapper接口
- 创建通用Service接口
- 创建抽象实现类AbstractService

### 3、修改domain.User实体类
```java
@Table(name = "user_info")
public class User implements Serializable {
    @Id <- 指定主键字段
    private String id;
    @Column(name = "user_name") <- 指定数据库字段名
    private String username;
    @Transient <- 数据库没有此字段
    private String password;

    ...
}
```

### 4、修改dao.IUserDao `mapper`类
```java
public interface IUserDao extends Mapper<User> {
}
```

### 5、修改service.IUserService `serivce`类
```java
public interface IUserService extends Service<User> {
}
```

### 6、修改实现类service.impl.UserServiceImpl
```java
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
}
```

### 7、测试
```java
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
```