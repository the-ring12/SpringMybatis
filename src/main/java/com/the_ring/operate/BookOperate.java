package com.the_ring.operate;

import com.the_ring.domain.Book;
import com.the_ring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookOperate {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    // 查询是否有匹配的书籍
    public boolean matchBook(String searchWord){
        String search = "%" + searchWord + "%";
        return bookService.matchBook(search) > 0;
    }

    // 查询书籍
    public List<Book> queryBook(String searchWord) {
        String search = "%" + searchWord + "%";
        return bookService.queryBook(search);
    }

    // 查询所有图书
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // 删除指定图书
    public int deleteBook(long bookId) {
        return bookService.deleteBook(bookId);
    }

    // 添加图书
    public boolean addBook(Book book) {
        return bookService.addBook(book) > 0;
    }

    // 获取指定图书信息
    public Book getBook(long bookId) {
        return bookService.getBook(bookId);
    }

    // 编辑指定图书信息
    public boolean editBook(Book book) {
        return bookService.editBook(book) > 0;
    }
}
