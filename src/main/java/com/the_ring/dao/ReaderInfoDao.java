package com.the_ring.dao;

import com.the_ring.domain.ReaderInfo;
import com.the_ring.mapper.ReaderInfoMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class ReaderInfoDao extends SqlSessionDaoSupport implements ReaderInfoMapper {

    private ReaderInfoMapper readerInfoMapper;

    @Override
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public ArrayList<ReaderInfo> getAllReaderInfo() {
        readerInfoMapper = getSqlSession().getMapper(ReaderInfoMapper.class);
        ArrayList<ReaderInfo> readerInfo = readerInfoMapper.getAllReaderInfo();
        return readerInfo;
    }

    @Override
    public ReaderInfo findReaderInfoByReaderId(int readerId) {
        readerInfoMapper = getSqlSession().getMapper(ReaderInfoMapper.class);
        ReaderInfo readerInfo = readerInfoMapper.findReaderInfoByReaderId(readerId);
        return readerInfo;
    }

    @Override
    public int deleteReaderInfo(int readerId) {
        readerInfoMapper = getSqlSession().getMapper(ReaderInfoMapper.class);
        int result = readerInfoMapper.deleteReaderInfo(readerId);
        return result;
    }

    @Override
    public int editReaderInfo(ReaderInfo readerInfo) {
        readerInfoMapper = getSqlSession().getMapper(ReaderInfoMapper.class);
        int result = readerInfoMapper.editReaderInfo(readerInfo);
        return result;
    }

    @Override
    public int addReaderInfo(ReaderInfo readerInfo) {
        readerInfoMapper = getSqlSession().getMapper(ReaderInfoMapper.class);
        int result = readerInfoMapper.addReaderInfo(readerInfo);
        return result;
    }
}
