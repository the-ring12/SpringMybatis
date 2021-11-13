package com.the_ring.service;

import com.the_ring.mapper.UserMapper;
import com.the_ring.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public interface IUserService {
    List<User> listUser();

    void addUser(User user);
}
