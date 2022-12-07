package com.zrkaxt.testmachine.httphelper;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.zrkaxt.testmachine.obj.Advertising;
import com.zrkaxt.testmachine.obj.OutputResult;
import com.zrkaxt.testmachine.obj.InstInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpDevice extends HttpBase {
    public void add(String mac,DataHandle<OutputResult> ret) {
        Map<String,String> param=new HashMap<>();
        param.put("mac",mac);
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();
                        OutputResult auth=new OutputResult(info);
                        ret.loadSuccess(auth);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="device/add";
        this.PostJSON(url,param,handler);
    }
    public void getadvertising(DataHandle<List<Advertising>> ret) {
        Map<String,String> param=new HashMap<>();
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONArray info=data.getJSONArry();
                        List<Advertising> auth=Advertising.LoadList(info);
                        ret.loadSuccess(auth);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="device/getadvertising";
        this.PostJSON(url,param,handler);
    }
    public void  devicelogin(DataHandle<String> ret)
    {
        Map<String,String> param=new HashMap<>();
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();
                        OutputResult op=new OutputResult(info);
                        ret.loadSuccess(op.getReturn());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="device/devicelogin";
        this.PostJSON(url,param,handler);
    }

    public void  checklogin(String randomnum,DataHandle<String> ret)
    {
        Map<String,String> param=new HashMap<>();
        param.put("randomnum",randomnum);
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();
                        OutputResult op=new OutputResult(info);
                        ret.loadSuccess(op.getReturn());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="device/checklogin";
        this.PostJSON(url,param,handler);
    }




}
