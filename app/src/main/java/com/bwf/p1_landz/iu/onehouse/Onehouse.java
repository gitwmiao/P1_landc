package com.bwf.p1_landz.iu.onehouse;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.p1_landz.R;

/**
 * Created by Administrator on 2016/12/7.
 */

public class Onehouse extends BaseActivity {
    /**
     * 地图
     */
    private TextView mOneHTv;
    /**
     * 请输入楼盘信息...
     */
    private EditText mOneHEd;
    /**
     * 区域
     */
    private TextView mOneHQuyu;
    private RelativeLayout mOneHRl;
    /**
     * 均价
     */
    private TextView mOneJunTv;
    private RelativeLayout mOneJunRl;
    /**
     * 更多
     */
    private TextView mOneGdTv;
    private RelativeLayout mOneGdRl;

    @Override
    public int getContentViewId() {
        return R.layout.one_home;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {

        mOneHTv = (TextView) findViewById(R.id.one_h_tv);
        mOneHEd = (EditText) findViewById(R.id.one_h_ed);
        mOneHQuyu = (TextView) findViewById(R.id.one_h_quyu);
        mOneHRl = (RelativeLayout) findViewById(R.id.one_h_rl);
        mOneJunTv = (TextView) findViewById(R.id.one_jun_tv);
        mOneJunRl = (RelativeLayout) findViewById(R.id.one_jun_rl);
        mOneGdTv = (TextView) findViewById(R.id.one_gd_tv);
        mOneGdRl = (RelativeLayout) findViewById(R.id.one_gd_rl);
    }

    @Override
    public void initData() {

    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO:OnCreate Method has been created, run FindViewById again to generate code
        setContentView(R.layout.one_home);
        initView();
    }
}
