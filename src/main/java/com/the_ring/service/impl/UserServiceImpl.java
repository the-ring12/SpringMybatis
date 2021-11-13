package com.the_ring.service.impl;

import com.the_ring.mapper.UserMapper;
import com.the_ring.pojo.User;
import com.the_ring.service.IUserService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserServiceImpl extends SqlSessionDaoSupport implements UserMapper {

    private SqlSession sqlSession;

    public UserServiceImpl() {
        sqlSession = this.getSqlSession();
    }

    @Override
    public void addUser(User user) {
        sqlSession.insert("addUser", user);
    }

    @Override
    public void delUserById(Integer id) {
        sqlSession.delete("delUser", id);
    }

    @Override
    public List<User> findAllUser() {
        List<User> users = sqlSession.selectList("findAllUser");
        return users;
    }
}
