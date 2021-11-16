package com.the_ring.mapper;

import com.the_ring.domain.Lend;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public interface LendMapper {

    /**
     * 在还书表中添加日期
     * @param bookId
     * @param date
     * @return
     */
    int bookReturnOne(@Param("book_id") long bookId, @Param("date") Date date);

    /**
     * 修改书籍状态
     * @param bookId
     * @return
     */
    int bookReturnTwo(long bookId);

    /**
     * 在lend_list 中添加一行借出数据
     * @param bookId
     * @param readerId
     * @param date
     * @return
     */
    int bookLendOne(@Param("book_id") long bookId, @Param("reader_id") int readerId, @Param("date") Date date);

    /**
     * 修改图书状态
     * @param bookId
     * @return
     */
    int bookLendTwo(long bookId);

    /**
     * 查询所有的借出数据
     * @return
     */
    ArrayList<Lend> lendList();

    /**
     * 查询某个用户的借阅数据
     * @param readerId
     * @return
     */
    ArrayList<Lend> myLendList(int readerId);
}
