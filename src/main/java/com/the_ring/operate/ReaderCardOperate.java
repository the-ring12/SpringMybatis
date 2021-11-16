package com.the_ring.operate;

import com.the_ring.domain.ReaderInfo;
import com.the_ring.service.ReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderCardOperate {

    private ReaderCardService readerCardService;

    @Autowired
    public void setReaderCardService(ReaderCardService readerCardService) {
        this.readerCardService = readerCardService;
    }

    // 修改 ReaderCar 信息
    public boolean updateName(int readerId, String name) {
        return readerCardService.updateName(readerId, name) > 0;
    }

    // 修改 readerCard 密码
    public boolean updatePasswd(int readerId, String newPasswd) {
        return readerCardService.rePassword(readerId, newPasswd) > 0;
    }

    // 添加 ReaderCard
    public boolean addReaderCard(ReaderInfo readerInfo) {
        return readerCardService.addReaderCard(readerInfo) > 0;
    }
}
