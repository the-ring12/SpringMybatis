package com.the_ring.dao;

import com.the_ring.domain.Lend;
import com.the_ring.mapper.BookMapper;
import com.the_ring.mapper.LendMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class LendDao extends SqlSessionDaoSupport implements LendMapper {

//    private SqlSession sqlSession;

    private LendMapper lendMapper;

    @Override
    @Resource
    public void setSqlSessionFactory(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

//    public LendDao() {
//        sqlSession = this.getSqlSession();
//    }


    
    public int bookReturnOne(long bookId, Date date) {
        lendMapper = getSqlSession().getMapper(LendMapper.class);
        int result = lendMapper.bookReturnOne(bookId, date);
        return result;
    }

    
    public int bookReturnTwo(long bookId) {
        lendMapper = getSqlSession().getMapper(LendMapper.class);
        int result = lendMapper.bookReturnTwo(bookId);
        return result;
    }

    
    public int bookLendOne(long bookId, int readerId, Date date) {
        lendMapper = getSqlSession().getMapper(LendMapper.class);
        int result = lendMapper.bookLendOne(bookId, readerId, date);
        return result;
    }


    
    public int bookLendTwo(long bookId) {
        lendMapper = getSqlSession().getMapper(LendMapper.class);
        int result = lendMapper.bookLendTwo(bookId);
        return result;
    }

    
    public ArrayList<Lend> lendList() {
        lendMapper = getSqlSession().getMapper(LendMapper.class);
        ArrayList<Lend> lends = lendMapper.lendList();
        return lends;
    }

    
    public ArrayList<Lend> myLendList(int readerId) {
        lendMapper = getSqlSession().getMapper(LendMapper.class);
        ArrayList<Lend> lends = lendMapper.myLendList(readerId);
        return lends;
    }
}
