package com.the_ring.service;

import com.the_ring.dao.ReaderInfoDao;
import com.the_ring.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReaderInfoService {

    private ReaderInfoDao infoDao;

    @Autowired
    public void setInfoDao(ReaderInfoDao infoDao) {
        this.infoDao = infoDao;
    }

    public ArrayList<ReaderInfo> readerInfos() {
        return infoDao.getAllReaderInfo();
    }

    public boolean deleteReaderInfo(int readerId) {
        return infoDao.deleteReaderInfo(readerId) > 0;
    }

    public ReaderInfo getReaderInfo(int readerId) {
        return infoDao.findReaderInfoByReaderId(readerId);
    }

    public boolean editReaderInfo(ReaderInfo readerInfo) {
        return infoDao.editReaderInfo(readerInfo) > 0;
    }

    public boolean addReaderInfo(ReaderInfo readerInfo) {
        return infoDao.addReaderInfo(readerInfo) > 0;
    }
}
