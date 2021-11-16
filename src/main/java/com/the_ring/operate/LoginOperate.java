package com.the_ring.operate;

import com.the_ring.domain.ReaderCard;
import com.the_ring.service.AdminService;
import com.the_ring.service.ReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginOperate {

    private AdminService adminService;
    private ReaderCardService readerCardService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setReaderCardService(ReaderCardService readerCardService) {
        this.readerCardService = readerCardService;
    }

    // 是否有匹配的 admin
    public boolean hasMatchReader(int id, String passwd) {
        return readerCardService.getMatchCount(id, passwd) > 0;
    }

    // 是否有匹配的 admin
    public boolean hasMatchAdmin(int id, String passwd) {
        return adminService.getMatchCount(id, passwd) > 0;
    }

    // 获取readerCard信息
    public ReaderCard findReaderCardByUserId(int id) {
        return readerCardService.findReaderByReaderId(id);
    }

    // 获取 admin 密码
    public String getAdminPasswd(int id) {
        return adminService.getPasswd(id);
    }

    // 修改 admin 密码
    public boolean adminRePasswd(int id, String newPasswd) {
        return adminService.rePassword(id, newPasswd) > 0;
    }
}
