package com.the_ring.dao;

import com.the_ring.domain.ReaderInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface ReaderInfoMapper {

    ArrayList<ReaderInfo> getAllReaderInfo();

    ReaderInfo findReaderInfoByReaderId(int readerId);

    int deleteReaderInfo(int readerId);

    int editReaderInfo(ReaderInfo readerInfo);

    int addReaderInfo(ReaderInfo readerInfo);

}
