package com.lc.springboot_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lc.springboot_vue.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<String> getRoleById(Long userId);

    List<Integer> getMenuIdById(Long userId);

    List<User> getLikeByClassName(@Param("className") String className, @Param("userName") String userName, @Param("page") Integer page, @Param("pageSize")Integer pageSize);
}
