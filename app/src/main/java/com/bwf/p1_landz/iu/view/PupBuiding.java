package com.bwf.p1_landz.iu.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bwf.framework.utils.DisplayUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ParamListBean;
import com.bwf.p1_landz.iu.onevilla.Adper.Eap;

import java.util.List;

/**
 * Created by Administrator on 2016/12/4.
 */

public class PupBuiding extends PopupWindow{
    private boolean isone;
    private ListView ll_1,ll_2;
    private Eap ap1,ap2;
    public PupBuiding(Context context,boolean isone){
        super(context);
        this.isone=isone;
        View view = View.inflate(context, R.layout.pop_location,null);
        this.setContentView(view);
        this.setFocusable(true);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(DisplayUtil.getDensity_Height(context)/ 2+60);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);
        this.setBackgroundDrawable(new BitmapDrawable());
        initView(view,context);
    }
    public  void initView(View view,Context context){
        ll_1 = (ListView) view.findViewById(R.id.lv_location);
        ll_2 =(ListView)view.findViewById(R.id.lv_location2);
        ap1= new Eap(context,false);
        ll_1.setAdapter(ap1);
        if(isone){
            ll_2.setVisibility(View.GONE);
        }else {
            ll_2.setVisibility(View.VISIBLE);
            ap2 =new Eap(context,true);
            ll_2.setAdapter(ap2);
        }
    }
    public void setParamListBean(final List<ParamListBean> bean, final PupBuiding.PupupWindowItemClickCallBack pTsCallBack) {
        if (bean == null && bean.isEmpty()) {
            return;
        }
        ap1.setItms(bean);
        ap1.notifyDataSetChanged();
        ll_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setSelectDatas(bean, i);
                ap1.notifyDataSetChanged();
                if (!isone) {
                    List<ParamListBean> bean2 = bean.get(i).childList;
                    ap2.setItms(bean2);
                    ap2.notifyDataSetChanged();
                }
                if (isone) {//没有第二列数据
                    if (pTsCallBack != null) {
                        //回调回去点击的改对象
                        pTsCallBack.onItemClickCallBack(bean.get(i));
                        dismiss();
                    }
                }

            }
        });
        ll_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setSelectDatas(bean, i);
                ap2.notifyDataSetChanged();
                if (pTsCallBack != null) {
                    //回调回去点击的改对象
                    pTsCallBack.onItemClickCallBack(ap2.getItms().get(i));
                    dismiss();
                }
            }
        });
    }
    public void setPrice_zidingyuView(Context context,final PupBuiding.PupupWindowItemClickCallBack pTsCallBack){
        View footer_price = View.inflate(context,R.layout.select_custom_price_item,null);
      ll_1.addFooterView(footer_price);
        //设置数据获取 点击监听
        final EditText tv_min = (EditText) footer_price.findViewById(R.id.et_min);
        Button button = (Button) footer_price.findViewById(R.id.money_ok);

        final ParamListBean paramListBean = new ParamListBean();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String min_v = tv_min.getText().toString();
                if(min_v != null && min_v.equals("")){
                    paramListBean.minValue = min_v;
                }
                //最大值

                //回调

                if(pTsCallBack != null){
                    pTsCallBack.onItemClickCallBack(paramListBean);
                }

            }
        });
    }

    public void setSelectDatas(List<ParamListBean> bean,int position){
        for (int i=0;i<bean.size();i++){
            if(i==position){
                bean.get(i).isSelect= true;
            }else {
                bean.get(i).isSelect=false;
            }
        }

    }
    public void showPopupWindow(View view){
        if(!this.isShowing()){
            this.showAsDropDown(view);
        }
    }
    public interface PupupWindowItemClickCallBack{
        void onItemClickCallBack(ParamListBean paramListBean);
    }
}
