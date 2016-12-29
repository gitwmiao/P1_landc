package com.bwf.p1_landz.iu.onlinevilla.fragment;


import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.framework.base.BaseFrament;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.framework.utils.ListViewUtils;
import com.bwf.framework.utils.LogUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.GuWenResultBean;
import com.bwf.p1_landz.iu.onlinevilla.Moreadviser;
import com.bwf.p1_landz.iu.onlinevilla.adapter.FragmentThreeadapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class FragmentThree extends BaseFrament{
    private TextView tv_guwen;

    private ListView lv_guwen;

    private GuWenResultBean.GuWenBean result;

    private ImageLoader imageLoader;

    private String resourceId;
    private String type;

    private View deiver;
    public void setResult(GuWenResultBean.GuWenBean result, String resourceId, ImageLoader imageLoader) {
        this.result = result;
        this.resourceId = resourceId;
        this.imageLoader = imageLoader;
        initData();
    }
    @Override
    public int getResourceId() {
        return R.layout.fragment_onehouse3;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        tv_guwen = findViewByIdNoCast(R.id.tv_guwen);
        lv_guwen = findViewByIdNoCast(R.id.lv_guwen);
        deiver = findViewByIdNoCast(R.id.devier);
        setOnClick(R.id.tv_guwen);
    }

    @Override
    public void initData() {
        if (result != null && result.showArr != null && result.showArr.size() > 1) {
            deiver.setVisibility(View.VISIBLE);
            lv_guwen.setVisibility(View.VISIBLE);
            tv_guwen.setVisibility(View.VISIBLE);
            String guwen = String.format(getString(R.string.guwen), "" + result.totalAmount);
            tv_guwen.setText(Html.fromHtml(guwen));
            FragmentThreeadapter guWenAdapter = new FragmentThreeadapter(getContext(), result.showArr, imageLoader);
            lv_guwen.setAdapter(guWenAdapter);
            ListViewUtils.measureListViewHeight(lv_guwen);
        }else{
            //如果没有内容  隐藏这个模块
            deiver.setVisibility(View.GONE);
            lv_guwen.setVisibility(View.GONE);
            tv_guwen.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_guwen:
                Bundle bundle = new Bundle();
                bundle.putString("resourceId", resourceId);

                if (result != null) {
                    bundle.putString("totalAmount", result.totalAmount);
                }
                IntentUtils.openActivity(getActivity(), Moreadviser.class, bundle);
                LogUtils.i("frament3","点击");
                break;
        }
    }
}
