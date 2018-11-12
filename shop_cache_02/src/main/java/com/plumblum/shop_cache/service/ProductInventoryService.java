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

    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryRepository.getByProductId(productId);
    }

    /**
     * 设置商品库存的缓存
     * @param productInventory 商品库存
     */
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDao.set(key, String.valueOf(productInventory.getInventoryCnt()));
        System.out.println("===========日志===========: 已更新商品库存的缓存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt() + ", key=" + key);
    }

    /**
     * 获取商品库存的缓存
     * @param productId
     * @return
     */
    public ProductInventory getProductInventoryCache(Integer productId) {
        Long inventoryCnt = 0L;

        String key = "product:inventory:" + productId;
        String result = redisDao.get(key);

        if(result != null && !"".equals(result)) {
            try {
                inventoryCnt = Long.valueOf(result);
                return new ProductInventory(productId, inventoryCnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
