package com.bwf.p1_landz.shousuo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.http.HttpHelper;
import com.bwf.framework.http.HttpRequestAsyncTask;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.Shuosuoshuju;
import com.bwf.p1_landz.iu.onlinevilla.OnlineBuildActivity;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/12/11.
 */

public class ShousuoActivity extends BaseActivity{
    private EditText e_tv;
    private ListView l_vw;
    private ShousuoAdapeter s_ap;
    private Shuosuoshuju shuosuo ;
    private int type;
    @Override
    public int getContentViewId() {
        return R.layout.search;
    }

    @Override
    public void beforInitView() {
        type =getIntent().getIntExtra("insert_type",0);

    }

    @Override
    public void initView() {
        e_tv =findViewByIdNoCast(R.id.et_search);
        l_vw =findViewByIdNoCast(R.id.lv_history_data);

    }

    @Override
    public void initData() {
        setEditTextHit();
        s_ap =new ShousuoAdapeter(this);
        l_vw.setAdapter(s_ap);

    }

    @Override
    public void afterInitView() {
        e_tv.addTextChangedListener(watcher);
        l_vw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 隐藏键盘
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(ShousuoActivity.this
                                .getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                String resblockName = "";
                String circleTypeCode = "";
                if (type == 3 || type == 5) {//判断是否跳转到在线房源
                    if(shuosuo != null){//medel  搜索请求到的数据
                        if(shuosuo.result.get(position).type == 0 ||shuosuo.result.get(position).type == 1){
                            resblockName = shuosuo.result.get(position).name;
                        }else if(shuosuo.result.get(position).type == 2 ){
                            circleTypeCode = shuosuo.result.get(position).id;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("resblockName", resblockName);
                        bundle.putString("circleTypeCode", circleTypeCode);
                        IntentUtils.openActivity(ShousuoActivity.this, OnlineBuildActivity.class, bundle);
                        finish();
                    }
                }
            }
        });


    }

    @Override
    public void onClick(View view) {

    }
    int count = 0;
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            if (type == 1) {
                //地图
            } else if (type == 2 || type == 5) {
                count = 0;

            } else if (type == 3) {
                count = 1;
            } else if (type == 4) {
                count = 2;
            }
            String content = e_tv.getText().toString().trim();
            if(!TextUtils.isEmpty(content)){
                HttpHelper.getResblockListByKeyWord(ShousuoActivity.this,e_tv.getText().toString(),
                        count,callBack);
            }else{
                shuosuo.result.clear();
                s_ap.setData(shuosuo.result);
                s_ap.notifyDataSetChanged();
            }

        }

    };
    private void setEditTextHit() {
        switch (type) {
            case 1:
            case 2:
                e_tv.setHint("请输入楼盘或商圈名称...");
                break;
            case 3:
                e_tv.setHint("请输入房源或商圈名称...");
                break;
            case 4:
                e_tv.setHint("请输入房源名称...");
                break;
            case 5:
                e_tv.setHint("请输入楼盘名称或房源特征...");
                break;
        }
    }
    private HttpRequestAsyncTask.CallBack callBack = new HttpRequestAsyncTask.CallBack() {
        @Override
        public void OnSuccess(String result) {
            shuosuo = new Gson().fromJson(result,Shuosuoshuju.class);


            if (shuosuo.result!=null){

               s_ap.setData(shuosuo.result);
                s_ap.notifyDataSetChanged();
            }

        }

        @Override
        public void OnFailed(String errMsg) {
                ToastUtil.showToast(errMsg);
        }
    };
}
