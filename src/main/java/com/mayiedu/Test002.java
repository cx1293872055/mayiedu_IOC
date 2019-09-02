package com.mayiedu;

import com.mayiedu.xml.spring.ExtClassPathXmlApplicationContext;

public class Test002 {

    public static void main(String[] args) throws Exception {
        ExtClassPathXmlApplicationContext app = new ExtClassPathXmlApplicationContext("spring.xml");
        Test001 bean = (Test001) app.getBean("Test001");
        bean.add();
        System.out.println(bean);
    }
}
