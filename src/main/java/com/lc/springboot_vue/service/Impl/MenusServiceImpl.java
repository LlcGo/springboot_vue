package com.lc.springboot_vue.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.pojo.Menus;
import com.lc.springboot_vue.service.MenusService;
import com.lc.springboot_vue.mapper.MenusMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class MenusServiceImpl extends ServiceImpl<MenusMapper, Menus>
    implements MenusService {

    @Override
    public Restult<List<Menus>> getAllMenu() {
        LambdaQueryWrapper<Menus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menus::getParentid, 0);
        //查询出所有的父节点
        List<Menus> menusList = this.list(queryWrapper);
        setMenus(menusList);
        return Restult.success(menusList);
    }

    private void setMenus(List<Menus> menusList) {
        if (menusList != null) {
            for (Menus menus : menusList) {
                //查询所有的父节点是menus的节点
                //select * from menu where parentid = menus.id
                LambdaQueryWrapper<Menus> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Menus::getParentid, menus.getMenuId());
                List<Menus> chlirend = this.list(queryWrapper1);
                menus.setChildren(chlirend);
                //递归
                setMenus(chlirend);
            }
        }
    }

    @Override
    public List<Menus> getMuneListById(Long id) {
        List<Menus> fatehrList = this.baseMapper.getMuneListById(id, 0);
        setChrilds(id, fatehrList);
        return fatehrList;
    }

    private void setChrilds(Long id, List<Menus> fatehrList) {
        if(fatehrList != null){
            for (Menus menus : fatehrList) {
                List<Menus> chrildListById = this.baseMapper.getMuneListById(id, menus.getMenuId());
                menus.setChildren(chrildListById);
                //递归
                setChrilds(id,chrildListById);
            }
        }

    }


}




