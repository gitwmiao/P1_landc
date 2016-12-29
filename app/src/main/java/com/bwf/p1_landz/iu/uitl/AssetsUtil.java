package com.bwf.p1_landz.iu.uitl;

import android.content.Context;
import android.content.res.AssetManager;

import com.bwf.framework.utils.LogUtils;
import com.bwf.p1_landz.MyApplication;
import com.bwf.p1_landz.entity.OnlineTypeBean;
import com.google.gson.Gson;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/11/30.
 */

public class AssetsUtil {
    public static void getOnlineTypeData(final Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager assetManager = context.getAssets();
                try{
                    InputStream in =assetManager.open("study_type.txt");
                    byte[] bytes =new byte[in.available()];
                    in.read(bytes);
                    String result =new String(bytes,"utf-8");
                    OnlineTypeBean onlineTypeBean =  new Gson().fromJson(result,OnlineTypeBean.class);
                    MyApplication.getApplication().setOnlineTypeBean(onlineTypeBean);
                    LogUtils.e("msg",onlineTypeBean.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
