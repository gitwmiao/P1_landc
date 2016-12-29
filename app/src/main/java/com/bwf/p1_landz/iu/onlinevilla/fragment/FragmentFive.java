package com.bwf.p1_landz.iu.onlinevilla.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bwf.framework.base.BaseFrament;
import com.bwf.framework.image.ImageLoader;
import com.bwf.framework.utils.DisplayUtil;
import com.bwf.p1_landz.R;
import com.bwf.p1_landz.entity.ApartmentImgVoBean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */

public class FragmentFive extends BaseFrament{
    private List<ApartmentImgVoBean> apartmentImgVos;
    private LinearLayout linearLayout;
    private ImageLoader imageLoader;

    private View title;

    public void setApartmentImgVos(List<ApartmentImgVoBean> apartmentImgVos, ImageLoader imageLoader,View view) {
        this.apartmentImgVos = apartmentImgVos;
        this.imageLoader = imageLoader;
        this.title = view;
        initData();
    }

    @Override
    public int getResourceId() {
        return R.layout.fragment_onehouse5;
    }

    @Override
    public void beforInitView() {

    }

    @Override
    public void initView() {
        linearLayout =findViewByIdNoCast(R.id.ll_other_house);

    }

    @Override
    public void initData() {
        if(apartmentImgVos!=null){
            for(final ApartmentImgVoBean apartmentImgVo: apartmentImgVos){
                final ImageView imageView = new ImageView(this.getContext());
                imageView.setPadding(10,0,0,0);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageLoader.displayImg(apartmentImgVo.imgUrl,imageView);
                linearLayout.addView(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final PopupWindow popupWindow =new PopupWindow(getContext());
                        View pup =View.inflate(getContext(),R.layout.pop_other_house,null);
                        popupWindow.setContentView(pup);
                        ImageView pop_Img = (ImageView) pup.findViewById(R.id.img_pop_other_house);
                        imageLoader.displayImg(apartmentImgVo.imgUrl,pop_Img);

                        popupWindow.setWidth(DisplayUtil.getDensity_Width(getContext()));
                        popupWindow.setHeight(DisplayUtil.getDensity_Height(getContext())-title.getHeight());
                        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                        popupWindow.setBackgroundDrawable(new BitmapDrawable());
                        popupWindow.setAnimationStyle(R.style.PopupAnimation);
                        popupWindow.showAsDropDown(title);
                        pup.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });

                    }
                });
            }
        }

    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void onClick(View view) {

    }
}
