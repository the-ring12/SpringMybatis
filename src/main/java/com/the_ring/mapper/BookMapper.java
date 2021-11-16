package com.the_ring.mapper;

import com.the_ring.domain.Book;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;


public interface BookMapper {

    int matchBook(@Param("sw") String searchWord);

    List<Book> queryBook(String sw);

    ArrayList<Book> getAllBooks();

    int deleteBook(long bookId);

    int addBook(Book book);

    Book getBook(Long bookId);

    int editBook(Book book);

}
