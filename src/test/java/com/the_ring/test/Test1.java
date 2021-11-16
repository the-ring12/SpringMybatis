package com.the_ring.test;

import com.the_ring.dao.AdminDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class Test1 {

    private  AdminDao adminDao;

    @Before
    public void before() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
       adminDao = (AdminDao) ac.getBean("adminDao");
    }

    @Test
    public void test() {
        int matchCount = adminDao.getMatchCount(20170001, "111111");
        System.out.println(matchCount);
    }
}
