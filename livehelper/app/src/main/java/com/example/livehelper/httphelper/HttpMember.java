package com.zrkaxt.testmachine.httphelper;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zrkaxt.testmachine.config.GlobalVar;
import com.zrkaxt.testmachine.obj.InstInfo;
import com.zrkaxt.testmachine.obj.Member;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HttpMember extends HttpBase {

    public void info(String openid,DataHandle<Member> ret) {

        Map<String,String> param=new HashMap<>();
        param.put("member_id",openid);
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()&&data!=null) {
                    try {
                        JSONObject info=data.getJSON();
                        Member auth=new Member(info);
                        ret.loadSuccess(auth);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="member/info";
        this.PostJSON(url,param,handler);
    }

}
