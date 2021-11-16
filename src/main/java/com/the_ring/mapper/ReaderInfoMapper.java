package com.the_ring.mapper;

import com.the_ring.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public interface ReaderInfoMapper {


    ArrayList<ReaderInfo> getAllReaderInfo();

    ReaderInfo findReaderInfoByReaderId(int readerId);

    int deleteReaderInfo(int readerId);

    int editReaderInfo(ReaderInfo readerInfo);

    int addReaderInfo(ReaderInfo readerInfo);

}
