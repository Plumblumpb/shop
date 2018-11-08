package com.plumblum.shop_cache.Thread;

import com.plumblum.shop_cache.request.Request;
import com.plumblum.shop_cache.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: cpb
 * @Date: 2018/11/8 08:59
 * @Description:请求处理线程池：单例
 */
public class RequestProcessorThreadPool {
    private static  class Singleton{
        private static  RequestProcessorThreadPool instance;

        static {
            instance = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance() {
            return instance;
        }

    }

    public static RequestProcessorThreadPool getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 初始化的便捷方法
     */
    public static void init() {
        getInstance();
    }


    /**
     * 线程池
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public RequestProcessorThreadPool(){
        RequestQueue requestQueue = RequestQueue.getInstance();

        for(int i = 0;i<10;i++){
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
            requestQueue.add(queue);
//          执行操作
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }


}
