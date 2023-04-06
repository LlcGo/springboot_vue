package com.lc.springboot_vue.config;

import com.lc.springboot_vue.Interceptor.RefreshTokenInterceptor;
import com.lc.springboot_vue.utils.JacksonObjectMapper;
import com.lc.springboot_vue.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author Lc
 * @Date 2023/3/6
 * @Description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtUtil jwtUtil;

 //   @Override
 //   public void addResourceHandlers(ResourceHandlerRegistry registry) {
 //       registry.addResourceHandler("/").addResourceLocations("classpath:/static/");
 //   }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RefreshTokenInterceptor(jwtUtil))
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/user/login",
//                        "/user/info",
//                        "/user/logout",
//                        "/**.png",
//                        "/error",
//                        "/swagger-ui/**",
//                        "/swagger-resources/**",
//                        "/v3/**"
//                )
//                .order(0);
////        registry.addInterceptor(new LoginInterceptor())
////                .excludePathPatterns(
////                        "/user/login",
////                        "/user/info"
////                ).addPathPatterns("/**").order(1);
//    }
    /**
     * 对象转换器json与java序列化 防止精度丢失
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0,mappingJackson2HttpMessageConverter);
    }
}
