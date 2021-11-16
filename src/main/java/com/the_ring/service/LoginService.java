package com.the_ring.service;

import com.the_ring.dao.AdminDao;
import com.the_ring.dao.ReaderCardDao;
import com.the_ring.dao.ReaderInfoDao;
import com.the_ring.domain.ReaderCard;
import com.the_ring.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private AdminDao adminDao;
    private ReaderCardDao readerCardDao;
    private ReaderInfoDao infoDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Autowired
    public void setReaderCardDao(ReaderCardDao readerCardDao) {
        this.readerCardDao = readerCardDao;
    }

    @Autowired
    public void setInfoDao(ReaderInfoDao readerInfoDao) {
        this.infoDao = readerInfoDao;
    }

    public boolean hasMatchReader(int readerId, String passwd) {
        return readerCardDao.getMatchCount(readerId, passwd) > 0;
    }

    public ReaderCard findReaderCardByUserId(int readerId) {
        return readerCardDao.findReaderByReaderId(readerId);
    }

    public ReaderInfo findReaderInfoByReaderId(int readerId) {
        return infoDao.findReaderInfoByReaderId(readerId);
    }

    public boolean hasMatchAdmin(int adminId, String password) {
        return adminDao.getMatchCount(adminId, password) == 1;
    }

    public boolean adminRePasswd(int adminId, String newPasswd) {
        return adminDao.rePassword(adminId, newPasswd) > 0;
    }

    public String getAdminPasswd(int id) {
        return adminDao.getPasswd(id);
    }


}
