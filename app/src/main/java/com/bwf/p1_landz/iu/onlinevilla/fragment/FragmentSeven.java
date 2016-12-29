package com.bwf.p1_landz.iu.onlinevilla.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bwf.framework.base.BaseFrament;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.framework.utils.ListViewUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ResultOneHouse;
import com.bwf.p1_landz.iu.onlinevilla.Morehouseone;
import com.bwf.p1_landz.iu.onlinevilla.adapter.SevenAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/12/14.
 */

public class FragmentSeven extends BaseFrament {
    @BindView(R.id.fr7_tv)
    TextView fr7Tv;
    @BindView(R.id.fr7_vw)
    View fr7Vw;
    @BindView(R.id.fr7_lv)
    ListView fr7Lv;
    private String houseOneId, resblockOneId,resblockId;
    private ImageLoader imageLoader;
    private List<ResultOneHouse> result;
    private TextView fr7_tv;
    private ListView fr7_lv;
    private View fr7_vw;
    private int pagNo;

    public void setResult(String houseOneId, String resblockId, List<ResultOneHouse> result, ImageLoader imageLoader) {
        this.houseOneId = houseOneId;
        this.resblockId = resblockId;
        this.result = result;
        this.imageLoader = imageLoader;
        initData();


    }

    @Override
    public int getResourceId() {
        return R.layout.fragment_onehouse7;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        fr7Tv = findViewByIdNoCast(R.id.fr7_tv);
        fr7Lv = findViewByIdNoCast(R.id.fr7_lv);
        fr7Vw = findViewByIdNoCast(R.id.fr7_vw);
        setOnClick(R.id.fr7_tv);

    }

    @Override
    public void initData() {
            fr7Tv.setVisibility(View.VISIBLE);
            fr7Lv.setVisibility(View.VISIBLE);
            fr7Vw.setVisibility(View.VISIBLE);
            SevenAdapter adater = new SevenAdapter(this.getContext(),imageLoader);
            adater.setResult(result);
            fr7Lv.setAdapter(adater);
            ListViewUtils.measureListViewHeight(fr7Lv);
        fr7Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fr7_tv:
                Bundle bundle =new Bundle();
                bundle.putString("houseOneId",houseOneId);
                bundle.putString("resblockId",resblockId);
                bundle.putInt("pageNo",0);
              IntentUtils.openActivity(getActivity(), Morehouseone.class,bundle);

                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
