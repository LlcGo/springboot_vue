package com.lc.springboot_vue.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.ws.soap.Addressing;

/**
 * @Author Lc
 * @Date 2023/3/30
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restult <T>{
    private Integer code;
    private String message; //错误信息
    private T data;


    //成功返回数据
    public static <T> Restult<T> success(T data){
        return new Restult<>(20000,"success",data);
    }

    //成功不返回数据
    public static <T>  Restult<T> success(){
        return new Restult<>(20000,"success",null);
    }

    //失败
    public static <T> Restult<T> fail (String msg){
        return new Restult<>(20001,msg,null);
    }


}
