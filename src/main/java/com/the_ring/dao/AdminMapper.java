package com.the_ring.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AdminMapper {

    /**
     * 查询是否有 admin 用户
     * @param adminId
     * @param password
     * @return
     */
    int getMatchCount(@Param("admin_id") int adminId, @Param("password") String password);

    int rePassword(@Param("admin_id") int adminId, @Param("password") String newPasswd);

    String getPasswd(@Param("admin_id") int id);

}
