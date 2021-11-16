package com.the_ring.dao;

import com.the_ring.domain.Book;
import com.the_ring.mapper.BookMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDao extends SqlSessionDaoSupport implements BookMapper {



    private BookMapper bookMapper;

    @Override
    @Resource
    public void setSqlSessionFactory(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }



    public int matchBook(String searchWord) {
        bookMapper = getSqlSession().getMapper(BookMapper.class);
        int result = bookMapper.matchBook(searchWord);
        return result;
    }


    public ArrayList<Book> queryBook(String sw) {
        bookMapper = getSqlSession().getMapper(BookMapper.class);
        ArrayList<Book> bookList = (ArrayList<Book>) bookMapper.queryBook(sw);
        return bookList;
    }


    public ArrayList<Book> getAllBooks() {
        bookMapper = getSqlSession().getMapper(BookMapper.class);
        List<Book> bookList = bookMapper.getAllBooks();
        return (ArrayList<Book>) bookList;
    }


    public int deleteBook(long bookId) {
        bookMapper = getSqlSession().getMapper(BookMapper.class);
        int result = bookMapper.deleteBook(bookId);
        return result;
    }


    public int addBook(Book book) {
        bookMapper = getSqlSession().getMapper(BookMapper.class);
        int result = bookMapper.addBook(book);
        return result;
    }


    public Book getBook(Long bookId) {
        bookMapper = getSqlSession().getMapper(BookMapper.class);
        Book book = bookMapper.getBook(bookId);
        return book;
    }

    public int editBook(Book book) {
        bookMapper = getSqlSession().getMapper(BookMapper.class);
        int result = bookMapper.editBook(book);
        return result;
    }
}
