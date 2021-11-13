package com.the_ring.mapper;

import com.the_ring.pojo.User;

import java.util.List;

public interface UserMapper {

    void addUser(User user);

    void delUserById(Integer id);

    List<User> findAllUser();
}
