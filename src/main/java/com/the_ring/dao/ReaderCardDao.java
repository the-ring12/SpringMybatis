package com.the_ring.dao;

import com.the_ring.domain.ReaderCard;
import com.the_ring.domain.ReaderInfo;
import com.the_ring.mapper.AdminMapper;
import com.the_ring.mapper.ReaderCardMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ReaderCardDao extends SqlSessionDaoSupport implements ReaderCardMapper{


//    private SqlSession sqlSession;
    private ReaderCardMapper readerCardMapper;

    @Override
    @Resource
    public void setSqlSessionFactory(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
//
//    public ReaderCardDao() {
//        sqlSession = super.getSqlSession();
//
//        System.out.println(sqlSession);
//    }

    
    public int getMatchCount(int readerId, String passwd) {
        readerCardMapper = getSqlSession().getMapper(ReaderCardMapper.class);
        int result = readerCardMapper.getMatchCount(readerId, passwd);
        return result;

    }

    
    public ReaderCard findReaderByReaderId(int userId) {
        readerCardMapper = getSqlSession().getMapper(ReaderCardMapper.class);
        ReaderCard readerCard = readerCardMapper.findReaderByReaderId(userId);
        return readerCard;
    }

    
    public int rePassword(int readerId, String newPasswd) {
        readerCardMapper = getSqlSession().getMapper(ReaderCardMapper.class);
        int result = readerCardMapper.rePassword(readerId, newPasswd);
        return result;
    }

    
    public int addReaderCard(ReaderInfo readerInfo) {
        readerCardMapper =getSqlSession().getMapper(ReaderCardMapper.class);
        int result = readerCardMapper.addReaderCard(readerInfo);
        return result;
    }

    
    public int updateName(int readerId, String name) {
        readerCardMapper = getSqlSession().getMapper(ReaderCardMapper.class);
        int result = readerCardMapper.updateName(readerId, name);
        return result;
    }

}
