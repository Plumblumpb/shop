package com.plumblum.shop_cache.service;

import com.plumblum.shop_cache.request.ProductInventoryCacheRefreshRequest;
import com.plumblum.shop_cache.request.ProductInventoryDBUpdateRequest;
import com.plumblum.shop_cache.request.Request;
import com.plumblum.shop_cache.request.RequestQueue;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Auther: cpb
 * @Date: 2018/11/9 09:22
 * @Description:
 */
@Service("requestAsyncProcessService")
public class RequestAsyncProcessService {
    public void process(Request request) {
        // 先做读请求的去重
        RequestQueue requestQueue = RequestQueue.getInstance();
        Map<Integer,Boolean> flagMap = requestQueue.getFlagMap();
        try {

            if(request instanceof ProductInventoryDBUpdateRequest){
//          删除缓存
                flagMap.put(request.getProductId(),false);
            }else if(request instanceof ProductInventoryCacheRefreshRequest){
//          获取数据
                Boolean flag = flagMap.get(request.getProductId());
                if(flag == null){
                    flagMap.put(request.getProductId(),false);
                }
                // 如果是缓存刷新的请求，那么就判断，如果标识不为空，而且是true，就说明之前有一个这个商品的数据库更新请求
                if(flag != null && flag ){
                    flagMap.put(request.getProductId(),false);
                }

                // 如果是缓存刷新的请求，而且发现标识不为空，但是标识是false
                // 说明前面已经有一个数据库更新请求+一个缓存刷新请求了
                if(flag != null && !flag) {
                    // 对于这种读请求，直接就过滤掉，不要放到后面的内存队列里面去了
                    return;
                }
                // 做请求的路由，根据每个请求的商品id，路由到对应的内存队列中去
                ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
                // 将请求放入对应的队列中，完成路由操作
                queue.put(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取路由到的内存队列
     * @param productId 商品id
     * @return 内存队列
     */
    private ArrayBlockingQueue<Request> getRoutingQueue(Integer productId) {
        RequestQueue requestQueue = RequestQueue.getInstance();

        // 先获取productId的hash值
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        // 对hash值取模，将hash值路由到指定的内存队列中，比如内存队列大小8
        // 用内存队列的数量对hash值取模之后，结果一定是在0~7之间
        // 所以任何一个商品id都会被固定路由到同样的一个内存队列中去的
        int index = (requestQueue.queueSize() - 1) & hash;

        System.out.println("===========日志===========: 路由内存队列，商品id=" + productId + ", 队列索引=" + index);

        return requestQueue.getQueue(index);
    }
}
