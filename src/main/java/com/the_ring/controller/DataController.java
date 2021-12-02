package com.the_ring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataController {

    @RequestMapping("/data.html")
    public String getData() {

        return "data";
    }
}
