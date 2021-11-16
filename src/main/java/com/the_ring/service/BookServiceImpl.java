package com.the_ring.service;

import com.the_ring.dao.BookMapper;
import com.the_ring.domain.Book;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl extends SqlSessionDaoSupport implements BookService {

    private BookMapper bookMapper;

    @Resource
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int matchBook(String searchWord) {
        bookMapper = this.getSqlSession().getMapper(BookMapper.class);
        return bookMapper.matchBook(searchWord);
    }

    @Override
    public List<Book> queryBook(String sw) {
        bookMapper = this.getSqlSession().getMapper(BookMapper.class);
        return bookMapper.queryBook(sw);
    }

    @Override
    public List<Book> getAllBooks() {
        bookMapper = this.getSqlSession().getMapper(BookMapper.class);
        return bookMapper.getAllBooks();
    }

    @Override
    public int deleteBook(long bookId) {
        bookMapper = this.getSqlSession().getMapper(BookMapper.class);
        return bookMapper.deleteBook(bookId);
    }

    @Override
    public int addBook(Book book) {
        bookMapper = this.getSqlSession().getMapper(BookMapper.class);
        return bookMapper.addBook(book);
    }

    @Override
    public Book getBook(Long bookId) {
        bookMapper = this.getSqlSession().getMapper(BookMapper.class);
        return bookMapper.getBook(bookId);
    }

    @Override
    public int editBook(Book book) {
        bookMapper = this.getSqlSession().getMapper(BookMapper.class);
        return bookMapper.editBook(book);
    }
}
