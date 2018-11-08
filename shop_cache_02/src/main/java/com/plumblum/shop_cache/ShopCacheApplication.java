package com.plumblum.shop_cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.plumblum.shop_cache.dao.mybatis")
@EnableCaching
public class ShopCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopCacheApplication.class, args);
    }
}
