package com.ff.common.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import java.util.Properties;

/**
 * @Author yuzhongxin
 * @Description
 * @Create 2017-07-17 10:10
 **/
public class PropertiesUtil {
    private String fileName;

    public PropertiesUtil(String fileName) {
        this.fileName = fileName;
    }

    public String readProperty(String name) {
        Resource res = new ClassPathResource(fileName);
        Properties p = new Properties();
        try {
            p.load(res.getInputStream());
            // System.out.println(p.getProperty(name));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return p.getProperty(name);

    }
}
