package com.lc.springboot_vue.mapper;

import com.lc.springboot_vue.pojo.Menus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.lc.springboot_vue.pojo.Menus
 */
public interface MenusMapper extends BaseMapper<Menus> {
       List<Menus> getMuneListById(@Param("userId")Long id,@Param("pid")Integer pid);
}




