package com.bwf.framework.base;

/**
 * Created by Administrator on 2016/11/28.
 */

public class BaseBean<T> {
    public static Class<? extends BaseBean> calss;
    public String resultStatus;//接口返回码 200表示请求成功，其他表示失败

    public String resultMsg;//返回msg

    @Override
    public String toString() {
        return "BaseBean{" +
                "resultStatus='" + resultStatus + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }
}
