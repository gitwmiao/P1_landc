package com.bwf.p1_landz.entity;

import com.bwf.framework.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class Shuosuoshuju extends BaseBean{
    public List<SearchKeyWordDetail> result;

    public class SearchKeyWordDetail
    {
        /** 楼盘或商圈id */
        public String id;
        /** 楼盘或商圈名称 */
        public String name;
        /** 0.二手楼盘1.一手楼盘，2商圈 */
        public int type;
    }
}
