package com.the_ring.service;

import com.the_ring.dao.ReaderInfoMapper;
import com.the_ring.domain.ReaderInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReaderInfoServiceImpl extends SqlSessionDaoSupport implements ReaderInfoService {

    private ReaderInfoMapper readerInfoMapper;

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public List<ReaderInfo> getAllReaderInfo() {
        readerInfoMapper = this.getSqlSession().getMapper(ReaderInfoMapper.class);
        return readerInfoMapper.getAllReaderInfo();
    }

    @Override
    public ReaderInfo findReaderInfoByReaderId(int readerId) {
        readerInfoMapper = this.getSqlSession().getMapper(ReaderInfoMapper.class);
        return readerInfoMapper.findReaderInfoByReaderId(readerId);
    }

    @Override
    public int deleteReaderInfo(int readerId) {
        readerInfoMapper = this.getSqlSession().getMapper(ReaderInfoMapper.class);
        return readerInfoMapper.deleteReaderInfo(readerId);
    }

    @Override
    public int editReaderInfo(ReaderInfo readerInfo) {
        readerInfoMapper = this.getSqlSession().getMapper(ReaderInfoMapper.class);
        return readerInfoMapper.editReaderInfo(readerInfo);
    }

    @Override
    public int addReaderInfo(ReaderInfo readerInfo) {
        readerInfoMapper = this.getSqlSession().getMapper(ReaderInfoMapper.class);
        return readerInfoMapper.addReaderInfo(readerInfo);
    }
}
