package com.the_ring.service;

public interface AdminService {

    int getMatchCount(int adminId, String password);

    int rePassword(int adminId, String newPasswd);

    String getPasswd(int id);

}
