package com.lc.springboot_vue.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.TbFile;
import com.lc.springboot_vue.service.TbFileService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {


    @Resource
    private TbFileService tbFileService;

    //上传图片到服务器
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file){
        return tbFileService.savefile(file);
    }


    //下载图片
    @GetMapping("/download/{name}")
    public void download(@PathVariable String name, HttpServletResponse response){
           tbFileService.download(name,response);
    }


}



