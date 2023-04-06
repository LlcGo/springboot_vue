package com.lc.springboot_vue.service.Impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.springboot_vue.pojo.TbFile;
import com.lc.springboot_vue.service.TbFileService;
import com.lc.springboot_vue.mapper.TbFileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@Service
public class TbFileServiceImpl extends ServiceImpl<TbFileMapper, TbFile>
    implements TbFileService{

    @Value("${image.path}")
    private String basePath;

    @Override
    public String savefile(MultipartFile file) {
        //获取文件名为了获取他的后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //文件类型
        String type = FileUtil.extName(originalFilename);
        //文件大小
        long size = file.getSize();

        //文件名是全球唯一id加上文件后缀
        String fileName = UUID.randomUUID().toString() + suffix;

        File file1 = new File(basePath);
        //如果不存在就创建一个新的目录
        if(!file1.exists()){
            file1.mkdirs();
        }
        // E:\ideaJava\src\main\resources\static\ + uuid + 文件末尾
        File cacheFile = new File(basePath + fileName);

        String url;
        String md5;
        try {
            //存入
            file.transferTo(cacheFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //md5判断文件是否相同
        md5 = SecureUtil.md5(cacheFile);
        TbFile selectFile = getFileByMd5(md5);
        if(selectFile != null){
            url = selectFile.getUrl();
            //因为文件已经存在 删除已存在的文件
            cacheFile.delete();
        }else {
            url = "http://localhost:80/" + fileName;
        }

        //存入数据库
        TbFile tbFile = new TbFile();
        tbFile.setFileName(fileName);
        tbFile.setFileSize(size/1024);
        tbFile.setType(type);
        tbFile.setUrl(url);
        tbFile.setMd5(md5);
        this.save(tbFile);

        return url;
    }

    @Override
    public void download(String name, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");
            //读取文件
            FileInputStream is = new FileInputStream(new File(basePath + name));
            //写回页面
            ServletOutputStream os = response.getOutputStream();

            int len = -1;
            byte [] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1){
                os.write(bytes,0,len);
                os.flush();
            }
            os.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TbFile getFileByMd5(String md5){
        LambdaQueryWrapper<TbFile> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbFile::getMd5,md5);
        List<TbFile> list = this.list(queryWrapper);
        return list.size() > 0 ? list.get(0) : null;
    }
}




