package com.bwf.p1_landz.iu.welcome;

import android.animation.ObjectAnimator;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bwf.framework.base.BaseActivity;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.iu.welcome.adapter.GuideAdapter;
import com.bwf.framework.utils.DisplayUtil;

/**
 * Created by Administrator on 2016/11/29.
 */

public class GuidActivity extends BaseActivity{
    private ViewPager viewPager;
    private ImageView circle_01,circle_02,circle_03,splash_img;
    private ImageView[] circles =null;
    private LinearLayout ll_circle;
    private GuideAdapter guideAdapter;
    private int[] imgs = {R.mipmap.splash_a, R.mipmap.splash_b,R.mipmap.splash_c};
    @Override
    public int getContentViewId() {
        return R.layout.activity_guide;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        viewPager =findViewByIdNoCast(R.id.viewpager);
        ll_circle=findViewByIdNoCast(R.id.ll_circle);
        splash_img= findViewByIdNoCast(R.id.splash_img);
        circle_01 = findViewByIdNoCast(R.id.circle01);
        circle_02 = findViewByIdNoCast(R.id.circle02);
        circle_03 = findViewByIdNoCast(R.id.circle03);

    }

    @Override
    public void initData() {circles =new ImageView[]{circle_01,circle_02,circle_03};

    }

    @Override
    public void afterInitView() {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ll_circle.getLayoutParams();
        params.width= DisplayUtil.getDensity_Width(this);
        ll_circle.setLayoutParams(params);
        guideAdapter =new GuideAdapter(getSupportFragmentManager());
        viewPager.setAdapter(guideAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCheck(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setCheck(0);

    }

    @Override
    public void onClick(View view) {

    }

    public  void setCheck(int position){
        for (int i = 0;i < circles.length;i++){
            if(position == i){
                circles[i].setImageResource(R.mipmap.checked_page);
            }else{
                circles[i].setImageResource(R.mipmap.unchecked_page);
            }
        }
        //设置背景图片的切换
        splash_img.setImageResource(imgs[position]);

        //设置背景图片动画
        splash_bg_anim();
    }

    public void splash_bg_anim(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(splash_img,"translationX",0
                ,DisplayUtil.getDensity_Width(this)-getResources().getDimension(R.dimen.splash_a),0);
        animator.setDuration(15000);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();
    }
}
