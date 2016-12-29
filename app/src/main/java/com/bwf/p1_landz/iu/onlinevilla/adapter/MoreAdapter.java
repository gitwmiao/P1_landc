package com.bwf.p1_landz.iu.onlinevilla.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.tools.Constants;
import com.bwf.framework.utils.StringUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.HouseOneArrBean;
import com.bwf.p1_landz.entity.ResultOneHouse;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MoreAdapter extends BaseAdapter{
    private List<HouseOneArrBean> result;
    private Context context;
    private ImageLoader imageLoader;
    public MoreAdapter(Context context, ImageLoader imageLoader){
        this.context =context;
        this.imageLoader=imageLoader;



    }

    public List<HouseOneArrBean> getResult() {
        return result;
    }

    public void setResult(List<HouseOneArrBean> result) {
        this.result = result;
    }


    @Override
    public int getCount() {
        return result==null?0:result.size();
    }

    @Override
    public Object getItem(int i) {
        return result==null?0:result.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder =null;
        if(view ==null){
            viewHolder = new ViewHolder();
            view =View.inflate(context, R.layout.zaixian_two,null);
            viewHolder.zai_im_two = (ImageView) view.findViewById(R.id.zai_im_two);
            viewHolder.zai_tv_two =(TextView) view.findViewById(R.id.zai_tv_two);
            viewHolder.tv_type =(TextView) view.findViewById(R.id.tv_type);
            viewHolder.zai_tv_two_3 =(TextView) view.findViewById(R.id.zai_tv_two_3);
            viewHolder.zai_tv_two_4 =(TextView) view.findViewById(R.id.zai_tv_two_4);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (view!=null){
            HouseOneArrBean arrBean =  result.get(i);
            viewHolder.zai_tv_two.setText(arrBean.resblockOneName);
            viewHolder.zai_tv_two_4.setText(StringUtils.doubleFormat(arrBean.totalprBegin)+ "-"
                     +StringUtils.doubleFormat(arrBean.totalprEnd)+ "万");
            viewHolder.zai_tv_two_3.setText(arrBean.bedroomAmount+ "室" +arrBean.parlorAmount+ "厅"
                     +StringUtils.doubleFormat(arrBean.buildSize)+ "㎡"
                     + StringUtils.doubleFormat(arrBean.unitprBegin) + "-"
                     + StringUtils.doubleFormat(arrBean.unitprEnd) + "万/㎡");
            viewHolder.zai_im_two.setImageResource(R.mipmap.defult_twopic);
            imageLoader.displayImg(arrBean.titlepicImg+ Constants.IMG_URL_SUFFIX_ONLINE_LIST_ONE,viewHolder.zai_im_two);
        }

        return view;
    }
    private class ViewHolder{
        ImageView zai_im_two;
        TextView zai_tv_two;
        TextView tv_type;
        TextView zai_tv_two_3;
        TextView zai_tv_two_4;
    }
}
