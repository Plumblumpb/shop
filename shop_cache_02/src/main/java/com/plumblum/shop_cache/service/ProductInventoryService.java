package com.plumblum.shop_cache.service;

import com.plumblum.shop_cache.dao.RedisDao;
import com.plumblum.shop_cache.dao.jpa.ProductInventoryRepository;
import com.plumblum.shop_cache.entity.ProductInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: cpb
 * @Date: 2018/11/8 17:29
 * @Description:
 */
@Service("productInventoryService")
public class ProductInventoryService {
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDao.delete(key);
        System.out.println("===========日志===========: 已删除redis中的缓存，key=" + key);
    }


    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryRepository.save(productInventory);
        System.out.println("===========日志===========: 已修改数据库中的库存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
    }
}
