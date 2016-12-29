package com.bwf.p1_landz.iu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;

import android.widget.ListView;
import android.widget.Scroller;

import com.bwf.framework.utils.LogUtils;

/**
 * Created by Administrator on 2016/12/7.
 */

public class ListViewll extends ListView {
    private ListViewsl sl_list = null;
    private ListViewxl xl_list = null;
    private float mLastY = -1;
    private int mHeaderViewHeight = 0; // 头标的高度
    private int mFooterViewHeight = 0; // 头标的高度
    private final static float OFFSET_RADIO = 1.8f;// 移动的比例
    private Scroller mScroller;// 滑动工具类


    private final static int SCROLL_DURATION = 400;


    public ListViewll(Context context, AttributeSet attrs) {

        super(context, attrs);
        mScroller = new Scroller(context, new DecelerateInterpolator());
        sl_list = new ListViewsl(context);
        xl_list = new ListViewxl(context);

        this.addHeaderView(xl_list);
        this.addFooterView(sl_list);
        xl_list.measure(0, 0);
        mHeaderViewHeight = xl_list.getMeasuredHeight();
        sl_list.measure(0, 0);
        mFooterViewHeight = sl_list.getMeasuredHeight();
        sl_list.setVisibility(GONE);

        mScroller = new Scroller(context);
        this.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                //判断到了ListView的最后一行 显示加载更多View
                if(i == SCROLL_STATE_IDLE && (getLastVisiblePosition() == getCount()-1)){
                    sl_list.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    //触摸监听
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            //getRawY()  触摸点相对于屏幕的坐标
            //getY  触摸点相对于父类的View的坐标
            case MotionEvent.ACTION_DOWN://按下
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE://移动
                //移动Y的距离
                float deltaY = ev.getRawY()-mLastY;
                //获得继续移动的Y的坐标
                mLastY = ev.getRawY();
                //移动
                //判断是下拉刷新移动(当前ListView显示在界面的View第一个是多少,当前移动的方向是向下，)
                if(this.getFirstVisiblePosition() == 0 && (xl_list.getVisiableHeight()+50>0 ||deltaY > 0)){
                    updateHeaderHeight(deltaY/OFFSET_RADIO);
                    return true;
                }else if(getLastVisiblePosition() == getCount()-1 &&(sl_list.getVisiableHeight() > 0 || deltaY < 0)){
                    updateFooterHeight(-deltaY/OFFSET_RADIO);
                }
                break;
            case MotionEvent.ACTION_UP://抬起
                mLastY = -1;//重置
                //判断下拉刷新抬起
                if(this.getFirstVisiblePosition() == 0){
                    //当前显示的高度大于0
                    if(xl_list.getVisiableHeight()+50 > 0){
                        if(xl_list.getVisiableHeight() > mHeaderViewHeight){
                            xl_list.setState(ListViewxl.STATE_REFRESHING);//设置成刷新状态
                            if(refreh_listViewListener != null){
                                refreh_listViewListener.onRefresh();//下拉刷新回调
                            }
                        }
                        resetHeaderHeight();//重置回下拉刷新View的显示
                    }
                }else if(getLastVisiblePosition() == getCount()-1){
                    if(sl_list.getVisiableHeight() > mFooterViewHeight){
                        sl_list.setState(ListViewsl.STATE_LOADING);
                        if(refreh_listViewListener != null){
                            refreh_listViewListener.onLoadMore();
                        }
                    }
                    resetFooterHeight();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    //更新头部的高度
    public void updateHeaderHeight(float delta){
        //设置高度
        xl_list.setVisiableHeight(xl_list.getVisiableHeight()+(int)delta);
        if(xl_list.getVisiableHeight() > mHeaderViewHeight){
            xl_list.setState(ListViewxl.STATE_READY);//进入到刷新
        }else{
            xl_list.setState(ListViewxl.STATE_NORMAL);
        }
        invalidate();
    }


    /**
     * 重置头部高度
     */
    public void resetHeaderHeight(){
        LogUtils.e("msg",xl_list.getVisiableHeight()+"  "+mHeaderViewHeight);
        //判断当前下拉的高度是否大于默认头部的高度的2倍 则启动刷新操作 不然回到原来位置
        if(xl_list.getVisiableHeight() > mHeaderViewHeight){
            mScroller.startScroll(0, xl_list.getVisiableHeight(),0,mHeaderViewHeight-80-xl_list.getVisiableHeight(),SCROLL_DURATION);
        }else{
            mScroller.startScroll(0, xl_list.getVisiableHeight(),0,-xl_list.getVisiableHeight(),SCROLL_DURATION);
        }
        invalidate();//刷新View 启动conputeScrool
    }

    //更新尾部的高度
    public void updateFooterHeight(float delta){
        int height = sl_list.getVisiableHeight()+(int)delta;
        if(height > mFooterViewHeight + 50){
            sl_list.setState(ListViewsl.STATE_READY);
        }else{
            sl_list.setState(ListViewsl.STATE_NORMAL);
        }
        sl_list.setVisiableHeight(height);
    }


    public void resetFooterHeight(){
        if(sl_list.getVisiableHeight() > mFooterViewHeight+50){
            mScroller.startScroll(0,sl_list.getVisiableHeight(),0,mFooterViewHeight-sl_list.getVisiableHeight(),SCROLL_DURATION);
        }
        invalidate();
    }
    //当View刷新的时候  这个方法会执行
    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){//判断Scroller是否有没有执行完成
            if(!mScroller.isFinished()){
                if(getFirstVisiblePosition() == 0){
                    xl_list.setVisiableHeight(mScroller.getCurrY());
                }else if(getLastVisiblePosition() == getCount()-1){
                    sl_list.setVisiableHeight(mScroller.getCurrY());
                }
            }
            postInvalidate();//刷新View
        }
        super.computeScroll();
    }

    //设置刷新或者回调完成的方法
    public void setOnComplete(){
        //刷新结束
        xl_list.setState(ListViewxl.STATE_NORMAL);//设置回初始状态
        resetHeaderHeight();
        //加载结束
        sl_list.setState(ListViewsl.STATE_NORMAL);
        resetFooterHeight();
    }

    //声明接口
    private Refreh_ListViewListener refreh_listViewListener;

    public void setRefreh_listViewListener(Refreh_ListViewListener refreh_listViewListener) {
        this.refreh_listViewListener = refreh_listViewListener;
    }

    public interface Refreh_ListViewListener{
        void onRefresh();
        void onLoadMore();
    }
}
