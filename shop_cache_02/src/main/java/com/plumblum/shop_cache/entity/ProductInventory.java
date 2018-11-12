package com.plumblum.shop_cache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Auther: cpb
 * @Date: 2018/11/8 17:28
 * @Description:
 */
@Entity
@Table(name = "PRODUCT_INVENTORY", schema = "shop_cache", catalog = "")
public class ProductInventory {

    /**
     * 商品id
     */

    @Id
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    /**
     * 库存数量
     */
    @Column(name = "INVENTORY_CNT")
    private Long inventoryCnt;

    public ProductInventory(Integer productId,Long inventoryCnt){
        this.inventoryCnt = inventoryCnt;
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getInventoryCnt() {
        return inventoryCnt;
    }

    public void setInventoryCnt(Long inventoryCnt) {
        this.inventoryCnt = inventoryCnt;
    }
}
