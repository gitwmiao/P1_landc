package com.bwf.p1_landz.iu.onlinevilla;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bwf.framework.base.BaseActivity;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.LogUtils;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.GalleyBean;
import com.bwf.p1_landz.entity.ImgUrlArrBean;
import com.bwf.p1_landz.iu.onlinevilla.adapter.LookPhotoAdapter;
import com.bwf.p1_landz.iu.onlinevilla.adapter.TextViewAdapter;
import com.bwf.p1_landz.iu.onlinevilla.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

import static com.bwf.p1_landz.R.id.ll_list;

/**
 * Created by Administrator on 2016/12/19.
 */

public class Lookphonto extends BaseActivity{
    private ArrayList<ImgUrlArrBean> imgUrlArrBeen;
    private ImageLoader imageLoader;
    private FragmentTwo fragmentTwo;
    private Gallery gallery;
    private List<GalleyBean> galleryBens;
    private TextViewAdapter tvap;
    int gallery_current =-1;
    int num;
    private boolean isPageSelect =true,isItemSelect;
    private ImageView imageView;
    private LinearLayout layout;
    private ScrollView scrollView;

    public Lookphonto() {
    }

    @Override
    public int getContentViewId() {
        return R.layout.look_house;
    }

    @Override
    public void beforInitView() {
        imgUrlArrBeen =getIntent().getParcelableArrayListExtra("imgUrlArr");
        LogUtils.i("123456",imgUrlArrBeen.toString());


    }

    @Override
    public void initView() {
        fragmentTwo = (FragmentTwo) getSupportFragmentManager().findFragmentById(R.id.detail_fragment02);
        imageView =findViewByIdNoCast(R.id.im_look);
        gallery = (Gallery) findViewById(R.id.gallerg_str);
        layout =findViewByIdNoCast(ll_list);
        scrollView =findViewByIdNoCast(R.id.scrollView);
        setOnClick(R.id.im_look);


    }

    @Override
    public void initData() {
        imageLoader =new ImageLoader();
        fragmentTwo.setImgUrlArr(imgUrlArrBeen,false,false,imageLoader);
        tvap =new TextViewAdapter(this);
        gallery.setAdapter(tvap);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (gallery.getChildAt(i) != null) {
                    RelativeLayout rl = (RelativeLayout) gallery.getChildAt(i);
                    TextView tv1 = (TextView) rl.getChildAt(0);
                    TextPaint tp = tv1.getPaint();
                    tp.setFakeBoldText(true);//设置加粗
                    tv1.setTextSize(22);
                }
                if (gallery_current != -1 && gallery.getChildAt(gallery_current) != null) {
                    RelativeLayout rl1 = (RelativeLayout) gallery.getChildAt(gallery_current);
                    TextView tv2 = (TextView) rl1.getChildAt(0);
                    TextPaint tp1 = tv2.getPaint();
                    tp1.setFakeBoldText(false);//设置不加粗
                    tv2.setTextSize(20);
                }
                gallery_current =i;
                isItemSelect =true;
                if(!isPageSelect){
                    int pos =galleryBens.get(i).pos+num*imgUrlArrBeen.size();
                    fragmentTwo.setCurrentItem(pos);

                }
                isItemSelect =false;
            }

        });
        fragmentTwo.setOnPageSelectListener(onPageSelectListener);


    }
    private  FragmentTwo.OnPageSelectListener onPageSelectListener =new FragmentTwo.OnPageSelectListener(){

        @Override
        public void onPageSelected(int position) {
            num =position/imgUrlArrBeen.size();
            isPageSelect =true;
            if(!isItemSelect){
                ImgUrlArrBean imgUrlArrBean = imgUrlArrBeen.get(position%imgUrlArrBeen.size());
                String tpye =imgUrlArrBean.picType;
                for(int i =0;i<galleryBens.size();i++){
                    if(tpye.equals(galleryBens.get(i).picType)){
                        gallery.setSelection(i);
                        break;
                    }
                }

            }
            isPageSelect =false;

        }
    };

    @Override
    public void afterInitView() {
        addGallleyBeans();

    }

    @Override
    public void onClick(View view) {
        if(view == imageView){
            if(scrollView.getVisibility() == View.GONE){
                scrollView.setVisibility(View.VISIBLE);
                findViewById(R.id.lv_look).setVisibility(View.GONE);
                gallery.setVisibility(View.GONE);
                imageView.setImageResource(R.mipmap.thum_close);
            }else{
                scrollView.setVisibility(View.GONE);
                findViewById(R.id.lv_look).setVisibility(View.VISIBLE);
                gallery.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.mipmap.list_icon);
            }
        }

    }
    public void addGallleyBeans(){
        galleryBens = new ArrayList<>();
        for (int i = 0;i < imgUrlArrBeen.size(); i++){
            //去重
            boolean falg = true;
            for(GalleyBean galleyBean : galleryBens){
                if(galleyBean.picType.equals(imgUrlArrBeen.get(i).picType)){
                    falg = false;
                }
            }

            if(falg){//没有重复
                GalleyBean galleyBean = new GalleyBean(i,imgUrlArrBeen.get(i).picType,getTypeName(imgUrlArrBeen.get(i).picType));
                galleryBens.add(galleyBean);
                addView(i);//有几个Gallery对象 就添加表格几行
            }

        }
        //添加数据到适配器和刷新适配器
        tvap.setDatas(galleryBens);
        tvap.notifyDataSetChanged();
    }
    public String getTypeName(String type){
        if(type.equals("1")){
            return "外景图";
        }
        if(type.equals("2")){
            return "地理位置图";
        }
        if(type.equals("3")){
            return "座栋分布图";
        }
        if(type.equals("4")){
            return "户型图";
        }
        if(type.equals("5")){
            return "样板间";
        }
        if(type.equals("6")){
            return "实勘图";
        }
        return  "";
    }


    public void addView(int pos){
        //添加TextView
        TextView textView = new TextView(this);
        textView.setPadding(10,10,10,10);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(24);
        //设置样式内容
        textView.setText(getTypeName(imgUrlArrBeen.get(pos).picType));
        layout.addView(textView);
        //添加ImageView

        //当前这一列的ImageView的数据
        List<ImgUrlArrBean> new_imgs = new ArrayList<>();
        for(ImgUrlArrBean imgUrlArrBean : imgUrlArrBeen){
            if(imgUrlArrBean.picType.equals(imgUrlArrBeen.get(pos).picType)){
                new_imgs.add(imgUrlArrBean);
            }
        }
        //使用RecyclerView
        //1.实例化RecyclerView
        RecyclerView recyclerView = new RecyclerView(this);
        //2.为RecyclerView设置LayoutManger
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(linearLayoutManager);
        //3.为RecyclerView设置Adapater
        LookPhotoAdapter adapter = new LookPhotoAdapter(this,new_imgs,imageLoader);
        recyclerView.setAdapter(adapter);

        //把RecyclerView添加到LinearLayout
        layout.addView(recyclerView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (scrollView.getVisibility() == View.VISIBLE) {
                scrollView.setVisibility(View.GONE);
                findViewById(R.id.lv_look).setVisibility(View.VISIBLE);
                gallery.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.mipmap.list_icon);

                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
