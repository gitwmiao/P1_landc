package com.bwf.p1_landz.iu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.db.SearchHistotyModel;
import com.bwf.framework.tools.Constants;
import com.bwf.framework.utils.DisplayUtil;
import com.bwf.framework.utils.DrawableUtils;
import com.bwf.framework.utils.IntentUtils;
import com.bwf.framework.utils.ToastUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.Shuosuoshuju;
import com.bwf.p1_landz.iu.haozaiyj.HaozaiysActivity;
import com.bwf.p1_landz.iu.map.MapActivity;
import com.bwf.p1_landz.iu.onehouse.Onehouse;
import com.bwf.p1_landz.iu.onevilla.OnBuidingAct;
import com.bwf.p1_landz.iu.onlinevilla.OnlineBuildActivity;
import com.bwf.p1_landz.iu.renthouse.RenthouseActivity;
import com.bwf.p1_landz.iu.uitl.AssetsUtil;
import com.bwf.p1_landz.shousuo.ShousuoActivity;


public class MainActivity extends BaseActivity {
    private TextView tv_main_online;//在售
    private TextView tv_main_seebuild;//鉴赏
    private TextView tv_main_wait_rent;//待租
    private TextView tv_main_onehouse;//一手
    private TextView tv_main_map;//地图找房
    private TextView tv_main_study;//豪宅研究
    private TextView tv_main_man;//豪宅顾问
    private TextView tv_main_myhouse;//我的豪宅
    private EditText et_main_search;//豪宅搜索

    private LinearLayout ll_online_house,ll_wait_rent_luxuryhouse,ll_house_appreciate,
            ll_new_luxuryhouse;
    private RelativeLayout ll_map_find_house,ll_luxuryhouse_research,ll_luxuryhouse_consultant
            ,ll_my_luxuryhouse;
    private ViewGroup[] ll_ids ;

    private TextView[] textViews;
    private Integer[] normal_ids = new Integer[]{R.mipmap.main_online_normal, R.mipmap.main_seebuild_normal
            , R.mipmap.main_wait_rent_normal, R.mipmap.main_onehouse_normal,R.mipmap.main_map_normal,
            R.mipmap.main_study_normal,R.mipmap.main_man_normal,R.mipmap.main_myhouse_normal};
    private Integer[] select_ids = new Integer[]{R.mipmap.main_online, R.mipmap.main_seebuild
            , R.mipmap.main_wait_rent, R.mipmap.main_onehouse,R.mipmap.main_map,
            R.mipmap.main_study,R.mipmap.main_man,R.mipmap.main_myhouse};



    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {

        tv_main_online = findViewByIdNoCast(R.id.tv_main_online);
        tv_main_seebuild = findViewByIdNoCast(R.id.tv_main_seebuild);
        tv_main_wait_rent = findViewByIdNoCast(R.id.tv_main_wait_rent);
        tv_main_onehouse = findViewByIdNoCast(R.id.tv_main_onehouse);
        tv_main_map = findViewByIdNoCast(R.id.tv_main_map);
        tv_main_study = findViewByIdNoCast(R.id.tv_main_study);
        tv_main_man = findViewByIdNoCast(R.id.tv_main_man);
        tv_main_myhouse = findViewByIdNoCast(R.id.tv_main_myhouse);
        //搜索
        et_main_search = findViewByIdNoCast(R.id.et_main_search);

        //布局初始化
        ll_online_house = findViewByIdNoCast(R.id.ll_online_house);
        ll_wait_rent_luxuryhouse = findViewByIdNoCast(R.id.ll_wait_rent_luxuryhouse);
        ll_house_appreciate = findViewByIdNoCast(R.id.ll_house_appreciate);
        ll_new_luxuryhouse = findViewByIdNoCast(R.id.ll_new_luxuryhouse);
        ll_map_find_house = findViewByIdNoCast(R.id.ll_map_find_house);
        ll_luxuryhouse_research = findViewByIdNoCast(R.id.ll_luxuryhouse_research);
        ll_luxuryhouse_consultant = findViewByIdNoCast(R.id.ll_luxuryhouse_consultant);
        ll_my_luxuryhouse = findViewByIdNoCast(R.id.ll_my_luxuryhouse);



    }

    @Override
    public void initData() {
        textViews = new TextView[]{tv_main_online,tv_main_seebuild,tv_main_wait_rent,tv_main_onehouse,tv_main_map,tv_main_study
        ,tv_main_man,tv_main_myhouse};
        ll_ids =new ViewGroup[]{ll_online_house,ll_house_appreciate,ll_wait_rent_luxuryhouse,
                ll_new_luxuryhouse,ll_map_find_house,ll_luxuryhouse_research,ll_luxuryhouse_consultant
                ,ll_my_luxuryhouse};
        setOnClick(et_main_search);
        setOnClick(ll_ids);


    }

    @Override
    public void afterInitView() {
        AssetsUtil.getOnlineTypeData(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_online_house:
                setSelect(0);
                IntentUtils.openActivity(MainActivity.this, OnlineBuildActivity.class);
                break;
            case R.id.ll_house_appreciate:
                setSelect(1);
                IntentUtils.openActivity(MainActivity.this, OnBuidingAct.class);
                break;
            case R.id.ll_wait_rent_luxuryhouse:
                setSelect(2);
                IntentUtils.openActivity(MainActivity.this, RenthouseActivity.class);
                break;
            case R.id.ll_new_luxuryhouse:
                setSelect(3);
                IntentUtils.openActivity(MainActivity.this, Onehouse.class);
                break;
            case R.id.ll_map_find_house:
                setSelect(4);
                IntentUtils.openActivity(this, MapActivity.class);
                break;
            case R.id.ll_luxuryhouse_research:
                setSelect(5);
                IntentUtils.openActivity(MainActivity.this, HaozaiysActivity.class);
                break;
            case R.id.ll_luxuryhouse_consultant:
                setSelect(6);
                break;
            case R.id.ll_my_luxuryhouse:
                setSelect(7);
                break;
            case R.id.et_main_search:
                Intent intent = new Intent(this, ShousuoActivity.class);
                intent.putExtra("insert_type",5);
                startActivity(intent);
                break;





        }

    }
    public  void setSelect(int position){
        for (int i =0;i<textViews.length;i++){
            if (i==position){
                textViews[i].setTextColor(Color.parseColor("#fff0cb7e"));
                DrawableUtils.drawableTop(MainActivity.this,textViews[i],select_ids[i]);
            }else {
                textViews[i].setTextColor(Color.WHITE);
                DrawableUtils.drawableTop(MainActivity.this,textViews[i],normal_ids[i]);
            }
        }
    }
    private static final int TIEMS = 2000;

    private boolean isBack = true;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if(isBack){
                ToastUtil.showToast("再次点击退出");
                isBack=false;
                mHandler.sendEmptyMessageDelayed(10001,TIEMS);
            }else {
                finish();
            }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }
    private Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 10001:
                    isBack=true;

                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
