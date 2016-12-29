package com.bwf.p1_landz.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/6.
 */
public class HouseOneArrBean implements Serializable{
    public String resblockOneId;
    public String houseOneId;
    public String resblockOneName;//房源名称  东山公寓
    public String bedroomAmount;// 室
    public String parlorAmount;//厅
    public String buildSize;//豪宅大小：310平
    public String totalprBegin;//价格区间的开始
    public String totalprEnd;//价格区间的结束  如：2800-3500万
    public String titlepicImg;//豪宅图片地址
    public String circleTypeName;//划分类型 ： 朝阳公园
    public String resblockType;//筛选类型： 公寓
    public String totalShowing;//总看房次数
    public String sortNum;//排序的num
    public String unitprBegin;
    public String unitprEnd;
    public String totalNumber;
    public boolean isSelect;

    @Override
    public String toString() {
        return "HouseOneArrBean{" +
                "resblockOneId='" + resblockOneId + '\'' +
                ", houseOneId='" + houseOneId + '\'' +
                ", resblockOneName='" + resblockOneName + '\'' +
                ", bedroomAmount='" + bedroomAmount + '\'' +
                ", parlorAmount='" + parlorAmount + '\'' +
                ", buildSize='" + buildSize + '\'' +
                ", totalprBegin='" + totalprBegin + '\'' +
                ", totalprEnd='" + totalprEnd + '\'' +
                ", titlepicImg='" + titlepicImg + '\'' +
                ", circleTypeName='" + circleTypeName + '\'' +
                ", resblockType='" + resblockType + '\'' +
                ", totalShowing='" + totalShowing + '\'' +
                ", sortNum='" + sortNum + '\'' +
                ", unitprBegin='" + unitprBegin + '\'' +
                ", unitprEnd='" + unitprEnd + '\'' +
                ", totalNumber='" + totalNumber + '\'' +
                '}';
    }
}
