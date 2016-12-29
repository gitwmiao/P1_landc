package com.bwf.p1_landz.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */

public class OnLineHouseResult implements Serializable{
    public List<HouseArrBean> houseArr;
    public List<HouseOneArrBean> houseOneArr;

    @Override
    public String toString() {
        return "OnLineHouseResult{" +
                "houseArr=" + houseArr +
                ", houseOneArr=" + houseOneArr +
                '}';
    }
}
