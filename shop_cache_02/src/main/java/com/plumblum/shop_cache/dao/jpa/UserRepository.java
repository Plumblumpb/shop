package com.plumblum.shop_cache.dao.jpa;

import com.plumblum.shop_cache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: cpb
 * @Date: 2018/11/5 10:17
 * @Description:
 */
public interface UserRepository extends JpaRepository<User,String> {

    public User getById(String id);
}
