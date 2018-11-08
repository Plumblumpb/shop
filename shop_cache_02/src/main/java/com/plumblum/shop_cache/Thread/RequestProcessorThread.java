package com.plumblum.shop_cache.Thread;

import com.plumblum.shop_cache.request.Request;
import com.plumblum.shop_cache.request.RequestQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @Auther: cpb
 * @Date: 2018/11/8 17:12
 * @Description:执行请求的工作线程
 */
public class RequestProcessorThread implements Callable<Boolean> {

    /**
     * 监控队列
     */
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue){
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            // ArrayBlockingQueue
            // Blocking就是说明，如果队列满了，或者是空的，那么都会在执行操作的时候，阻塞住
            Request request = queue.take();
//          执行操作
            request.process();
        }catch(Exception e){
         e.printStackTrace();
        }
        return true;
    }
}
