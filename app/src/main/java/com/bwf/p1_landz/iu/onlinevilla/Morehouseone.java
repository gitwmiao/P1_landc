package com.bwf.p1_landz.iu.onlinevilla;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.framework.http.HttpRequestAsyncTask;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.LogUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.MoreOneHuose;
import com.bwf.p1_landz.iu.onlinevilla.adapter.SevenAdapter;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/12/18.
 */

public class Morehouseone extends BaseActivity{
    private TextView tv;
    private ListView lv;
    private ImageLoader imageLoader;
    private SevenAdapter ap;
    private String resblockId;
    private String houseOneId;
    private int pageNo;
    @Override
    public int getContentViewId() {
        return R.layout.activity_more;
    }

    @Override
    public void beforInitView() {

        resblockId =getIntent().getStringExtra("resblockId");
        houseOneId =getIntent().getStringExtra("houseOneId");
        pageNo =getIntent().getIntExtra("pageNo",0);


    }

    @Override
    public void initView() {
        tv= findViewByIdNoCast(R.id.moreguwen_tv);
        lv =findViewByIdNoCast(R.id.moreguwen_lv);

    }

    @Override
    public void initData() {
        tv.setText("房源推荐");
        imageLoader =new ImageLoader();
        ap =new SevenAdapter(getBaseContext(),imageLoader);
        lv.setAdapter(ap);
        getHo();


    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {

    }
    public  void getHo(){
        if(resblockId==null){
            return;
        }
        HttpHelper.getOneHouseMore(this, houseOneId, resblockId,pageNo, new HttpRequestAsyncTask.CallBack() {
            @Override
            public void OnSuccess(String result) {
                MoreOneHuose res =new  Gson().fromJson(result,MoreOneHuose.class);
                LogUtils.e("SSSSSSS",res.toString());
                ap.setResult(res.result);
                ap.notifyDataSetChanged();

            }

            @Override
            public void OnFailed(String errMsg) {

            }
        });
    }
}
