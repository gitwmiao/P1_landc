package com.bwf.p1_landz.iu.onlinevilla;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.framework.http.HttpRequestAsyncTask;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.LogUtils;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.GuWenResultBean;
import com.bwf.p1_landz.entity.HouseDetailResultBean;
import com.bwf.p1_landz.entity.MoreOneHuose;
import com.bwf.p1_landz.entity.OnLineHouseResult;
import com.bwf.p1_landz.entity.OnLineTypeResultBean;
import com.bwf.p1_landz.entity.ResultOneHouse;
import com.bwf.p1_landz.iu.onlinevilla.fragment.FragmemtSix;
import com.bwf.p1_landz.iu.onlinevilla.fragment.FragmentFive;
import com.bwf.p1_landz.iu.onlinevilla.fragment.FragmentFour;
import com.bwf.p1_landz.iu.onlinevilla.fragment.FragmentOne;
import com.bwf.p1_landz.iu.onlinevilla.fragment.FragmentSeven;
import com.bwf.p1_landz.iu.onlinevilla.fragment.FragmentThree;
import com.bwf.p1_landz.iu.onlinevilla.fragment.FragmentTwo;
import com.bwf.p1_landz.iu.view.Wenxid;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/12.
 */

public class HouseOne extends BaseActivity {
    @BindView(R.id.tv_title_detail)
    TextView tvTitleDetail;
    @BindView(R.id.tv_decription_detail)
    TextView tvDecriptionDetail;
    @BindView(R.id.share_img)
    ImageView shareImg;
    @BindView(R.id.shouchang_img)
    ImageView shouchangImg;
    @BindView(R.id.im_detail_photo)
    ImageView imDetailPhoto;
    @BindView(R.id.tv_huxing_name)
    TextView tvHuxingName;
    @BindView(R.id.tv_huxing_phone)
    TextView tvHuxingPhone;
    @BindView(R.id.im_detail_phone)
    ImageView imDetailPhone;
    @BindView(R.id.im_detail_sms)
    ImageView imDetailSms;
    @BindView(R.id.bottom)
    RelativeLayout bottom;

    private ImageLoader imageLoader;
    private String houseOneId, resblockId,resblockOneId,houseId;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private FragmentFive fragmemtFive;
    private FragmemtSix fragmemtSix;
    private FragmentSeven fragmentSeven;
    private GuWenResultBean.ShowArr show;
    private  HouseDetailResultBean resultBean;
    private Wenxid dal;


    @Override
    public int getContentViewId() {
        return R.layout.house_one;
    }

    @Override
    public void beforInitView() {
        houseOneId =getIntent().getStringExtra("houseOneId");
        resblockOneId =getIntent().getStringExtra("resblockOneId");

    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        tvTitleDetail = (TextView) findViewById(R.id.tv_title_detail);
        tvDecriptionDetail = (TextView) findViewById(R.id.tv_decription_detail);
        shareImg = (ImageView) findViewById(R.id.share_img);
        shouchangImg= findViewByIdNoCast(R.id.shouchang_img);
        imDetailPhoto =findViewByIdNoCast(R.id.im_detail_photo);
        tvHuxingName=findViewByIdNoCast(R.id.tv_huxing_name);
        tvHuxingPhone=findViewByIdNoCast(R.id.tv_huxing_phone);
        imDetailSms =findViewByIdNoCast(R.id.im_detail_sms);
        bottom =findViewByIdNoCast(R.id.bottom);
        imDetailPhone=findViewByIdNoCast(R.id.im_detail_phone);
        fragmentOne = (FragmentOne) getSupportFragmentManager().findFragmentById(R.id.detail_fragment01);
        fragmentTwo = (FragmentTwo) getSupportFragmentManager().findFragmentById(R.id.detail_fragment02);
        fragmentThree = (FragmentThree) getSupportFragmentManager().findFragmentById(R.id.detail_fragment03);
        fragmentFour = (FragmentFour) getSupportFragmentManager().findFragmentById(R.id.detail_fragment04);
        fragmemtFive = (FragmentFive) getSupportFragmentManager().findFragmentById(R.id.detail_fragment05);
        fragmemtSix = (FragmemtSix) getSupportFragmentManager().findFragmentById(R.id.detail_fragment06);
        fragmentSeven = (FragmentSeven) getSupportFragmentManager().findFragmentById(R.id.detail_fragment07);
        setOnClick(R.id.share_img);

    }

    @Override
    public void initData() {
        imageLoader = new ImageLoader();
        getNetWorkData();
        getguwenDate();
        getgengduofrist();

    }

    @Override
    public void afterInitView() {

    }



    private void getNetWorkData() {
        if (TextUtils.isEmpty(houseOneId)) {
            finish();
            return;
        }
        showProgressBarDialog();
        HttpHelper.getDetail(this, houseOneId, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                dismissProgressBarDialog();
                 resultBean = new Gson().fromJson(result, HouseDetailResultBean.class);
                if(resultBean.result != null){
                    setTitle_Content(resultBean);
                    fragmentOne.setResultBean(resultBean,imageLoader);
                    fragmentTwo.setImgUrlArr(resultBean.result.imgUrlArr,true,imageLoader);
                    fragmentFour.setResult(resultBean.result);
                    fragmemtFive.setApartmentImgVos(resultBean.result.apartmentImgVos,imageLoader,findViewByIdNoCast(R.id.title_bar_one));
                    fragmemtSix.setResult(resultBean.result,imageLoader);


                }
            }

            @Override
            public void OnFailed(String errMsg) {
                dismissProgressBarDialog();
                ToastUtil.showToast(errMsg);
            }
        });
    }
    @OnClick({R.id.share_img, R.id.shouchang_img, R.id.im_detail_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_img:
                showda();

                break;
            case R.id.shouchang_img:
                break;
            case R.id.im_detail_photo:
                break;
        }
    }


    private void  setTitle_Content(HouseDetailResultBean resultBean){
        tvTitleDetail.setText(resultBean.result.resblockOneName);
        StringBuffer sb = new StringBuffer();
        sb.append(resultBean.result.totalprBegin)
                .append("-")
                .append(resultBean.result.totalprEnd)
                .append("万  ")
                .append((int)(resultBean.result.buildSize)+"平米  ")
                .append((int)resultBean.result.bedroomAmount+"室")
                .append((int)resultBean.result.parlorAmount+"厅")
                .append((int)resultBean.result.toiletAmount+"卫");
        tvDecriptionDetail.setText(sb.toString());
    }
    private void getguwenDate(){
        HttpHelper.getOneDetailLook(this, houseOneId, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                GuWenResultBean resultBean = new Gson().fromJson(result, GuWenResultBean.class);
                LogUtils.e("2222",resultBean.toString());
                if (resultBean != null) {
                    if (resultBean.result.showArr != null && !resultBean.result.showArr.isEmpty()){
                        fragmentThree.setResult(resultBean.result, houseOneId, imageLoader);
                        show = resultBean.result.showArr.get(0);
                        tvHuxingName.setText(show.createName);
                        tvHuxingPhone.setText(show.phone);
                        imageLoader.displayImg(show.photo,imDetailPhoto);
                    }
                }
            }

            @Override
            public void OnFailed(String errMsg) {
                ToastUtil.showToast(errMsg);
            }
        });
    }
    private void getgengduofrist(){

        HttpHelper.getOneDetailMore(this,houseOneId,resblockOneId, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                MoreOneHuose resultBen = new Gson().fromJson(result,MoreOneHuose.class);
                LogUtils.i("12222",resultBen.toString());
                if(resultBen!=null){
                    fragmentSeven.setResult(houseOneId,resblockOneId,resultBen.result,imageLoader);
                }



            }

            @Override
            public void OnFailed(String errMsg) {
                ToastUtil.showToast(errMsg);

            }
        });
    }

    public  void showda(){
        dal = new Wenxid(HouseOne.this,R.style.weixinDialog,HouseOne.this);
        dal.show();
    }


}
