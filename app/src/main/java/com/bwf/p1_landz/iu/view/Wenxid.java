package com.bwf.p1_landz.iu.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bwf.p1_landz.R;

/**
 * Created by Administrator on 2016/12/21.
 */

public class Wenxid extends Dialog implements View.OnClickListener{
    private TextView tv_content = null;
    private String content;
    private View.OnClickListener onClick = null;


    public Wenxid(Context context, int themeResId,View.OnClickListener onClickListener) {
        super(context, themeResId);
        onClick = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixin);
        setCanceledOnTouchOutside(true);
        findViewById(R.id.weixin_o).setOnClickListener(this);
        findViewById(R.id.pengyou_o).setOnClickListener(this);
        findViewById(R.id.duanxin_o).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.webView:

                break;
            case R.id.pengyou_o:

                break;
            case R.id.duanxin_o:
                break;
        }

    }
}
