package com.bwf.p1_landz.entity;

import com.bwf.framework.base.BaseBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by Administrator on 2016/12/6.
 */

public class OnLineAnd extends BaseBean{
    public OnLineHouseResult result;

    public List<Object> inlistData(){
        if (this!=null){
            List<HouseArrBean> houseArrBeen =this.result.houseArr;
            List<HouseOneArrBean> houseOneArrBeen =this.result.houseOneArr;


            List<Object> newTotalList = new ArrayList<>();

            if (houseArrBeen != null && !houseArrBeen.isEmpty())
                newTotalList.addAll(houseArrBeen);
            if (houseOneArrBeen != null && !houseOneArrBeen.isEmpty())
                newTotalList.addAll(houseOneArrBeen);

            Collections.sort(newTotalList, new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {

                    int a, b = 0;

                    if (o1 instanceof HouseArrBean)
                        a = Integer.parseInt(((HouseArrBean) o1).sortNum);
                    else
                        a = Integer.parseInt(((HouseOneArrBean) o1).sortNum);

                    if (o2 instanceof HouseArrBean)
                        b = Integer.parseInt(((HouseArrBean) o2).sortNum);
                    else
                        b = Integer.parseInt(((HouseOneArrBean) o2).sortNum);

                    if (a > b)//前面大于后面的返回正数
                        return 1;
                    if (a < b)//前面小于后面的返回负数
                        return -1;

                    return 0;
                }
            });
            return  newTotalList;
        }
        return  null;
    }
}
