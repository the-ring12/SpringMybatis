package com.the_ring.service;

import com.the_ring.dao.LendMapper;
import com.the_ring.domain.Lend;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

@Service
public class LendServiceImpl  extends SqlSessionDaoSupport implements LendService {

    private LendMapper lendMapper;

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int bookReturnOne(long bookId, Date date) {
        lendMapper = this.getSqlSession().getMapper(LendMapper.class);
        return lendMapper.bookReturnOne(bookId, date);
    }

    @Override
    public int bookReturnTwo(long bookId) {
        lendMapper = this.getSqlSession().getMapper(LendMapper.class);
        return lendMapper.bookReturnTwo(bookId);
    }

    @Override
    public int bookLendOne(long bookId, int readerId, Date date) {
        lendMapper = this.getSqlSession().getMapper(LendMapper.class);
        return lendMapper.bookLendOne(bookId, readerId, date);
    }

    @Override
    public int bookLendTwo(long bookId) {
        lendMapper = this.getSqlSession().getMapper(LendMapper.class);
        return lendMapper.bookLendTwo(bookId);
    }

    @Override
    public ArrayList<Lend> lendList() {
        lendMapper = this.getSqlSession().getMapper(LendMapper.class);
        return lendMapper.lendList();
    }

    @Override
    public ArrayList<Lend> myLendList(int readerId) {
        lendMapper = this.getSqlSession().getMapper(LendMapper.class);
        return lendMapper.myLendList(readerId);
    }
}
