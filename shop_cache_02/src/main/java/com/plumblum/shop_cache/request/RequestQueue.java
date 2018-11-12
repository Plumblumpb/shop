package com.plumblum.shop_cache.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: cpb
 * @Date: 2018/11/8 09:12
 * @Description:包装内存队列，作用：去重
 */
public class RequestQueue {

    private static class Singleton{
        private static RequestQueue instance ;
        static {
            instance = new RequestQueue();
        }

        public static RequestQueue getInstance(){
            return  instance;
        }
    }

    public static RequestQueue getInstance(){
        return Singleton.instance;
    }




    /**
     * 内存队列
     */
    private List<ArrayBlockingQueue<Request>> list = new ArrayList<>();

    /**
     * 标识位map
     */
    private Map<Integer, Boolean> flagMap = new ConcurrentHashMap<Integer, Boolean>();


    /**
     * 添加到内存队列
     */

    public void add(ArrayBlockingQueue<Request> queue){
        this.list.add(queue);
    }

    /**
     * 返回内存队列长度
     */
    public int queueSize() {
        return list.size();
    }

    /**
     * 获取内存队列
     * @param index
     * @return
     */
    public ArrayBlockingQueue<Request> getQueue(int index) {
        return list.get(index);
    }
    /**
     * setter和getter
     * @return
     */

    public List<ArrayBlockingQueue<Request>> getList() {
        return list;
    }

    public void setList(List<ArrayBlockingQueue<Request>> list) {
        this.list = list;
    }

    public Map<Integer, Boolean> getFlagMap() {
        return flagMap;
    }

    public void setFlagMap(Map<Integer, Boolean> flagMap) {
        this.flagMap = flagMap;
    }
}
