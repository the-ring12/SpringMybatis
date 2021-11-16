package com.the_ring.dao;

import com.the_ring.mapper.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AdminDao extends SqlSessionDaoSupport implements AdminMapper {

//    private SqlSession sqlSession;
    private AdminMapper adminMapper;

    @Override
    @Resource
    public void setSqlSessionFactory(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

//    public AdminDao() {
//        sqlSession = getSqlSession();
//
//        System.out.println(sqlSession);
//    }


    public int getMatchCount(int adminId, String password) {
        adminMapper = getSqlSession().getMapper(AdminMapper.class);
        int matchCount = adminMapper.getMatchCount(adminId, password);
        return matchCount;
    }


    public int rePassword(int adminId, String newPasswd) {
        adminMapper = getSqlSession().getMapper(AdminMapper.class);
        int result = adminMapper.rePassword(adminId, newPasswd);
        return result;
    }


    public String getPasswd(int id) {
        adminMapper = getSqlSession().getMapper(AdminMapper.class);
        String passwd = adminMapper.getPasswd(id);
        return passwd;
    }

}
