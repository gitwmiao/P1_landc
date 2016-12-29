package com.bwf.p1_landz.iu.onlinevilla.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.framework.base.BaseFrament;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.LogUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ImgUrlArrBean;
import com.bwf.p1_landz.iu.onlinevilla.Lookphonto;
import com.bwf.p1_landz.iu.onlinevilla.adapter.ImagePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/12.
 */

public class FragmentTwo extends BaseFrament {
    private static final int MSG_DELAY = 2000;

    private TextView yangbanjian;

    private ViewPager detailViewPager;


    private int location = -1;
    private boolean circle;
    private boolean canAuto = true;


    private List<ImgUrlArrBean> imgUrlArr;
    private ImageLoader imageLoader;

    public void setImgUrlArr(List<ImgUrlArrBean> imgUrlArr, boolean isYangbanjian, ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        beforInitView();
        if(isYangbanjian){
            yangbanjian.setVisibility(View.VISIBLE);
            this.imgUrlArr = new ArrayList<>();
            for(ImgUrlArrBean imgUrlArrBean : imgUrlArr){
                if(imgUrlArrBean.picType.equals("5")) {//判断是否是样板间图片
                    this.imgUrlArr.add(imgUrlArrBean);
                }
            }
        }else{
            this.imgUrlArr = imgUrlArr;
        }

        ImagePagerAdapter imap = new ImagePagerAdapter(this.getContext(), this.imgUrlArr, imageLoader);
        detailViewPager.setAdapter(imap);

       if(canAuto){

           handler.sendEmptyMessageDelayed(1, MSG_DELAY);
       }


    }
    public void setImgUrlArr(List<ImgUrlArrBean> imgUrlArr,boolean isYangbanjian,boolean canAuto,ImageLoader imageLoader){
        this.canAuto = canAuto;
        setImgUrlArr(imgUrlArr,isYangbanjian,imageLoader);
    }




    @Override
    public int getResourceId() {
        return R.layout.fragment_onehouse2;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    location++;
                    detailViewPager.setCurrentItem(location);
                    handler.sendEmptyMessageDelayed(1, MSG_DELAY);
                    break;
                case 2:
                    handler.removeMessages(1);
                    break;
            }


        }
    };

    @Override
    public void beforInitView() {
        yangbanjian = findViewByIdNoCast(R.id.yangbanjian);
        detailViewPager = findViewByIdNoCast(R.id.detail_viewPager);

    }

    @Override
    public void initView() {

        detailViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //滑动中执行
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //选中执行
            @Override
            public void onPageSelected(int position) {
                location = position;

                if(onPageSelectListener != null){
                    onPageSelectListener.onPageSelected(position);
                }

            }

            //滑动状态改变执行
            @Override
            public void onPageScrollStateChanged(int state) {
                if(!canAuto)
                    return;
                if (state == ViewPager.SCROLL_STATE_IDLE) {//ViewPager滑动停止  自动
                    if (circle) {
                        circle = false;
                        handler.sendEmptyMessageDelayed(1, MSG_DELAY);
                    }
                }

                //dragging
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {//ViewPager拉拽  手动
                    handler.sendEmptyMessage(2);
                    circle = true;
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) {
            if(canAuto){
                handler.removeCallbacksAndMessages(null);
            }

        }
    }

    @Override
    public void initData() {
        if(getActivity() !=null&& getActivity() instanceof Lookphonto){
            Lookphonto activity = (Lookphonto) getActivity();
        }

    }

    @Override
    public void afterInitView() {

    }


    @Override
    public void onClick(View view) {

    }

    public void setCurrentItem(int currentItem) {
        detailViewPager.setCurrentItem(currentItem);
    }

    private OnPageSelectListener onPageSelectListener;

    public void setOnPageSelectListener(OnPageSelectListener onPageSelectListener) {
        this.onPageSelectListener = onPageSelectListener;
    }


    //写一个接口 用来回调
    public interface OnPageSelectListener {
        void onPageSelected(int position);
    }
}
