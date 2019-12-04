#  PageHelper整合

#### 1、添加pom包
```xml
<!-- mybatis分页查询 -->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.13</version>
</dependency>
```
#### 2、编写查询所有的sql语句
在mapper->UserInfoMapper.xml中加入查询语句
```sql
<select id="selectAll" resultMap="BaseResultMap">
    select <include refid="BaseColumnList"/> from user_info
</select>
```
#### 3、dao中编写接口
```java
List<User> selectAll();
```
#### 4、service中编写接口与实现类
```java
/**
 * 分页查询
 */
@Override
public PageInfo<User> selectAll(Integer page, Integer size) {
    // 紧跟PageHelper的第二行语句才具备分页功能
    PageHelper.startPage(page,size);
    List<User> userList = userDao.selectAll();
    return new PageInfo<>(userList);
}
```
#### 5、controller中实现
```java
@ApiOperation(value = "查询所有用户")
@ApiImplicitParams({
        @ApiImplicitParam(name = "page",value = "当前页码"),
        @ApiImplicitParam(name = "size",value = "每页显示条数")
})
@GetMapping("/api/selectAll")
public Object selectAll(@RequestParam(defaultValue = "0")Integer page,@RequestParam(defaultValue = "1")Integer size){
    PageInfo<User> pageInfo = userService.selectAll(page,size);
    return RetResponse.makeOKRsp(pageInfo);
}
```