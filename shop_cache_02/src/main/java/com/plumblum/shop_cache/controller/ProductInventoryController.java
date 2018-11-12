package com.plumblum.shop_cache.controller;

import com.plumblum.shop_cache.Utils.Response;
import com.plumblum.shop_cache.entity.ProductInventory;
import com.plumblum.shop_cache.request.ProductInventoryCacheRefreshRequest;
import com.plumblum.shop_cache.request.ProductInventoryDBUpdateRequest;
import com.plumblum.shop_cache.request.Request;
import com.plumblum.shop_cache.service.ProductInventoryService;
import com.plumblum.shop_cache.service.RequestAsyncProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: cpb
 * @Date: 2018/11/9 08:55
 * @Description:
 */
@Controller
public class ProductInventoryController {

    @Autowired
    private ProductInventoryService productInventoryService;

    @Autowired
    private RequestAsyncProcessService  requestAsyncProcessService;

    /**
     * 更新商品库存
     */
    @ResponseBody
    @RequestMapping("/updateProductInventory")
    public Response updateProductInventory(ProductInventory productInventory){
        System.out.println("===========日志===========: 接收到更新商品库存的请求，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
        Response response = null;
        try {
            Request request = new ProductInventoryDBUpdateRequest(productInventory,productInventoryService);
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            response = new Response(Response.FAILURE);
        }
        return response;
    }


    /**
     * 获取商品库存
     */
    @RequestMapping("/getProductInventory")
    @ResponseBody
    public ProductInventory getProductInventory(Integer productId) {
        System.out.println("===========日志===========: 接收到一个商品库存的读请求，商品id=" + productId);

        ProductInventory productInventory = null;
        try {
            Request request = new ProductInventoryCacheRefreshRequest(productId,productInventoryService);
            requestAsyncProcessService.process(request);
            // 将请求扔给service异步去处理以后，就需要while(true)一会儿，在这里hang住
            // 去尝试等待前面有商品库存更新的操作，同时缓存刷新的操作，将最新的数据刷新到缓存中
            long startTime = System.currentTimeMillis();
            long endTime = 0L;
            long waitTime = 0L;

            // 等待超过200ms没有从缓存中获取到结果
            while(true) {
//				if(waitTime > 25000) {
//					break;
//				}

                // 一般公司里面，面向用户的读请求控制在200ms就可以了
                if(waitTime > 200) {
                    break;
                }

                // 尝试去redis中读取一次商品库存的缓存数据
                productInventory = productInventoryService.getProductInventoryCache(productId);
                if(productInventory!=null){
                    System.out.println("===========日志===========: 在200ms内读取到了redis中的库存缓存，商品id=" + productInventory.getProductId() + ", 商品库存数量=" + productInventory.getInventoryCnt());
                    return  productInventory;
                }
                else {
                    // 如果没有读取到结果，那么等待一段时间
                    Thread.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }



            }
            // (如果缓存读不到)直接尝试从数据库中读取数据
            productInventory = productInventoryService.findProductInventory(productId);
            if(productInventory != null) {
                // 将缓存刷新一下
                productInventoryService.setProductInventoryCache(productInventory);
                return productInventory;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ProductInventory(productId, -1L);
    }
}
