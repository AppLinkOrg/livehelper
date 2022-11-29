package com.zrkaxt.testmachine.httphelper;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.zrkaxt.testmachine.obj.InstInfo;
import com.zrkaxt.testmachine.obj.OutputResult;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HttpUpload extends HttpBase {
    public void upload(String module,String filepath,DataHandle<OutputResult> ret) {

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

        this.UploadFile(module,filepath,handler);
    }
}
