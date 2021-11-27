package com.the_ring.util;

import com.the_ring.kafka.Producer;
import com.the_ring.kafka.entity.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class PageMessage {

    public static void getMessage(HttpServletRequest request) {
        String ip = IPUtil.getIpAddr(request);
        String URL = request.getRequestURL().toString();
        Page page = new Page(ip, URL, new Date());
        Producer producer = new Producer(page);
        producer.run();
    }
}
