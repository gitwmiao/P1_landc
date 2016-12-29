package com.bwf.p1_landz.iu.onevilla;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.p1_landz.MyApplication;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.OnLineTypeResultBean;
import com.bwf.p1_landz.entity.OnlineTypeBean;
import com.bwf.p1_landz.entity.ParamListBean;
import com.bwf.p1_landz.iu.view.PupBuiding;

import com.bwf.p1_landz.iu.view.TitlePopupWindow;
import com.bwf.p1_landz.request.OnLineHouseRequest;

import java.util.List;

/**
 * Created by Administrator on 2016/12/4.
 */

public class OnBuidingAct extends BaseActivity{
    private TextView tv_location, tv_price, tv_room, tv_type, tv_more;
    private RelativeLayout rl_location, rl_price, rl_room, rl_type, rl_more;
    //选择筛选的数据
    private OnlineTypeBean onlineTypeBean;
    private EditText et_two_search;
    // 地区、价格、居室、类型
    private List<ParamListBean> location_paramList,price_paramList,room_paramList,type_paramList;

    //保存筛选请求的内容
    private OnLineHouseRequest onLineHouseRequest;
    @Override
    public int getContentViewId() {
        return R.layout.activity_on_line_title;
    }

    @Override
    public void beforInitView() {

        //获得筛选的数据
        onlineTypeBean = MyApplication.getApplication().getOnlineTypeBean();
        //进行分类
        if(onlineTypeBean != null){
            for(OnLineTypeResultBean onLineTypeResultBean : onlineTypeBean.result){
                if(onLineTypeResultBean.paramType.equals("1001")){//地区
                    location_paramList = onLineTypeResultBean.paramList;
                }
                if(onLineTypeResultBean.paramType.equals("1003")){//价格
                    price_paramList = onLineTypeResultBean.paramList;
                }
                if(onLineTypeResultBean.paramType.equals("1005")){//居室
                    room_paramList = onLineTypeResultBean.paramList;
                }
                if(onLineTypeResultBean.paramType.equals("1006")){//类型
                    type_paramList = onLineTypeResultBean.paramList;
                }
            }
        }
    }

    @Override
    public void initView() {
        tv_location = findViewByIdNoCast(R.id.tv_location);
        tv_price = findViewByIdNoCast(R.id.tv_price);
        tv_room = findViewByIdNoCast(R.id.tv_room);
        tv_type = findViewByIdNoCast(R.id.tv_type);
        tv_more = findViewByIdNoCast(R.id.tv_more);
        rl_location = findViewByIdNoCast(R.id.rl_location);
        rl_price = findViewByIdNoCast(R.id.rl_price);
        rl_room = findViewByIdNoCast(R.id.rl_room);
        rl_type = findViewByIdNoCast(R.id.rl_type);
        rl_more = findViewByIdNoCast(R.id.rl_more);
        et_two_search=findViewByIdNoCast(R.id.et_two_search);
    }

    @Override
    public void initData() {
        //设置监听
        setOnClick(rl_location, rl_price, rl_room, rl_type, rl_more);

        onLineHouseRequest = new OnLineHouseRequest();
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_location://区域
                currentPost = 0;
                PupBuiding pou =new PupBuiding(this,true);
                pou.setParamListBean(location_paramList,pTsCallBack);
                pou.showAsDropDown(rl_location);
                break;
            case R.id.rl_price://价格
                currentPost = 1;
                PupBuiding pou1 =new PupBuiding(this,true);
                pou1.setParamListBean(price_paramList,pTsCallBack);

                //设置自定义View
                pou1.setPrice_zidingyuView(this,pTsCallBack);
                pou1.showAsDropDown(rl_location);
                break;
            case R.id.rl_room://
                currentPost = 2;
                PupBuiding pou2 =new PupBuiding(this,true);
                pou2.setParamListBean(room_paramList,pTsCallBack);
                pou2.showAsDropDown(rl_location);
                break;
            case R.id.rl_type://
                currentPost = 3;
                PupBuiding pou3 =new PupBuiding(this,true);
                pou3.setParamListBean(type_paramList,pTsCallBack);
                pou3.showAsDropDown(rl_location);
                break;
            case R.id.rl_more://更多
                break;
        }
    }

    int currentPost = 0;//存储点击的哪个pupop

    private PupBuiding.PupupWindowItemClickCallBack  pTsCallBack = new PupBuiding.PupupWindowItemClickCallBack() {
        @Override
        public void onItemClickCallBack(ParamListBean paramListBean) {
            if(paramListBean == null){
                return;
            }
            switch(currentPost){
                case 0://区域
                    tv_location.setText(""+paramListBean.name);
                    onLineHouseRequest.circleTypeCode = paramListBean.key;
                    break;
                case 1://价格
                    tv_price.setText(""+paramListBean.name);
                    onLineHouseRequest.totalPricesBegin = paramListBean.minValue;
                    onLineHouseRequest.totalPricesEnd = paramListBean.maxValue;
                    break;
                case 2://居室
                    tv_room.setText(""+paramListBean.name);
                    onLineHouseRequest.bedroomAmount = paramListBean.value;
                    break;
                case 3://类型
                    tv_type.setText(""+paramListBean.name);
                    onLineHouseRequest.buildingType = paramListBean.name;
                    break;
            }
        }
    };
}
