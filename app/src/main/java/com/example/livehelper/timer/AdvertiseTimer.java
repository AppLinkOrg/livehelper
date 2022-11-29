package com.zrkaxt.testmachine.timer;


import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.zrkaxt.testmachine.StartApplication;
import com.zrkaxt.testmachine.SuperActivity;
import com.zrkaxt.testmachine.config.GlobalVar;
import com.zrkaxt.testmachine.mycomponent.AdvertisingWindow;

public class AdvertiseTimer {
    long lastupdatetime=0;
    static AdvertiseTimer Instance;
    private AdvertiseTimer(){
        updateLastTouch();
        StartTimer();
    }
    public static AdvertiseTimer GetInstance(){
        if(Instance==null){
            Instance=new AdvertiseTimer();
        }
        return Instance;
    }
    public void updateLastTouch() {
        Log.d("AdvertiseTimer","updateLastTouch ");
        this.lastupdatetime=System.currentTimeMillis();
    }

    void StartTimer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (GlobalVar.ThreadFlag){
                    long now=System.currentTimeMillis();
                    long interval=(now-lastupdatetime)/1000;
                    Log.d("AdvertiseTimer",String.valueOf(now)+"?"+String.valueOf(lastupdatetime));
                    Log.d("AdvertiseTimer2",String.valueOf(interval)+"?"+String.valueOf(GlobalVar.AdvertisingInterval()));
                    if(interval>GlobalVar.AdvertisingInterval()){
                        updateLastTouch();
                        updateHandle.sendMessage(new Message());
                    }
                    try {
                        Thread.sleep(10*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    Handler updateHandle=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            StartApplication.Instance.showAdvertising();
        }
    };

}
