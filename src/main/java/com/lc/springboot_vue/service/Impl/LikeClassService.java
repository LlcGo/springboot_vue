package com.lc.springboot_vue.service.Impl;

import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.LikeClass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 *
 */
public interface LikeClassService extends IService<LikeClass> {

    Restult<HashMap<String, Object>> getlist(Integer pageNo, Integer pageSize, String username, String className);
}
