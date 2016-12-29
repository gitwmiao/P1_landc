package com.bwf.p1_landz.iu.welcome;

import android.view.View;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.share.SharedHelper;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.iu.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/11/29.
 */

public class WelcomeActivity extends BaseActivity{
    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void afterInitView() {
        Timer timer =new Timer();
        TimerTask task =new TimerTask() {
            @Override
            public void run() {
                if (SharedHelper.getInstance(WelcomeActivity.this).getIsFirst()){
                    IntentUtils.openActivity(WelcomeActivity.this,GuidActivity.class);
                }else {
                    IntentUtils.openActivity(WelcomeActivity.this,MainActivity.class);
                }
                finish();
            }
        };
        timer.schedule(task,2000);

    }

    @Override
    public void onClick(View view) {

    }
}
