package top.xuxuzhaozhao.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void StringRedisTempTest(){
        redisTemplate.opsForValue().set("徐程意","赵燕");
        String 徐程意 = redisTemplate.opsForValue().get("徐程意");
        System.out.println(徐程意);
    }

}
