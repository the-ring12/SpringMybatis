package com.the_ring.controller;

import com.the_ring.domain.Admin;
import com.the_ring.domain.Book;
import com.the_ring.kafka.entity.Page;
import com.the_ring.operate.BookOperate;
import com.the_ring.util.IPUtil;
import com.the_ring.util.PageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;

@Controller
public class BookController {

    private BookOperate bookOperate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private Page page;

    @Autowired
    public void setBookOperate(BookOperate bookOperate) {
        this.bookOperate = bookOperate;
    }

    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(HttpServletRequest request, String searchWord) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        boolean exist = bookOperate.matchBook(searchWord);
        if (exist) {
            ArrayList<Book> books = (ArrayList<Book>) bookOperate.queryBook(searchWord);
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books", books);
            return modelAndView;
        } else {
            return new ModelAndView("admin_books", "error", "没有匹配的图书");
        }
    }

    @RequestMapping("/reader_querybook.html")
    public ModelAndView readerQueryBook() {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        return new ModelAndView("reader_book_query");

    }

    @RequestMapping("/reader_querybook_do.html")
    public String readerQueryBookDo(HttpServletRequest request, String searchWord, RedirectAttributes redirectAttributes) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        boolean exist = bookOperate.matchBook(searchWord);
        if (exist) {
            ArrayList<Book> books = (ArrayList<Book>) bookOperate.queryBook(searchWord);
            redirectAttributes.addFlashAttribute("books", books);
            return "redirect:/reader_querybook.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "没有匹配的图书！");
            return "redirect:/reader_querybook.html";
        }

    }

    @RequestMapping("/allbooks.html")
    public ModelAndView allBook() {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        ArrayList<Book> books = (ArrayList<Book>) bookOperate.getAllBooks();
        ModelAndView modelAndView = new ModelAndView("admin_books");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @RequestMapping("/deletebook.html")
    public String deleteBook(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        long bookId = Integer.parseInt(request.getParameter("bookId"));
        int res = bookOperate.deleteBook(bookId);

        if (res == 1) {
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
            return "redirect:/allbooks.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping("/book_add.html")
    public ModelAndView addBook(HttpServletRequest request) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        return new ModelAndView("admin_book_add");

    }

    @RequestMapping("/book_add_do.html")
    public String addBookDo(BookAddCommand bookAddCommand, RedirectAttributes redirectAttributes) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        Book book = new Book();
        book.setBookId(0);
        book.setPrice(bookAddCommand.getPrice());
        book.setState(bookAddCommand.getState());
        book.setPublish(bookAddCommand.getPublish());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setName(bookAddCommand.getName());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassId(bookAddCommand.getClassId());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setIntroduction(bookAddCommand.getIntroduction());
        book.setPressmark(bookAddCommand.getPressmark());
        book.setLanguage(bookAddCommand.getLanguage());


        boolean succ = bookOperate.addBook(book);
        ArrayList<Book> books = (ArrayList<Book>) bookOperate.getAllBooks();
        if (succ) {
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
            return "redirect:/allbooks.html";
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookOperate.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(HttpServletRequest request, BookAddCommand bookAddCommand, RedirectAttributes redirectAttributes) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        long bookId = Integer.parseInt(request.getParameter("id"));
        Book book = new Book();
        book.setBookId(bookId);
        book.setPrice(bookAddCommand.getPrice());
        book.setState(bookAddCommand.getState());
        book.setPublish(bookAddCommand.getPublish());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setName(bookAddCommand.getName());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassId(bookAddCommand.getClassId());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setIntroduction(bookAddCommand.getIntroduction());
        book.setPressmark(bookAddCommand.getPressmark());
        book.setLanguage(bookAddCommand.getLanguage());


        boolean succ = bookOperate.editBook(book);
        if (succ) {
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
            return "redirect:/allbooks.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
            return "redirect:/allbooks.html";
        }
    }


    @RequestMapping("/bookdetail.html")
    public ModelAndView bookDetail(HttpServletRequest request) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookOperate.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }


    @RequestMapping("/readerbookdetail.html")
    public ModelAndView readerBookDetail(HttpServletRequest request) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        long bookId = Integer.parseInt(request.getParameter("bookId"));
        Book book = bookOperate.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("reader_book_detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }


}
