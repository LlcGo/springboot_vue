package com.lc.springboot_vue;

import com.lc.springboot_vue.pojo.User;
import com.lc.springboot_vue.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootVueApplicationTests {

    @Resource
    UserService userService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
        List<User> list = userService.list();
        System.out.println(list);
    }

    @Test
    void test1(){
        stringRedisTemplate.opsForValue().set("id","123",1, TimeUnit.MINUTES);
        String id = stringRedisTemplate.opsForValue().get("id");
        System.out.println(id);
    }

    @Test
    void test2(){
        String a = "http://localhost:8081/ ";
        String replace = a.replace("8081", "80");
        System.out.println(replace);
    }

}
