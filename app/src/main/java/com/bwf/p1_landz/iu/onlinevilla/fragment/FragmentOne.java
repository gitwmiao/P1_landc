package com.bwf.p1_landz.iu.onlinevilla.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.framework.base.BaseFrament;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseDetailResultBean;
import com.bwf.p1_landz.iu.onlinevilla.Lookphonto;

import java.util.ArrayList;


import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/12.
 */

public class FragmentOne extends BaseFrament {

    private ImageView imgTitlePic;

    private TextView tvHouseDesc;

    private ImageView imgDown;

    private HouseDetailResultBean resultBean;
    private ImageLoader imageLoader;
    private int lineNum = 0;

    public void setResultBean(HouseDetailResultBean resultBean, ImageLoader imageLoader){
        this.resultBean = resultBean;
        this.imageLoader = imageLoader;
        initData();
    }

    @Override
    public int getResourceId() {
        return R.layout.fragment_onehouse;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        imgTitlePic =findViewByIdNoCast(R.id.img_titlePic);
        tvHouseDesc =findViewByIdNoCast(R.id.tv_house_desc);
        imgDown =findViewByIdNoCast(R.id.img_down);
        setOnClick(imgDown);
        setOnClick(R.id.img_titlePic);

    }
    boolean isUp = false;

    @Override
    public void initData() {
        if(resultBean != null){
            //给logo图片下载
            imageLoader.displayImg(resultBean.result.titlepicImg,imgTitlePic);
            //房源描述
            tvHouseDesc.setText(resultBean.result.roomDescripe);
            //设置房源描述的行数  100毫秒执行
            tvHouseDesc.postDelayed(new Runnable() {
                @Override
                public void run() {
                    lineNum = tvHouseDesc.getLineCount();
                    if(lineNum > 5){
                        tvHouseDesc.setLines(5);
                    }else{
                        tvHouseDesc.setLines(lineNum);
                    }
                }
            },100);
        }
    }

    @Override
    public void afterInitView() {

    }





    @OnClick
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_titlePic:
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("imgUrlArr", (ArrayList<? extends Parcelable>) resultBean.result.imgUrlArr);
                IntentUtils.openActivity(getContext(), Lookphonto.class,bundle);
                break;
            case R.id.img_down:

                if(isUp){//关闭
                    isUp = false;
                    imgDown.setImageResource(R.mipmap.first_down);
                    if(lineNum > 5){
                        tvHouseDesc.setLines(5);
                    }else{
                        tvHouseDesc.setLines(lineNum);
                    }
                }else{//打开
                    isUp = true;
                    tvHouseDesc.setLines(lineNum);
                    imgDown.setImageResource(R.mipmap.content_up);
                }
                break;
        }
    }
}
