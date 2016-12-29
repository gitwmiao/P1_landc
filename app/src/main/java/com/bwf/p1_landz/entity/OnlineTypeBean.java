package com.bwf.p1_landz.entity;

import com.bwf.framework.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public class OnlineTypeBean extends BaseBean {
    public List<OnLineTypeResultBean> result;

    @Override
    public String toString() {
        return "OnlineTypeBean{" +
                "result=" + result +
                '}';
    }
}

