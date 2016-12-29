package com.bwf.p1_landz.shousuo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.Shuosuoshuju;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/11.
 */

public class ShousuoAdapeter extends BaseAdapter{
    private Context context;
    private List<Shuosuoshuju.SearchKeyWordDetail> data = new ArrayList<Shuosuoshuju.SearchKeyWordDetail>();
    public ShousuoAdapeter(Context mContext) {
        this.context = mContext;
    }

    public void setData(List<Shuosuoshuju.SearchKeyWordDetail> data) {
        this.data = data;
    }
    @Override
    public int getCount() {
        if(data!=null&&data.size()!=0){
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(
                    R.layout.item_search_value, null);
            holder.tv_search_val = (TextView) view
                    .findViewById(R.id.tv_search_val);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_search_val.setText(data.get(i).name);
        return view;
    }
    class ViewHolder {
        TextView tv_search_val;
    }
}
