package com.bwf.p1_landz.entity;

import com.bwf.framework.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MoreOneHuose extends BaseBean implements Serializable{

    public List<ResultOneHouse> result ;

    @Override
    public String toString() {
        return "MoreOneHuose{" +
                "resultStatus='" + resultStatus + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", result=" + result +
                '}';
    }
}
