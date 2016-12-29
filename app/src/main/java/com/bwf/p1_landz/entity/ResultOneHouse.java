package com.bwf.p1_landz.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/15.
 */

public class ResultOneHouse implements Serializable{
    public String resblockOneId;

    public String houseOneId;

    public String resblockOneName;

    public String bedroomAmount;

    public String parlorAmount;

    public String buildSize;

    public String totalprBegin;

    public String totalprEnd;

    public String titlepicImg;

    public String circleTypeName;

    public String resblockType;

    public String totalShowing;

    public String sortNum;

    public String unitprBegin;

    public String unitprEnd;

    public String totalNumber;


    @Override
    public String toString() {
        return "ResultOneHouse{" +
                "resblockOneId='" + resblockOneId + '\'' +
                ", houseOneId='" + houseOneId + '\'' +
                ", resblockOneName='" + resblockOneName + '\'' +
                ", bedroomAmount=" + bedroomAmount +
                ", parlorAmount=" + parlorAmount +
                ", buildSize=" + buildSize +
                ", totalprBegin=" + totalprBegin +
                ", totalprEnd=" + totalprEnd +
                ", titlepicImg='" + titlepicImg + '\'' +
                ", circleTypeName='" + circleTypeName + '\'' +
                ", resblockType='" + resblockType + '\'' +
                ", totalShowing=" + totalShowing +
                ", sortNum=" + sortNum +
                ", unitprBegin=" + unitprBegin +
                ", unitprEnd=" + unitprEnd +
                ", totalNumber=" + totalNumber +
                '}';
    }
}
