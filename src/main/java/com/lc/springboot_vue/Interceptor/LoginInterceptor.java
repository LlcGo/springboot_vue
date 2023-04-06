package com.lc.springboot_vue.Interceptor;

import com.lc.springboot_vue.Common.UserHolder;
import com.lc.springboot_vue.Dto.UserDTO;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Lc
 * @Date 2023/3/30
 * @Description
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO user = UserHolder.getUser();
        if(user == null){
            return false;
        }
        return true;
    }
}
