package com.bwf.p1_landz.iu.onlinevilla;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.framework.http.HttpRequestAsyncTask;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.LogUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.GuWenResultBean;
import com.bwf.p1_landz.iu.onlinevilla.adapter.FragmentThreeadapter;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2016/12/18.
 */

public class Moreadviser extends BaseActivity {
    private ListView lv;
    private String resourceId;
    private int pageNo;
    private String type;
    private FragmentThreeadapter ap;
    private ImageLoader imageLoader;
    @Override
    public int getContentViewId() {
        return R.layout.activity_more;
    }

    @Override
    public void beforInitView() {
        resourceId =getIntent().getStringExtra("resourceId");

    }

    @Override
    public void initView() {
        lv = findViewByIdNoCast(R.id.moreguwen_lv);

    }

    @Override
    public void initData() {
        pageNo=0;
        imageLoader =new ImageLoader();
        ap =new FragmentThreeadapter(getBaseContext(),imageLoader);
        getGw();
        lv.setAdapter(ap);



    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {

    }
    public void getGw(){
        if(resourceId==null){
            return;
        }
        HttpHelper.getLookHistory(this,resourceId,"1",pageNo,new HttpRequestAsyncTask.CallBack(){

            @Override
            public void OnSuccess(String result) {
                GuWenResultBean resultBean = new Gson().fromJson(result, GuWenResultBean.class);
                if(resultBean!=null){
                    LogUtils.i("DDDDD",resultBean.toString());
                    if(pageNo==0){
                        ap.setShowArrs(resultBean.result.showArr);
                        ap.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void OnFailed(String errMsg) {

            }
        });
    }
}