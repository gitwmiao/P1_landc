package com.bwf.framework.base;

import android.app.Dialog;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bwf.framework.tools.AppManager;
import com.bwf.p1_landz.R;


/**
 * Created by Administrator on 2016/11/28.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    public Dialog dialog = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        AppManager.getInstance().addActivity(this);

        beforInitView();
        initView();
        initData();
        afterInitView();

    }



    /**
     * //获得LayoutId
     *
     * @param
     */
    public abstract int getContentViewId();

    /**
     * 初始化View之前的类容
     *
     * @param
     */
    public abstract void beforInitView();

    /**
     * 初始View
     *
     * @param
     */
    public abstract void initView();

    /**
     * 初始化数据
     *
     * @param
     */
    public abstract void initData();

    /**
     * 初始化之后的执行
     *
     * @param
     */
    public abstract void afterInitView();

    /**
     * 不用强制转换的findViewId
     *
     * @param
     */
    public <T extends View> T findViewByIdNoCast(int id) {
        return (T) findViewById(id);
    }

    /**
     * 设置所有需要注册的监听
     *
     * @param
     */
    public void setOnClick(int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(this);
        }
    }

    public void setOnClick(View... views) {
        for (View v : views) {
            v.setOnClickListener(this);
        }
    }

    /**
     * 显示进度条
     *
     * @param
     */
    public void showProgressBarDialog(){
        if(dialog == null){
            //没有标题的dialog
            dialog = new Dialog(this,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        }
        View view = View.inflate(this, R.layout.dialog_progressbar,null);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    /**
     *关进度条
     * @param
     */
    public void dismissProgressBarDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
    }



}
