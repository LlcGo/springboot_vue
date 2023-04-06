package com.lc.springboot_vue.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.LikeClass;
import com.lc.springboot_vue.service.Impl.LikeClassService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author Lc
 * @Date 2023/4/6
 * @Description
 */
@RestController
@RequestMapping("/likeclass")
public class ClassController {
    @Resource
    private LikeClassService likeClassService;

    //获取分页用的数据
    @GetMapping("/list")
    public Restult<HashMap<String,Object>> list(@RequestParam("pageNo") Integer pageNo,
                                                @RequestParam("pageSize") Integer pageSize,
                                                @RequestParam(value = "userName",required = false) String username,
                                                @RequestParam(value = "className",required = false) String className
                                                ){
        return likeClassService.getlist(pageNo,pageSize,username,className);
    }


    //删除用户
    @DeleteMapping("/delete")
    public Restult<String> delete(@RequestParam Long id){
        LambdaQueryWrapper<LikeClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LikeClass::getUserId,id);
        likeClassService.remove(queryWrapper);
        return Restult.success("删除成功");
    }
}
