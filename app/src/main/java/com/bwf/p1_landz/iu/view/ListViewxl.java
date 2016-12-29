package com.bwf.p1_landz.iu.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.p1_landz.R;

/**
 * Created by Administrator on 2016/12/7.
 */

public class ListViewxl extends LinearLayout{
    private LinearLayout xl_ll;
    private ImageView xl_im;
    private ProgressBar xl_rp;
    private TextView xl_tv;
    private int mState = STATE_NORMAL;
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;
    private final int ROTATE_ANIM_DURATION = 180;

    public ListViewxl(Context context) {

        super(context);
        xl_ll= (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xiala,null);
        LayoutParams pl =new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        this.addView(xl_ll,pl);

        xl_im= (ImageView) findViewById(R.id.xl_im);
        xl_rp = (ProgressBar) findViewById(R.id.xl_pr);
        xl_tv = (TextView) findViewById(R.id.xl_tv);
        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);

        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
    }
    public void setVisiableHeight(int height) {
        LayoutParams pl = (LayoutParams) xl_ll
                .getLayoutParams();
        pl.height = height;
        xl_ll.setLayoutParams(pl);
    }
    public int getVisiableHeight() {
        return xl_ll.getHeight();
    }
    //设置不同的状态
    public void setState(int state) {
        if (state == mState) {
            return;
        }
        switch (state) {
            case STATE_NORMAL://初始状态
                if (mState != STATE_REFRESHING) {//如果是第一次启动就开始动画
                    xl_im.startAnimation(mRotateDownAnim);
                }
                xl_tv.setText(R.string.xlistview_header_hint_normal);

                xl_im.setVisibility(View.VISIBLE);
                xl_rp.setVisibility(View.INVISIBLE);
                break;
            case STATE_READY://正在刷新
                if (mState != STATE_READY) {//移动调用一次
                    xl_im.clearAnimation();
                    //默认图片方向是向下   启动动画  把图片方向向上
                    xl_im.startAnimation(mRotateUpAnim);
                    //修改文字信息
                    xl_tv.setText(R.string.xlistview_header_hint_ready);

                }
                break;
            case STATE_REFRESHING://抬起
                xl_tv.setText(R.string.xlistview_header_hint_loading);
                xl_im.clearAnimation();
                xl_im.setVisibility(View.INVISIBLE);
                xl_rp.setVisibility(View.VISIBLE);

                break;
            default:
        }
        mState = state;
    }


}
