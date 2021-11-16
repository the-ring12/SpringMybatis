package com.the_ring.controller;

import com.the_ring.domain.ReaderCard;
import com.the_ring.domain.ReaderInfo;
import com.the_ring.operate.LoginOperate;
import com.the_ring.operate.ReaderCardOperate;
import com.the_ring.operate.ReaderOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ReaderController {

    private ReaderOperate readerOperate;
    private LoginOperate loginOperate;
    private ReaderCardOperate readerCardOperate;

    @Autowired
    public void setReaderOperate(ReaderOperate readerOperate) {
        this.readerOperate = readerOperate;
    }

    @Autowired
    public void setLoginOperate(LoginOperate loginOperate) {
        this.loginOperate = loginOperate;
    }

    @Autowired
    public void setReaderCardOperate(ReaderCardOperate readerCardOperate) {
        this.readerCardOperate = readerCardOperate;
    }

    @RequestMapping("allreaders.html")
    public ModelAndView allBooks(){
        ArrayList<ReaderInfo> readers= (ArrayList<ReaderInfo>) readerOperate.readerInfos();
        ModelAndView modelAndView=new ModelAndView("admin_readers");
        modelAndView.addObject("readers",readers);
        return modelAndView;
    }

    @RequestMapping("reader_delete.html")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes){
        int readerId= Integer.parseInt(request.getParameter("readerId"));
        boolean success=readerOperate.deleteReaderInfo(readerId);

        if(success){
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
            return "redirect:/allreaders.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
            return "redirect:/allreaders.html";
        }

    }
    @RequestMapping("/reader_info.html")
    public ModelAndView toReaderInfo(HttpServletRequest request) {
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo=readerOperate.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView=new ModelAndView("reader_info");
        modelAndView.addObject("readerinfo",readerInfo);
        return modelAndView;
    }
    @RequestMapping("reader_edit.html")
    public ModelAndView readerInfoEdit(HttpServletRequest request){
        int readerId= Integer.parseInt(request.getParameter("readerId"));
        ReaderInfo readerInfo=readerOperate.getReaderInfo(readerId);
        ModelAndView modelAndView=new ModelAndView("admin_reader_edit");
        modelAndView.addObject("readerInfo",readerInfo);
        return modelAndView;
    }

    @RequestMapping("reader_edit_do.html")
    public String readerInfoEditDo(HttpServletRequest request, String name, String sex, String birth, String address, String telcode, RedirectAttributes redirectAttributes){
        int readerId= Integer.parseInt(request.getParameter("id"));
        ReaderCard readerCard = loginOperate.findReaderCardByUserId(readerId);
        String oldName=readerCard.getName();
        if(!oldName.equals(name)){
            boolean succo=readerCardOperate.updateName(readerId,name);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }
            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(address);
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerId);
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(sex);
            boolean succ=readerOperate.editReaderInfo(readerInfo);
            if(succo&&succ){
                redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
                return "redirect:/allreaders.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
                return "redirect:/allreaders.html";
            }
        }
        else {
            System.out.println("部分修改");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }
            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(address);
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerId);
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(sex);

            boolean succ=readerOperate.editReaderInfo(readerInfo);
            if(succ){
                redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
                return "redirect:/allreaders.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
                return "redirect:/allreaders.html";
            }
        }

    }

    @RequestMapping("reader_add.html")
    public ModelAndView readerInfoAdd(){
        ModelAndView modelAndView=new ModelAndView("admin_reader_add");
        return modelAndView;

    }
    //用户功能--进入修改密码页面
    @RequestMapping("reader_repasswd.html")
    public ModelAndView readerRePasswd(){
        ModelAndView modelAndView=new ModelAndView("reader_repasswd");
        return modelAndView;
    }
    //用户功能--修改密码执行
    @RequestMapping("reader_repasswd_do.html")
    public String readerRePasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        int readerId=readerCard.getReaderId();
        String passwd=readerCard.getPasswd();

        if (newPasswd.equals(reNewPasswd)){
            if(passwd.equals(oldPasswd)){
                boolean succ=readerCardOperate.updatePasswd(readerId,newPasswd);
                if (succ){
                    ReaderCard readerCardNew = loginOperate.findReaderCardByUserId(readerId);
                    request.getSession().setAttribute("readercard", readerCardNew);
                    redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                    return "redirect:/reader_repasswd.html";
                }else {
                    redirectAttributes.addFlashAttribute("succ", "密码修改失败！");
                    return "redirect:/reader_repasswd.html";
                }

            }else {
                redirectAttributes.addFlashAttribute("error", "修改失败,原密码错误");
                return "redirect:/reader_repasswd.html";
            }
        }else {
            redirectAttributes.addFlashAttribute("error", "修改失败,两次输入的新密码不相同");
            return "redirect:/reader_repasswd.html";
        }






    }
    //管理员功能--读者信息添加
    @RequestMapping("reader_add_do.html")
    public String readerInfoAddDo(String name, String sex, String birth, String address, String telcode, int readerId, RedirectAttributes redirectAttributes){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date nbirth=new Date();
        try{
            Date date=sdf.parse(birth);
            nbirth=date;
        }catch (ParseException e){
            e.printStackTrace();
        }

        ReaderInfo readerInfo=new ReaderInfo();
        readerInfo.setAddress(address);
        readerInfo.setBirth(nbirth);
        readerInfo.setName(name);
        readerInfo.setReaderId(readerId);
        readerInfo.setTelcode(telcode);
        readerInfo.setSex(sex);
        boolean succ=readerOperate.addReaderInfo(readerInfo);
        boolean succc=readerCardOperate.addReaderCard(readerInfo);
        ArrayList<ReaderInfo> readers= (ArrayList<ReaderInfo>) readerOperate.readerInfos();
        if (succ&&succc){
            redirectAttributes.addFlashAttribute("succ", "添加读者信息成功！");
            return "redirect:/allreaders.html";
        }else {
            redirectAttributes.addFlashAttribute("succ", "添加读者信息失败！");
            return "redirect:/allreaders.html";
        }
    }
//读者功能--读者信息修改
    @RequestMapping("reader_info_edit.html")
    public ModelAndView readerInfoEditReader(HttpServletRequest request){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo=readerOperate.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView=new ModelAndView("reader_info_edit");
        modelAndView.addObject("readerinfo",readerInfo);
        return modelAndView;

    }
    @RequestMapping("reader_edit_do_r.html")
    public String readerInfoEditDoReader(HttpServletRequest request, String name, String sex, String birth, String address, String telcode, RedirectAttributes redirectAttributes){
        ReaderCard readerCard=(ReaderCard) request.getSession().getAttribute("readercard");
        if (!readerCard.getName().equals(name)){
            boolean succo=readerCardOperate.updateName(readerCard.getReaderId(),name);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }

            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(address);
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerCard.getReaderId());
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(sex);

            boolean succ=readerOperate.editReaderInfo(readerInfo);
            if(succ&&succo){
                ReaderCard readerCardNew = loginOperate.findReaderCardByUserId(readerCard.getReaderId());
                request.getSession().setAttribute("readercard", readerCardNew);
                redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
                return "redirect:/reader_info.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "信息修改失败！");
                return "redirect:/reader_info.html";
            }



        }else {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date nbirth=new Date();
            try{
                Date date=sdf.parse(birth);
                nbirth=date;
            }catch (ParseException e){
                e.printStackTrace();
            }

            ReaderInfo readerInfo=new ReaderInfo();
            readerInfo.setAddress(address);
            readerInfo.setBirth(nbirth);
            readerInfo.setName(name);
            readerInfo.setReaderId(readerCard.getReaderId());
            readerInfo.setTelcode(telcode);
            readerInfo.setSex(sex);

            boolean succ=readerOperate.editReaderInfo(readerInfo);
            if(succ){
                ReaderCard readerCardNew = loginOperate.findReaderCardByUserId(readerCard.getReaderId());
                request.getSession().setAttribute("readercard", readerCardNew);
                redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
                return "redirect:/reader_info.html";
            }else {
                redirectAttributes.addFlashAttribute("error", "信息修改失败！");
                return "redirect:/reader_info.html";
            }
        }



    }
}
