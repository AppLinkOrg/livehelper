package com.zrkaxt.testmachine.obj;

import org.json.JSONObject;

public class NotifyCode {
    String NotifyCode;

    public  String getNotifyCode(){
        return  NotifyCode;
    }

    public  NotifyCode(String data){
        try {
            NotifyCode=data;


        }catch (Exception e){

        }

    }
}
