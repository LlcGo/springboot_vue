package com.lc.springboot_vue.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.springboot_vue.Common.Restult;
import com.lc.springboot_vue.mapper.UserMapper;
import com.lc.springboot_vue.pojo.Menus;
import com.lc.springboot_vue.pojo.User;
import com.lc.springboot_vue.pojo.UserToRole;
import com.lc.springboot_vue.service.MenusService;
import com.lc.springboot_vue.service.UserService;
import com.lc.springboot_vue.service.UserToRoleService;
import com.lc.springboot_vue.utils.JwtUtil;
import com.sun.javafx.menu.MenuBase;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Lc
 * @Date 2023/3/29
 * @Description
 */
@Service
public class UserviceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserToRoleService userToRoleService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private MenusService menusService;

    @Override
    public Restult<HashMap<String, Object>> login(User user) {
        //判断用户是否存在
        User sqluser = this.query().eq("username", user.getUsername()).one();
        if(sqluser == null){
            return Restult.fail("用户名不存在");
        }


        if(!passwordEncoder.matches(user.getPassword(),sqluser.getPassword())){
            return Restult.fail("密码错误");
        }

        String token =  jwtUtil.create(sqluser);
        //登录成功 根据token 存入redis的用户
        //Map<String, Object> userMap = BeanUtil.beanToMap(sqluser,new HashMap<>(),
        //        CopyOptions.create()
        //                .setFieldValueEditor((fieldName , fieldValue) -> fieldValue.toString()));
        //String cacheToken = LOGIN_USER_KEY + token;
        //stringRedisTemplate.opsForHash().putAll(cacheToken,userMap);
        //stringRedisTemplate.expire(cacheToken,30L,TimeUnit.MINUTES);
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);

        return Restult.success(tokenMap);
    }

    @Override
    public Restult<HashMap<String, Object>> info(String token) {
        //String cacheToken = LOGIN_USER_KEY + token;
        //根据token获取用户信息
        //Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(cacheToken);

        User user = null;
        try {
            user = jwtUtil.parseToken(token, User.class);
        } catch (Exception e) {
            return Restult.fail("用户信息获取失败");
        }
        //if(userMap.size() <= 0){
        //    return Restult.fail("用户登录失败");
        //}
        //User user = BeanUtil.fillBeanWithMap(userMap, new User(), false);
//        if(user == null){
//            return Restult.fail("用户登录失败");
//        }
        //查询角色
        Long userId = user.getId();
        List<String> roleList = this.baseMapper.getRoleById(userId);

        //根据id查询menuList
        List<Menus> menusList = menusService.getMuneListById(userId);

        HashMap<String, Object> data = new HashMap<>();
        data.put("name",user.getUsername());
        data.put("avatar",user.getAvatar());
        data.put("phone",user.getPhone());
        data.put("email",user.getEmail());
        data.put("id",userId);
        data.put("role",roleList);
        data.put("menuList",menusList);

        return Restult.success(data);
    }

    @Override
    public Restult<String> logout(String token) {
//        stringRedisTemplate.delete(token);
        return Restult.success();
    }

    @Override
    public Restult<HashMap<String, Object>> getlist(Integer pageNo, Integer pageSize, String username, String phone) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StrUtil.isNotBlank(username),User::getUsername,username);
        queryWrapper.eq(StrUtil.isNotBlank(phone),User::getPhone,phone);
        //排序
        queryWrapper.orderByDesc(User::getId);
        Page<User> userPage = new Page<>(pageNo, pageSize);
        Page<User> page = this.page(userPage, queryWrapper);

        HashMap<String, Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        return Restult.success(data);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        //1.先添加进去user
        this.baseMapper.insert(user);
        //2.将roleid放入数据库
        List<Integer> roleIdList = user.getRoleIdList();
        if(roleIdList.size() > 0){
            setRoleByUser(user, roleIdList);
        }
    }

    private void setRoleByUser(User user, List<Integer> roleIdList) {
        for (Integer roleId : roleIdList) {
            UserToRole userToRole = new UserToRole();
            userToRole.setRoleId(roleId);
            userToRole.setUserId(user.getId());
            userToRoleService.save(userToRole);
        }
    }

    @Override
    public User getRoleAndUserById(Long id) {
        //1.根据id查询用户
        User user = this.baseMapper.selectById(id);
        //根据id查出他的权限
        LambdaQueryWrapper<UserToRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserToRole::getUserId,id);
        List<UserToRole> userToRoleList = userToRoleService.list(queryWrapper);
        //创建一个arrylist将所有的id封装进去
//        List<Integer> roleIdList2 = userToRoleList.stream().map(userToRole -> {return userToRole.getRoleId()});
        List<Integer> roleIdList = new ArrayList<>(userToRoleList.size());
        for (UserToRole userToRole : userToRoleList) {
            Integer roleId = userToRole.getRoleId();
            roleIdList.add(roleId);
        }
        //放入user并且返回
        user.setRoleIdList(roleIdList);
        return user;
    }

    @Override
    public void removeRoleAndUserById(Long id) {
        //删除用户
        this.baseMapper.deleteById(id);
        //删除用户相关的Roleid
        LambdaQueryWrapper<UserToRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserToRole::getUserId,id);
        userToRoleService.remove(queryWrapper);
    }

    @Override
    public void updateRoleAndUserById(User user) {
        //1.直接修改用户的信息
        this.baseMapper.updateById(user);
        //2.根据userid删除他的所有权限
        LambdaQueryWrapper<UserToRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserToRole::getUserId,user.getId());
        userToRoleService.remove(queryWrapper);
        //3.然后再插入信息
        List<Integer> roleIdList = user.getRoleIdList();
        setRoleByUser(user,roleIdList);
    }
}
