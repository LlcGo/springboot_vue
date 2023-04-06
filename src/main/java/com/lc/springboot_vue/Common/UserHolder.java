package com.lc.springboot_vue.Common;


import com.lc.springboot_vue.Dto.UserDTO;
import com.lc.springboot_vue.pojo.User;

public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static void saveUser(UserDTO user){
        tl.set(user);
    }

    public static UserDTO getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
