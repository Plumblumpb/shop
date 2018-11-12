package com.plumblum.shop_cache.request;

import com.plumblum.shop_cache.entity.ProductInventory;
import com.plumblum.shop_cache.service.ProductInventoryService;

/**
 * @Auther: cpb
 * @Date: 2018/11/9 08:40
 * @Description:重新加载库存商品缓存
 *  从数据库加载最新的数据， 加载到redis缓存
 */
public class ProductInventoryCacheRefreshRequest implements  Request{
    /**
     * 商品id
     */

    private Integer productId;

    private ProductInventoryService productInventoryService;

    public ProductInventoryCacheRefreshRequest(Integer productId,ProductInventoryService productInventoryService){
        this.productId = productId;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
//   加载数据库数据
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        System.out.println("===========日志===========: 已查询到商品最新的库存数量，商品id=" + productId + ", 商品库存数量=" + productInventory.getInventoryCnt());
        // 将最新的商品库存数量，刷新到redis缓存中去
        productInventoryService.setProductInventoryCache(productInventory);

    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
