package com.the_ring.controller;

import com.the_ring.mapper.UserMapper;
import com.the_ring.pojo.User;
import com.the_ring.service.IUserService;
import com.the_ring.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String getUser() {
        List<User> users = userMapper.findAllUser();
        System.out.println(users);
//        if (users == null) {
//            return "index";
//        }
        return "index";
    }

    @RequestMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public String addUser() {
        User user = new User();
        user.setAge(99);
        user.setName("甲");
        user.setSex(0);
        userMapper.addUser(user);
        return "index";
    }
}
