package com.the_ring.service;

import com.the_ring.dao.LendDao;
import com.the_ring.mapper.LendMapper;
import com.the_ring.domain.Lend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class LendService {

    private LendDao lendDao;

    @Autowired
    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }

    public boolean bookReturn(long bookId) {
        Date date = new Date();
        return lendDao.bookReturnOne(bookId, date) > 0 && lendDao.bookReturnTwo(bookId) > 0;
    }

    public boolean bookLend(long bookId, int readerId) {
        Date date = new Date();
        return lendDao.bookLendOne(bookId, readerId, date) > 0 && lendDao.bookLendTwo(bookId) > 0;

    }

    public ArrayList<Lend> lendList() {
        return lendDao.lendList();
    }

    public ArrayList<Lend> myLendList(int readerId) {
        return lendDao.myLendList(readerId);
    }

}
