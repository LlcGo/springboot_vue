package com.lc.springboot_vue;

import com.lc.springboot_vue.pojo.User;
import com.lc.springboot_vue.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author Lc
 * @Date 2023/4/2
 * @Description
 */
@SpringBootTest
public class TetsJWT {
    @Resource
    private JwtUtil jwtUtil;

    @Test
    public void testCreat(){
        User user = new User();
        user.setUsername("zhansgan");
        user.setPhone("12345655555");
        String token = jwtUtil.create(user);
        System.out.println(token);
    }

    @Test
    public void testToObj(){
        User user = jwtUtil.parseToken("eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxYjliMGM5ZS02Y2E1LTRjMjktOTRkZC00YTYzY2E2NmU2MTMiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzNDU2NTU1NTVcIixcInVzZXJuYW1lXCI6XCJ6aGFuc2dhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY4MDQzMjU2MCwiZXhwIjoxNjgwNDM0MzYwfQ.x7ysh86qf8KlU9dCU5NqtWc1AGLLTC_gAjVXMUMg-au_J2JL50jLCxf6L1uZxNCQjvJbV4U85r72T5BZXi-STg", User.class);
        System.out.println(user);
    }

}
