package com.lc.springboot_vue.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.mapper.UserMapper;
import com.lc.springboot_vue.pojo.LikeClass;
import com.lc.springboot_vue.mapper.LikeClassMapper;
import com.lc.springboot_vue.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Slf4j
@Service
public class LikeClassServiceImpl extends ServiceImpl<LikeClassMapper, LikeClass>
    implements LikeClassService{

    @Resource
    private UserMapper userMapper;

    @Override
    public Restult<HashMap<String, Object>> getlist(Integer pageNo, Integer pageSize, String username, String className) {
        //查出所有关于这个课的学生
        // limit 第几跳数据=(pageNo-1)*pageSize
        Integer page = (pageNo - 1) * pageSize;
        List<User> userList = userMapper.getLikeByClassName(className,username,page,pageSize);
        HashMap<String, Object> data = new HashMap<>();
        data.put("total",userList.size());
        data.put("rows",userList);
        return Restult.success(data);
    }
}




