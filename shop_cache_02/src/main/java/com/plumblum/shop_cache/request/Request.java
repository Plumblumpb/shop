package com.plumblum.shop_cache.request;

/**
 * 请求接口
 * @author Administrator
 *
 */
public interface Request {
	/**
	 * 执行操作
	 */
	void process();

	/**
	 * 返回商品id
	 * @return
	 */
	Integer getProductId();
	
}
