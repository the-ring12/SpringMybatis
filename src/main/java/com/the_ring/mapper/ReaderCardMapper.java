package com.the_ring.mapper;

import com.the_ring.domain.ReaderCard;
import com.the_ring.domain.ReaderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReaderCardMapper {

    /**
     * 查询是否有匹配的读者
     * @param readerId
     * @param passwd
     * @return
     */
    int getMatchCount(@Param("reader_id") int readerId, @Param("password") String passwd);

    ReaderCard findReaderByReaderId(int userId);

    int rePassword(int readerId, String newPasswd);

    int addReaderCard(ReaderInfo readerInfo);

    int updateName(int readerId, String name);
}
