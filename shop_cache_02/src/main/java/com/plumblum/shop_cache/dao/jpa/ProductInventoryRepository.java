package com.plumblum.shop_cache.dao.jpa;

import com.plumblum.shop_cache.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: cpb
 * @Date: 2018/11/8 18:31
 * @Description:
 */
public interface ProductInventoryRepository extends JpaRepository<ProductInventory,Integer> {

    public ProductInventory getByProductId(Integer id);
}
