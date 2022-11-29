package com.zrkaxt.testmachine.httphelper;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.zrkaxt.testmachine.obj.AppVersion;
import com.zrkaxt.testmachine.obj.InstInfo;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HttpInst extends HttpBase {
    public void info(DataHandle<InstInfo> ret) {
        Map<String,String> param=new HashMap<>();
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();
                        InstInfo auth=new InstInfo(info);
                        ret.loadSuccess(auth);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="inst/info";
        this.PostJSON(url,param,handler);
    }


    public void appupdate(DataHandle<AppVersion> ret) {

        Map<String,String> param=new HashMap<>();




        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();
                        AppVersion auth=new AppVersion(info);
                        ret.loadSuccess(auth);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="inst/appupdate";
        this.PostJSON(url,param,handler);
    }
}
