package com.plumblum.shop_cache.Utils;

/**
 * @Auther: cpb
 * @Date: 2018/11/9 09:01
 * @Description:请求的响应
 */
public class Response {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    private String status;
    private String message;
    public Response() {

    }
    public Response(String status) {
        this.status = status;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }
}
