package com.the_ring.service;

import com.the_ring.domain.Lend;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Date;

public interface LendService {

    int bookReturnOne(@Param("book_id") long bookId, @Param("date") Date date);

    int bookReturnTwo(long bookId);

    int bookLendOne(@Param("book_id") long bookId, @Param("reader_id") int readerId, @Param("date") Date date);

    int bookLendTwo(long bookId);

    ArrayList<Lend> lendList();

    ArrayList<Lend> myLendList(int readerId);
}
