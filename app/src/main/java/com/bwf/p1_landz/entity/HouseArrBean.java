package com.bwf.p1_landz.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/6.
 */
public class HouseArrBean implements Serializable{
    public String housedelId;//房源id
    public String resblockId;//
    public String resblockName;//房源名称  龙湾别墅
    public String circleTypeCode;//划分类型id
    public String circleTypeName;//划分类型  中央别墅区
    public String totalPrices;//总价
    public String bedroomAmount;// 室
    public String parlorAmount;//厅
    public String buildSize;//豪宅大小：310平
    public String houseLabel;
    public String salesTrait;//销售特点： 龙湾别墅  4室2厅
    public String titleImg;//豪宅图片地址
    public String isFocus;
    public String isBasilic;
    public String isKey;
    public String maxCanLookTime;
    public String totalShowing;//总看房次数
    public String score;//分数
    public String orientation;//豪宅方向
    public String usageType;//筛选的类型： 别墅
    public String sortNum;//排序num
    public String totalNumber;
    public boolean isSelect = false;


    @Override
    public String toString() {
        return "HouseArrBean{" +
                "housedelId='" + housedelId + '\'' +
                ", resblockId='" + resblockId + '\'' +
                ", resblockName='" + resblockName + '\'' +
                ", circleTypeCode='" + circleTypeCode + '\'' +
                ", circleTypeName='" + circleTypeName + '\'' +
                ", totalPrices='" + totalPrices + '\'' +
                ", bedroomAmount='" + bedroomAmount + '\'' +
                ", parlorAmount='" + parlorAmount + '\'' +
                ", buildSize='" + buildSize + '\'' +
                ", houseLabel='" + houseLabel + '\'' +
                ", salesTrait='" + salesTrait + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", isFocus='" + isFocus + '\'' +
                ", isBasilic='" + isBasilic + '\'' +
                ", isKey='" + isKey + '\'' +
                ", maxCanLookTime='" + maxCanLookTime + '\'' +
                ", totalShowing='" + totalShowing + '\'' +
                ", score='" + score + '\'' +
                ", orientation='" + orientation + '\'' +
                ", usageType='" + usageType + '\'' +
                ", sortNum='" + sortNum + '\'' +
                ", totalNumber='" + totalNumber + '\'' +
                '}';
    }
}
