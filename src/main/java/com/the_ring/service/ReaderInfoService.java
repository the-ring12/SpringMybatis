package com.the_ring.service;

import com.the_ring.domain.ReaderInfo;

import java.util.List;

public interface ReaderInfoService {
    List<ReaderInfo> getAllReaderInfo();

    ReaderInfo findReaderInfoByReaderId(int readerId);

    int deleteReaderInfo(int readerId);

    int editReaderInfo(ReaderInfo readerInfo);

    int addReaderInfo(ReaderInfo readerInfo);
}
