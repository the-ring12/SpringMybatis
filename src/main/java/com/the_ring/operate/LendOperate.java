package com.the_ring.operate;

import com.the_ring.domain.Lend;
import com.the_ring.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LendOperate {

    private LendService lendService;

    @Autowired
    public void setLendService(LendService lendService) {
        this.lendService = lendService;
    }

    // 借书
    public boolean bookLend(long bookId, int readerId) {
        Date date = new Date();
        if ((lendService.bookLendOne(bookId, readerId, date)) > 0) {
            if ((lendService.bookLendTwo(bookId)) > 0) return true;
        }
        return false;
    }

    // 还书
    public boolean bookReturn(long bookId) {
        Date date = new Date();
        if ((lendService.bookReturnOne(bookId, date)) > 0) {
            if ((lendService.bookReturnTwo(bookId)) > 0) return true;
        }
        return false;
    }

    // 借阅日志
    public List<Lend> lendList() {
        return lendService.lendList();
    }

    // 指定用户的借阅日志
    public List<Lend> myLendList(long readerId) {
        return lendService.myLendList((int) readerId);
    }
}
