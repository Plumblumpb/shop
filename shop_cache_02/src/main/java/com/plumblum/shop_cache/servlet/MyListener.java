package com.plumblum.shop_cache.servlet;

import com.plumblum.shop_cache.Thread.RequestProcessorThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * @Auther: cpb
 * @Date: 2018/11/6 10:18
 * @Description:
 */
@Configuration
@ServletComponentScan("com.plumblum.shop_cache.servlet")
public class MyListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(MyListener.class);


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 初始化工作线程池和内存队列
        RequestProcessorThreadPool.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


}
