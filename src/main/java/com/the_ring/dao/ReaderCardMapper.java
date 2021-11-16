package com.the_ring.dao;

import com.the_ring.domain.ReaderCard;
import com.the_ring.domain.ReaderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ReaderCardMapper {

    int getMatchCount(@Param("reader_id") int readerId, @Param("password") String passwd);

    ReaderCard findReaderByReaderId(int readerId);

    int rePassword(int readerId, String newPasswd);

    int addReaderCard(ReaderInfo readerInfo);

    int updateName(int readerId, String name);
}
