package com.bwf.p1_landz.iu.onevilla.Adper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ParamListBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/4.
 */

public class Eap extends BaseAdapter{
    private List<ParamListBean> bean;
    private Context context;
    private boolean iserji;

    public Eap (Context context, boolean iserji){
        this.context=context;
        this.iserji =iserji;
    }
    public List<ParamListBean> getItms(){
        return bean;
    }
    public void setItms(List<ParamListBean> bean){
        this.bean=bean;
    }
    @Override
    public int getCount() {
        return bean==null?0:bean.size();
    }

    @Override
    public ParamListBean getItem(int i) {
        return bean==null?null:bean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh =null;
        if (vh==null){
            view= View.inflate(context, R.layout.item_location,null);
            vh =new ViewHolder();
            vh.ll_la= (LinearLayout) view.findViewById(R.id.ll_location);
            vh.tv_x= (TextView) view.findViewById(R.id.tv_itme_loaction);
            vh.im_vw=(ImageView) view.findViewById(R.id.img_nike);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        ParamListBean bn  =bean.get(i);
        vh.tv_x.setText(bn.name);
        if(iserji){
            vh.ll_la.setBackgroundColor(Color.parseColor("#EEEEEE"));
            if(bn.isSelect){
                vh.ll_la.setBackgroundColor(Color.parseColor("#EEEEEE"));
                vh.tv_x.setTextColor(Color.parseColor("#4a2450"));
            }else {
                vh.ll_la.setBackgroundColor(Color.WHITE);
                vh.tv_x.setTextColor(Color.BLACK);
            }
        }
        return view;
    }
    private class  ViewHolder{
        LinearLayout ll_la;
        ImageView im_vw;
        TextView tv_x;
    }
}
