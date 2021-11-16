package com.the_ring.service;

import com.the_ring.dao.AdminMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl extends SqlSessionDaoSupport implements AdminService {

    private AdminMapper adminMapper;

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int getMatchCount(int adminId, String password) {
        adminMapper = this.getSqlSession().getMapper(AdminMapper.class);
        return adminMapper.getMatchCount(adminId, password);
    }

    @Override
    public int rePassword(int adminId, String newPasswd) {
        adminMapper = this.getSqlSession().getMapper(AdminMapper.class);
        return adminMapper.rePassword(adminId, newPasswd);
    }

    @Override
    public String getPasswd(int id) {
        adminMapper = this.getSqlSession().getMapper(AdminMapper.class);
        return adminMapper.getPasswd(id);
    }
}
