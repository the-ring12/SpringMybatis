package com.the_ring.service;

import com.the_ring.domain.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookService {

    int matchBook(String searchWord);

    List<Book> queryBook(String sw);

    List<Book> getAllBooks();

    int deleteBook(long bookId);

    int addBook(Book book);

    Book getBook(Long bookId);

    int editBook(Book book);

}
