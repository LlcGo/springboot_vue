package com.lc.springboot_vue.service;

import com.lc.springboot_vue.pojo.TbFile;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface TbFileService extends IService<TbFile> {

    /**
     * 将文件保存至服务器
     * @param file 文件
     * @return 存入后的地址 url
     */
    String savefile(MultipartFile file);

    /**
     * 将文件已流的方式下载
     * @param name 文件名
     * @param response 返回用的resp
     */
    void download(String name, HttpServletResponse response);
}
