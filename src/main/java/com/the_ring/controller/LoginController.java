package com.the_ring.controller;

import com.the_ring.domain.Admin;
import com.the_ring.domain.ReaderCard;
import com.the_ring.operate.LoginOperate;
import com.the_ring.util.PageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

//标注为一个Spring mvc的Controller
@Controller
public class LoginController {

    private LoginOperate loginOperate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    public void setLoginOperate(LoginOperate loginOperate) {
        this.loginOperate = loginOperate;
    }

    //负责处理login.html请求
    @RequestMapping(value = {"/", "/login.html"})
    public String toLogin(HttpServletRequest request) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        request.getSession().invalidate();
        return "index";
    }

    @RequestMapping("/logout.html")
    public String logout(HttpServletRequest request) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        request.getSession().invalidate();
        return "redirect:/login.html";
    }


    //负责处理loginCheck.html请求
    //请求参数会根据参数名称默认契约自动绑定到相应方法的入参中
    @RequestMapping(value = "api/loginCheck", method = RequestMethod.POST)
    public @ResponseBody
    Object loginCheck(HttpServletRequest request) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        int id = Integer.parseInt(request.getParameter("id"));
        String passwd = request.getParameter("passwd");
        boolean isReader = loginOperate.hasMatchReader(id, passwd);
        boolean isAdmin = loginOperate.hasMatchAdmin(id, passwd);
        HashMap<String, String> res = new HashMap<String, String>();
        if (isAdmin == false && isReader == false) {
            res.put("stateCode", "0");
            res.put("msg", "账号或密码错误！");
        } else if (isAdmin) {
            Admin admin = new Admin();
            admin.setAdminId(id);
            admin.setPassword(passwd);
            request.getSession().setAttribute("admin", admin);
            res.put("stateCode", "1");
            res.put("msg", "管理员登陆成功！");
        } else {
            ReaderCard readerCard = loginOperate.findReaderCardByUserId(id);
            request.getSession().setAttribute("readercard", readerCard);
            res.put("stateCode", "2");
            res.put("msg", "读者登陆成功！");
        }
        return res;
    }

    ;

    @RequestMapping("/admin_main.html")
    public ModelAndView toAdminMain(HttpServletResponse response) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        return new ModelAndView("admin_main");
    }


    @RequestMapping("/reader_main.html")
    public ModelAndView toReaderMain(HttpServletResponse response) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        return new ModelAndView("reader_main");
    }


    @RequestMapping("/admin_repasswd.html")
    public ModelAndView reAdminPasswd() {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        return new ModelAndView("admin_repasswd");
    }

    @RequestMapping("/admin_repasswd_do")
    public String reAdminPasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes) {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        int id = admin.getAdminId();
        String passwd = loginOperate.getAdminPasswd(id);

        if (passwd.equals(oldPasswd)) {
            boolean succ = loginOperate.adminRePasswd(id, newPasswd);
            if (succ) {

                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                return "redirect:/admin_repasswd.html";
            } else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/admin_repasswd.html";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/admin_repasswd.html";
        }
    }

    ;

    //配置404页面
    @RequestMapping("*")
    public String notFind() {
        // kafka 生产者发送消息
        PageMessage.getMessage(request);

        return "404";
    }


}