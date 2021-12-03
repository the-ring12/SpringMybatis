package com.the_ring.hbase;

import com.the_ring.hbase.entity.PageCount;

import java.io.IOException;
import java.util.List;

public class HBaseTest {

    public static void main(String[] args) throws IOException {
        HBaseRead hBaseRead = new HBaseRead();
        List<PageCount> allCount = hBaseRead.getAllCount();
        for (PageCount pageCount : allCount) {
            System.out.println(pageCount);
        }

        int count = hBaseRead.getCount("http://localhost:8080/SpringMybatis_war_exploded/");
        System.out.println(count);
    }
}
