package com.plumblum.shop_cache.UserTest;

import com.plumblum.shop_cache.dao.jpa.UserRepository;
import com.plumblum.shop_cache.dao.mybatis.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: cpb
 * @Date: 2018/11/5 10:25
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getOne(){
        userDao.getOne("1");
        userRepository.getById("1");
    }
}
