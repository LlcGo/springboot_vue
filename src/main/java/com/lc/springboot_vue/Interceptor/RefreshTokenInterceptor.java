package com.lc.springboot_vue.Interceptor;

import cn.hutool.json.JSONUtil;
import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.Common.UserHolder;
import com.lc.springboot_vue.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Lc
 * @Date 2023/3/30
 * @Description
 */
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private JwtUtil jwtUtil;

    public RefreshTokenInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户存入ThreadLocal
        //获取token
//        String Clitoken = request.getHeader("X-Token");
//        log.info("token:{}",token);
//        String cacheToken = LOGIN_USER_KEY + Clitoken;
//        Map<Object, Object> usermap = stringRedisTemplate.opsForHash().entries(cacheToken);
//        log.info("usermap:{}",usermap);
        //如果为空直接放行
//        if(usermap.isEmpty()){
//            return true;
//        }
//        UserDTO userDTO = BeanUtil.fillBeanWithMap(usermap, new UserDTO(), false);
//        log.info("userDTO:{}",userDTO);
        //存入ThreadLocal
//        UserHolder.saveUser(userDTO);
        //刷新信息
//        stringRedisTemplate.expire(cacheToken,30l, TimeUnit.MINUTES);

        String token = request.getHeader("X-Token");
        log.info("token = {}",token);
        if(token != null){
            jwtUtil.parseToken(token);
            return true;
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSONUtil.toJsonStr(Restult.fail("jwt无效请重新登录")));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
