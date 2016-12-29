package com.bwf.framework.http;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/28.
 */

public class Request {
    private  String url;
    private int REQUEST_TIME_OUT =10000;
    private Method method;
    private Map<String,String> params;
    public Request(String url,int timeout,Method method,Map<String,String> map){
        this.url=url;
        this.method=method;
        this.REQUEST_TIME_OUT=timeout;
        this.params=map;
    }
    public Request(String url,Method method,Map<String,String> map){
        this.params=map;
        this.url=url;
        this.method=method;
    }
    public String getUrl(){
        return url;

    }
    public int getRequestTimeOut(){
        return REQUEST_TIME_OUT;
    }
    public Method getMethod(){
        return method;
    }
    public Map<String,String> getParams(){
        return params;
    }

    public enum Method{
        GET("GET"),POST("POST");
        private String methed;

        Method(String methed) {
            this.methed=methed;
        }
        public String getMethed(){
            return methed;
        }
    }


}
