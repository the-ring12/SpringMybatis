package com.the_ring.service;

import com.the_ring.dao.ReaderCardMapper;
import com.the_ring.domain.ReaderCard;
import com.the_ring.domain.ReaderInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReaderCardServiceImpl extends SqlSessionDaoSupport implements ReaderCardService {

    private ReaderCardMapper readerCardMapper;

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int getMatchCount(int readerId, String passwd) {
        readerCardMapper = this.getSqlSession().getMapper(ReaderCardMapper.class);
        return readerCardMapper.getMatchCount(readerId, passwd);
    }

    @Override
    public ReaderCard findReaderByReaderId(int readerId) {
        readerCardMapper = this.getSqlSession().getMapper(ReaderCardMapper.class);
        return readerCardMapper.findReaderByReaderId(readerId);
    }

    @Override
    public int rePassword(int readerId, String newPasswd) {
        readerCardMapper = this.getSqlSession().getMapper(ReaderCardMapper.class);
        return readerCardMapper.rePassword(readerId, newPasswd);
    }

    @Override
    public int addReaderCard(ReaderInfo readerInfo) {
        readerCardMapper = this.getSqlSession().getMapper(ReaderCardMapper.class);
        return readerCardMapper.addReaderCard(readerInfo);
    }

    @Override
    public int updateName(int readerId, String name) {
        readerCardMapper = this.getSqlSession().getMapper(ReaderCardMapper.class);
        return readerCardMapper.updateName(readerId, name);
    }
}
