package com.the_ring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.the_ring.hbase.HBaseRead;
import com.the_ring.hbase.entity.PageCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    private HBaseRead hBaseRead;

    @RequestMapping("/data.html")
    public String getData() {

        return "data";
    }

    @RequestMapping("/getData.html")
    public ModelAndView getShowData() throws IOException {
        List<PageCount> counts = hBaseRead.getAllCount();
        String res = new ObjectMapper().writeValueAsString(counts);
        System.out.println(res);
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        mv.addObject(counts);
        return mv;
    }
}
