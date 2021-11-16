package com.the_ring.operate;

import com.the_ring.domain.ReaderInfo;
import com.the_ring.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderOperate {

    private ReaderInfoService readerInfoService;

    @Autowired
    public void setReaderInfoService(ReaderInfoService readerInfoService) {
        this.readerInfoService = readerInfoService;
    }

    // 获取所有 Reader 信息
    public List<ReaderInfo> readerInfos() {
        return readerInfoService.getAllReaderInfo();
    }

    // 删除指定 reader 信息
    public boolean deleteReaderInfo(int readerId) {
        return readerInfoService.deleteReaderInfo(readerId) > 0;
    }

    // 获取指定 reader 信息
    public ReaderInfo getReaderInfo(int readerId) {
        return readerInfoService.findReaderInfoByReaderId(readerId);
    }

    // 修改 Reader 信息
    public boolean editReaderInfo(ReaderInfo readerInfo) {
        return readerInfoService.editReaderInfo(readerInfo) > 0;
    }

    // 添加 readerCard
    public boolean addReaderInfo(ReaderInfo readerInfo) {
        return readerInfoService.addReaderInfo(readerInfo) > 0;
    }
}
