package com.zrkaxt.testmachine.mycomponent;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.entity.BaseBannerInfo;
import com.zrkaxt.testmachine.MainActivity;
import com.zrkaxt.testmachine.R;
import com.zrkaxt.testmachine.StartApplication;
import com.zrkaxt.testmachine.config.ApiConfig;
import com.zrkaxt.testmachine.config.GlobalVar;
import com.zrkaxt.testmachine.helper.FileUtil;
import com.zrkaxt.testmachine.obj.BannerInfo;
import com.zrkaxt.testmachine.timer.AdvertiseTimer;

import java.util.ArrayList;
import java.util.List;


public class AdvertisingWindow extends LinearLayout {

    public static boolean FLAGS = true;

    private WindowManager wm;
    public WindowManager.LayoutParams wmParams;
    public XBanner xbanner;

    public AdvertisingWindow(Context context) {
        this(context, null);
        initView();
        initEvent();
        //startThread();

    }

    public WindowManager getWm() {
        return wm;
    }

    public void setWm(WindowManager wm) {
        this.wm = wm;
    }

    public WindowManager.LayoutParams getWmParams() {
        return wmParams;
    }

    public void setWmParams(WindowManager.LayoutParams wmParams) {
        this.wmParams = wmParams;
    }

    public AdvertisingWindow(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        initView();
        initEvent();
        //startThread();
    }

    public AdvertisingWindow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenHeight = dm.heightPixels;
        int screenWidth = dm.widthPixels;

        initView();
        initEvent();
        //startThread();
    }

    public void initView() {
        //
    }

    public void initEvent() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseAdvertising();
            }
        });
    }

    void CloseAdvertising(){
        AdvertiseTimer.GetInstance().updateLastTouch();
        GlobalVar.setMember(null);
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getContext().startActivity(intent);
        StartApplication.Instance.hideAdvertising();
    }

    public void initData(){
        if(xbanner==null){
            xbanner=(XBanner)findViewById(R.id.xbanner);
            List<BaseBannerInfo> bannerlist = new ArrayList<BaseBannerInfo>();
            for (int i = 0; i < GlobalVar.GetAdvertisingList().size(); i++) {
                String url=ApiConfig.GetUploadPath()+"advertising/"+GlobalVar.GetAdvertisingList().get(i).getPic();
                Log.d("advertising",url);
                bannerlist.add(new BannerInfo(url));
            }

            xbanner.setBannerData(bannerlist);
            xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    BaseBannerInfo urlban = (BaseBannerInfo) model;
                    ImageView img = (ImageView) view;
                    Log.d("advertising2",(String) urlban.getXBannerUrl());
                    FileUtil.GetInstance().loadImageFromUrl(img, (String) urlban.getXBannerUrl());
                }
            });
            xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, Object model, View view, int position) {
                    CloseAdvertising();
                }
            });
        }
    }
//
//    void startThread(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (GlobalVar.ThreadFlag){
//                    Message m=new Message();
//                    updateHandle.sendMessage(m);
//                    try {
//                        Thread.sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }

//    Handler updateHandle=new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            if(GlobalVar.AdvertisingShow){
//                setVisibility(VISIBLE);
//                Log.d("showadvertising","true");
//            }else{
//                setVisibility(GONE);
//            }
//        }
//    };

}
