package com.itheima.client.web.clientweb.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.client.web.clientweb.dao.UserMapper;
import com.feginapi.domain.User;
import com.feginapi.utils.AjaxResult;
import com.feginapi.utils.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/info")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/list")
    @ResponseBody
    public AjaxResult list(@RequestBody User user) {
        Page<User> userPage = new Page<>();
        userPage.setCurrent(2);
        userPage.setSize(1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> userIPage = userMapper.selectPage(userPage,queryWrapper);
        return  AjaxResult.toReponseEntry("成功",userIPage, HttpStatus.HTTP_OK);
    }

    @RequestMapping("/aa")
    @ResponseBody
    public String aa() {
        return  "aa";
    }
}
