package com.bwf.p1_landz.iu.onlinevilla;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.framework.http.HttpRequestAsyncTask;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.MyApplication;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseArrBean;
import com.bwf.p1_landz.entity.HouseOneArrBean;
import com.bwf.p1_landz.entity.OnLineAnd;
import com.bwf.p1_landz.entity.OnLineHouseResult;
import com.bwf.p1_landz.entity.OnLineTypeResultBean;
import com.bwf.p1_landz.entity.OnlineTypeBean;
import com.bwf.p1_landz.entity.ParamListBean;
import com.bwf.p1_landz.iu.onlinevilla.adapter.OnlineAdater;
import com.bwf.p1_landz.iu.view.ListViewll;
import com.bwf.p1_landz.iu.view.TitlePopupWindow;
import com.bwf.p1_landz.request.OnLineHouseRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */

public class OnlineBuildActivity extends BaseActivity{
    private TextView tv_location, tv_price, tv_room, tv_type, tv_more;
    private RelativeLayout rl_location, rl_price, rl_room, rl_type, rl_more;
    //选择筛选的数据
    private OnlineTypeBean onlineTypeBean;
    // 地区、价格、居室、类型
    private List<ParamListBean> location_paramList,price_paramList,room_paramList,type_paramList;

    //保存筛选请求的内容
    private OnLineHouseRequest onLineHouseRequest;
    private ListViewll on_list;
    private RelativeLayout  zai_no_house;
    private OnLineTypeResultBean onLineTypeResultBean;
    private List<Object> andlist;
    private OnlineAdater adater;
    private ImageView compare_type_btn,compare_btn;
    @Override
    public int getContentViewId() {
        return R.layout.activity_on_line_title;
    }

    @Override
    public void beforInitView() {

        onlineTypeBean = MyApplication.getApplication().getOnlineTypeBean();
        //进行分类
        if(onlineTypeBean != null){
            for(OnLineTypeResultBean onLineTypeResultBean : onlineTypeBean.result){
                if(onLineTypeResultBean.paramType.equals("1001")){//地区
                    location_paramList = onLineTypeResultBean.paramList;
                }
                if(onLineTypeResultBean.paramType.equals("1008")){//价格
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
        on_list=findViewByIdNoCast(R.id.on_list);
        zai_no_house=findViewByIdNoCast(R.id.zai_no_house);
        compare_type_btn =findViewByIdNoCast(R.id.compare_type_btn);
        compare_type_btn.setTag("dianji");
        compare_btn =findViewByIdNoCast(R.id.compare_btn);
    }

    @Override
    public void initData() {
        //设置监听
        setOnClick(rl_location, rl_price, rl_room, rl_type, rl_more,compare_type_btn,compare_btn);

        onLineHouseRequest = new OnLineHouseRequest();
        onLineHouseRequest.resblockName = getIntent().getStringExtra("resblockName");
        onLineHouseRequest.circleTypeCode = getIntent().getStringExtra("circleTypeCode");
        if(!TextUtils.isEmpty(onLineHouseRequest.resblockName)){
            tv_location.setText(""+onLineHouseRequest.resblockName);
        }
        andlist =new ArrayList<>();
        adater =new OnlineAdater(this);
        on_list.setAdapter(adater);
        on_list.setRefreh_listViewListener(new ListViewll.Refreh_ListViewListener() {
            @Override
            public void onRefresh() {
                onLineHouseRequest.pageNo = 0;
                getOnlineShuju();
            }

            @Override
            public void onLoadMore() {
                onLineHouseRequest.pageNo++;
                getOnlineShuju();

            }
        });
        onLineTypeResultBean =new OnLineTypeResultBean();
        on_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(adater.getItem(i-1) instanceof HouseOneArrBean){
                    HouseOneArrBean houseOneArrBean = (HouseOneArrBean) adater.getItem(i-1);
                    Bundle bundle =new Bundle();
                    bundle.putString("houseOneId",houseOneArrBean.houseOneId);
                    bundle.putString("resblockOneId",houseOneArrBean.resblockOneId);
                    IntentUtils.openActivity(OnlineBuildActivity.this,HouseOne.class,bundle);
                }else{
                    HouseArrBean houseArrBean = (HouseArrBean) adater.getItem(i-1);
                    Bundle bundle =new Bundle();
                    bundle.putString("housedelId",houseArrBean.housedelId);
                    bundle.putString("resblockId",houseArrBean.resblockId);
                    IntentUtils.openActivity(OnlineBuildActivity.this,HouseTwo.class,bundle);

                }

            }
        });

    }

    @Override
    public void afterInitView() {
        onLineHouseRequest.pageNo = 0;
        getOnlineShuju();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_location:
                currentPost = 0;
                TitlePopupWindow titlePopupWindow = new TitlePopupWindow(this,false);
                titlePopupWindow.setParamListBean(location_paramList,pTsCallBack);//设置数据
                titlePopupWindow.showAsDropDown(rl_location);
                break;
            case R.id.rl_price:
                currentPost = 1;
                TitlePopupWindow titlePopupWindow1 = new TitlePopupWindow(this,true);
                titlePopupWindow1.setParamListBean(price_paramList,pTsCallBack);//设置数据

                titlePopupWindow1.setPrice_zidingyuView(this,pTsCallBack);
                titlePopupWindow1.showAsDropDown(rl_location);
                break;
            case R.id.rl_room:
                currentPost = 2;
                TitlePopupWindow titlePopupWindow2 = new TitlePopupWindow(this,true);
                titlePopupWindow2.setParamListBean(room_paramList,pTsCallBack);//设置数据
                titlePopupWindow2.showAsDropDown(rl_location);
                break;
            case R.id.rl_type:
                currentPost = 3;
                TitlePopupWindow titlePopupWindow3 = new TitlePopupWindow(this,true);
                titlePopupWindow3.setParamListBean(type_paramList,pTsCallBack);//设置数据
                titlePopupWindow3.showAsDropDown(rl_location);
                break;
            case R.id.rl_more://更多
                Intent intent =new Intent(OnlineBuildActivity.this,Mroemessage.class);
                intent.putExtra("OnLineHouseRequest",onLineHouseRequest);
                startActivityForResult(intent,1);
                break;

            case R.id.compare_type_btn:
                String str = (String) compare_type_btn.getTag();
                if(str.equals("dianji")){
                    compare_type_btn.setTag("wanle");
                    compare_type_btn.setImageResource(R.mipmap.compare_cancel_btn);
                    adater.setClickOn(true);
                    compare_btn.setClickable(true);

                }else {
                    compare_type_btn.setTag("dianji");
                    compare_type_btn.setImageResource(R.mipmap.compare_add_btn);
                    adater.setClickOn(false);
                    compare_btn.setClickable(true);
                }
                adater.notifyDataSetChanged();

                break;
            case R.id.compare_btn:
                String str1 = (String) compare_type_btn.getTag();
                List<Object> objectList = adater.getTotalList();
                OnLineHouseResult resultBeanss = new OnLineHouseResult();
                List<HouseArrBean> houseArr =new ArrayList<>();
                List<HouseOneArrBean> houseOneArr =new ArrayList<>();
                for(Object obj :objectList){
                    if(obj instanceof HouseArrBean){
                        HouseArrBean houseArrBean = (HouseArrBean) obj;
                        if(houseArrBean.isSelect){
                            houseArr.add(houseArrBean);

                        }
                    }
                    if(obj instanceof  HouseOneArrBean){
                        HouseOneArrBean houseOneArrBean = (HouseOneArrBean) obj;
                        if(houseOneArrBean.isSelect){
                            houseOneArr.add(houseOneArrBean);
                        }
                    }

                }
                resultBeanss.houseArr=houseArr;
                resultBeanss.houseOneArr=houseOneArr;
                Bundle bundle =new Bundle();
                bundle.putSerializable("resultBeanss",resultBeanss);
                if(!str1.equals("dianji")){
                    if(adater.getTotalList().size()>0){
                        IntentUtils.openActivity(this,Bijiao.class,bundle);
                    }else {ToastUtil.showToast("请添加");}

                }else{
                    ToastUtil.showToast("请添加");

                }


                break;
        }
    }

    int currentPost = 0;
    /**
     * 筛选后回调的返回值接收
     */
    private TitlePopupWindow.PupupWindowItemClickCallBack  pTsCallBack = new TitlePopupWindow.PupupWindowItemClickCallBack() {
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
            onLineHouseRequest.pageNo =0;
            getOnlineShuju();
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){
            onLineHouseRequest = (OnLineHouseRequest) data.getSerializableExtra("OnLineHouseRequest");
            onLineHouseRequest.pageNo = 0;
            getOnlineShuju();

        }
    }
    public void getOnlineShuju(){
        showProgressBarDialog();
        HttpHelper.getOnLineHouseList(this,onLineHouseRequest, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                dismissProgressBarDialog();
                OnLineAnd res =new Gson().fromJson(result,OnLineAnd.class);
                andlist =res.inlistData();
                if(onLineHouseRequest.pageNo==0){
                    adater.setTotalList(andlist);
                }else {
                    adater.addTotaList(andlist);
                }
                adater.notifyDataSetChanged();
                if (adater.getCount()==0){
                    zai_no_house.setVisibility(View.VISIBLE);
                }else {
                    zai_no_house.setVisibility(View.GONE);
                }
                if ((andlist==null||andlist.size()==0)||adater.getCount()==0){
                    ToastUtil.showToast("没有数据");
                }
                on_list.setOnComplete();

            }

            @Override
            public void OnFailed(String errMsg) {
                dismissProgressBarDialog();
                ToastUtil.showToast(errMsg);
                if (adater.getCount()==0){
                    zai_no_house.setVisibility(View.VISIBLE);
                }else {
                    zai_no_house.setVisibility(View.GONE);
                }
                on_list.setOnComplete();

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(int i = 0;i< onlineTypeBean.result.size();i++){
            List<ParamListBean> paramList = onlineTypeBean.result.get(i).paramList;
            for (int j = 0;j < paramList.size();j++){
                paramList.get(j).isSelect = false;
                if(paramList.get(j).childList != null){
                    for( ParamListBean bean :paramList.get(j).childList){
                        bean.isSelect = false;
                    }
                }
            }
        }
    }
}
