# Redis的使用
### 1、导入pom包
```xml
<!-- 添加redis依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
    <version>2.2.1.RELEASE</version>
</dependency>
<!-- 需引入commons-pool2 -->
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
    <version>2.7.0</version>
</dependency>
```

### 2、配置redis服务器信息
```yaml
spring:
    redis:
        host: 192.168.160.128
        port: 6379
        timeout: 500
        password: 123456
        lettuce:
          shutdown-timeout: 100ms
          pool:
            min-idle : 5
            max-idle : 10
            max-active : 8
            max-wait : 1ms
```

### 3、直接使用
```java
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
```

### 注意事项：
1、Redis command timed out; nested exception is io.lettuce.core.RedisCommandTimeoutException: Command timed out after no timeout
```java
spring.redis.timeout不要设置为0
```
2、使用StringRedisTemplate代替RedisTemplate，否则key值会出现乱码
```http request
https://my.oschina.net/javamaster/blog/2239462
```
3、StringRedisTemplate使用样例
```http request
https://www.cnblogs.com/slowcity/p/9002660.html
```