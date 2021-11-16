package com.the_ring.controller;

import com.the_ring.domain.Book;
import com.the_ring.domain.ReaderCard;
import com.the_ring.operate.BookOperate;
import com.the_ring.operate.LendOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LendController {
    
    private BookOperate bookOperate;
    private LendOperate lendOperate;

    @Autowired
    public void setBookOperate(BookOperate bookOperate) {
        this.bookOperate = bookOperate;
    }

    @Autowired
    public void setLendOperate(LendOperate lendOperate) {
        this.lendOperate = lendOperate;
    }

    @RequestMapping("/lendbook.html")
    public ModelAndView bookLend(HttpServletRequest request){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        Book book=bookOperate.getBook(bookId);
       ModelAndView modelAndView=new ModelAndView("admin_book_lend");
       modelAndView.addObject("book",book);
       return modelAndView;
    }

    @RequestMapping("/lendbookdo.html")
    public String bookLendDo(HttpServletRequest request, RedirectAttributes redirectAttributes, int readerId){
        long bookId=Integer.parseInt(request.getParameter("id"));
        boolean lendsucc=lendOperate.bookLend(bookId,readerId);
        if (lendsucc){
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
            return "redirect:/allbooks.html";
        }else {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
            return "redirect:/allbooks.html";
        }


    }

    @RequestMapping("/returnbook.html")
    public String bookReturn(HttpServletRequest request, RedirectAttributes redirectAttributes){
        long bookId=Integer.parseInt(request.getParameter("bookId"));
        boolean retSucc=lendOperate.bookReturn(bookId);
        if (retSucc){
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
            return "redirect:/allbooks.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", "图书归还失败！");
            return "redirect:/allbooks.html";
        }
    }


    @RequestMapping("/lendlist.html")
    public ModelAndView lendList(){

        ModelAndView modelAndView=new ModelAndView("admin_lend_list");
        modelAndView.addObject("list",lendOperate.lendList());
        return modelAndView;
    }
    @RequestMapping("/mylend.html")
    public ModelAndView myLend(HttpServletRequest request){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView=new ModelAndView("reader_lend_list");
        modelAndView.addObject("list",lendOperate.myLendList(readerCard.getReaderId()));
        return modelAndView;
    }




}
