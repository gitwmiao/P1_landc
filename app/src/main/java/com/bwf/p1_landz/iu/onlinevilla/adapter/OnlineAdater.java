package com.bwf.p1_landz.iu.onlinevilla.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.tools.Constants;
import com.bwf.framework.utils.StringUtils;

import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseArrBean;
import com.bwf.p1_landz.entity.HouseOneArrBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/6.
 */

public class OnlineAdater extends BaseAdapter {
    private Context context;


    private List<Object> andlist;

    private int type_01 = 0;//houseArr

    private int type_02 = 1;//houseOneArr

    private ImageLoader imageLoader;
    private boolean isClick=false;
    private int cont =0;

    public OnlineAdater(Context context) {
        this.context = context;
        imageLoader = new ImageLoader();
    }
    public OnlineAdater(Context context, List<Object> andlist, ImageLoader imageLoader) {
        this.context = context;
        this.imageLoader = imageLoader;
        this.andlist = new ArrayList<>();
        if (andlist != null && !andlist.isEmpty()) {
            for (int i = 1; i < andlist.size(); i++) {
                this.andlist.add(andlist.get(i));
            }
        }

    }
    public void addTotaList(List<Object> andlist){
        this.andlist.addAll(andlist);
    }
    public void setTotalList(List<Object> andlist) {
        this.andlist = andlist;
    }
    public List<Object> getTotalList() {
        return andlist;
    }

    @Override
    public int getCount() {
        return andlist==null ? 0 :andlist.size();
    }

    @Override
    public Object getItem(int i) {
        return andlist==null ? null : andlist.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public int getItemViewType(int i) {
        if (andlist.get(i) instanceof HouseArrBean)
            return type_01;
        else
            return type_02;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder =null;
        ViewHolder2 viewHolder2 =null;
        int type =getItemViewType(i);
        if(view==null){
            if(type==type_01){
                viewHolder =new ViewHolder();
                view = View.inflate(context, R.layout.zaixian_one,null);
                viewHolder.zai_im= (ImageView) view.findViewById(R.id.zai_im);
                viewHolder.tv_two_twocontent= (TextView) view.findViewById(R.id.tv_two_twocontent);
                viewHolder.tv_two_twoName= (TextView) view.findViewById(R.id.tv_two_twoName);
                viewHolder.ll_bottom_label= (LinearLayout) view.findViewById(R.id.ll_bottom_label);
                viewHolder.tv_detail_tese1= (TextView) view.findViewById(R.id.tv_detail_tese1);
                viewHolder.tv_detail_tese2= (TextView) view.findViewById(R.id.tv_detail_tese2);
                viewHolder.tv_detail_tese3= (TextView) view.findViewById(R.id.tv_detail_tese3);
                viewHolder.tv_two_twoarea= (TextView) view.findViewById(R.id.tv_two_twoarea);
                viewHolder.tv_two_twomoney= (TextView) view.findViewById(R.id.tv_two_twomoney);
                viewHolder.btn_newhouse_compare = (CheckedTextView) view.findViewById(R.id.btn_newhouse_compare);
                view.setTag(viewHolder);

            }else if (type==type_02){
                viewHolder2 = new ViewHolder2();
                view =View.inflate(context, R.layout.zaixian_two,null);
                viewHolder2.zai_im_two = (ImageView) view.findViewById(R.id.zai_im_two);
                viewHolder2.zai_tv_two =(TextView) view.findViewById(R.id.zai_tv_two);
                viewHolder2.tv_type =(TextView) view.findViewById(R.id.tv_type);
                viewHolder2.zai_tv_two_3 =(TextView) view.findViewById(R.id.zai_tv_two_3);
                viewHolder2.zai_tv_two_4 =(TextView) view.findViewById(R.id.zai_tv_two_4);
                viewHolder2.btn_newhouse_compare = (CheckedTextView) view.findViewById(R.id.btn_newhouse_compare1);
                view.setTag(viewHolder2);
            }
        } else {
            if(type == type_01){
                viewHolder = (ViewHolder) view.getTag();
            }else if(type== type_02){
                viewHolder2 = (ViewHolder2) view.getTag();
            }
        }
        if(type==type_01){
            final HouseArrBean arrBean = (HouseArrBean) andlist.get(i);
            viewHolder.tv_two_twocontent.setText(arrBean.salesTrait);
            viewHolder.tv_two_twoName.setText(arrBean.resblockName+""+arrBean.circleTypeName);
            viewHolder.tv_two_twomoney.setText(StringUtils.doubleFormat(arrBean.totalPrices)+"万");
            viewHolder.tv_two_twoarea.setText(arrBean.bedroomAmount+"室"+arrBean.parlorAmount+"厅"
            +StringUtils.doubleFormat(arrBean.buildSize)+"㎡");
            showLabel(arrBean.houseLabel,viewHolder);
            viewHolder.zai_im.setImageResource(R.mipmap.defult_onepic);
            imageLoader.displayImg(arrBean.titleImg+ Constants.IMG_URL_SUFFIX_ONLINE_LIST_TWO,viewHolder.zai_im);
            if (isClick){
                viewHolder.btn_newhouse_compare.setVisibility(View.VISIBLE);
                if(arrBean.isSelect){
                    viewHolder.btn_newhouse_compare.setChecked(true);

                }else {
                    viewHolder.btn_newhouse_compare.setChecked(false);
                }
            }else {
                viewHolder.btn_newhouse_compare.setVisibility(View.GONE);
            }
            viewHolder.btn_newhouse_compare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckedTextView ct = (CheckedTextView) view;
                    if(ct.isChecked()){
                        ct.setChecked(false);
                        arrBean.isSelect =false;
                        cont--;
                    }else {
                        if(cont>=5){
                            ToastUtil.showToast("太多了");
                            return;
                        }
                        ct.setChecked(true);
                        arrBean.isSelect=true;
                        cont++;
                    }

                }
            });

        }else if(type == type_02){
            final HouseOneArrBean arrBean1 = (HouseOneArrBean) andlist.get(i);
            viewHolder2.zai_tv_two.setText(arrBean1.resblockOneName);
            viewHolder2.zai_tv_two_4.setText(StringUtils.doubleFormat(arrBean1.totalprBegin)+ "-"
                    +StringUtils.doubleFormat(arrBean1.totalprEnd)+ "万");
            viewHolder2.zai_tv_two_3.setText(arrBean1.bedroomAmount+ "室"+ " "+ arrBean1.parlorAmount+ "厅"
            + "   " +StringUtils.doubleFormat(arrBean1.buildSize) + "㎡"+" "
                    + StringUtils.doubleFormat(arrBean1.unitprBegin) + "-"
                    + StringUtils.doubleFormat(arrBean1.unitprEnd) + "万/㎡");
            viewHolder2.zai_im_two.setImageResource(R.mipmap.defult_twopic);
            imageLoader.displayImg(arrBean1.titlepicImg+Constants.IMG_URL_SUFFIX_ONLINE_LIST_ONE,viewHolder2.zai_im_two);
            if(isClick){
                viewHolder2.btn_newhouse_compare.setVisibility(View.VISIBLE);
                if (arrBean1.isSelect){
                    viewHolder2.btn_newhouse_compare.setChecked(true);
                }else {
                    viewHolder2.btn_newhouse_compare.setChecked(false);
                }
            }else {
                viewHolder2.btn_newhouse_compare.setVisibility(View.GONE);
            }

            viewHolder2.btn_newhouse_compare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckedTextView ct = (CheckedTextView) view;
                    if(ct.isChecked()){
                        ct.setChecked(false);
                        arrBean1.isSelect =false;
                        cont--;
                    }else {
                        if(cont>=5){
                            ToastUtil.showToast("太多了");
                            return;
                        }
                        ct.setChecked(true);
                        arrBean1.isSelect=true;
                        cont++;
                    }

                }
            });
        }
        return view;
    }
    private class ViewHolder {
        ImageView zai_im;
        TextView tv_two_twocontent;
        TextView tv_two_twoName;
        LinearLayout ll_bottom_label;
        TextView tv_detail_tese1;
        TextView tv_detail_tese2;
        TextView tv_detail_tese3;
        TextView tv_two_twoarea;
        TextView tv_two_twomoney;
        CheckedTextView btn_newhouse_compare;



    }
    private class ViewHolder2{
        ImageView zai_im_two;
        TextView zai_tv_two;
        TextView tv_type;
        TextView zai_tv_two_3;
        TextView zai_tv_two_4;
        CheckedTextView btn_newhouse_compare;
    }
    private void showLabel(String label, ViewHolder holder) {
        if (!TextUtils.isEmpty(label)) {
            String[] arr = label.trim().split(" ");
            if (arr.length >= 3) {
                holder.tv_detail_tese1.setText(arr[0]);
                holder.tv_detail_tese1.setVisibility(View.VISIBLE);
                holder.tv_detail_tese2.setText(arr[1]);
                holder.tv_detail_tese2.setVisibility(View.VISIBLE);
                holder.tv_detail_tese3.setText(arr[2]);
                holder.tv_detail_tese3.setVisibility(View.VISIBLE);
            } else if (arr.length == 2) {
                holder.tv_detail_tese1.setText(arr[0]);
                holder.tv_detail_tese1.setVisibility(View.VISIBLE);
                holder.tv_detail_tese2.setText(arr[1]);
                holder.tv_detail_tese2.setVisibility(View.VISIBLE);
            } else if (arr.length == 1) {
                holder.tv_detail_tese1.setText(arr[0]);
                holder.tv_detail_tese1.setVisibility(View.VISIBLE);
            }
        }
    }
    public void setClickOn(boolean isDian){
       isClick=isDian;
    }
}
