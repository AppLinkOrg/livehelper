package com.zrkaxt.testmachine.obj;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.zrkaxt.testmachine.helper.WifiHelper;
import com.zrkaxt.testmachine.httphelper.HttpReturnData;

public class User {
    String refresh_verify="";

    long deviceid=0;
    private static User Instance;
    private User(){
    }
    public static User GetInstance(){
        if(User.Instance==null){
            User.Instance=new User();
        }
        return  User.Instance;
    }
    public void init(Context ctx){
        SharedPreferences mPerferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        refresh_verify = mPerferences.getString("refresh_verify","");
    }
    public boolean isLogined(){
        if(refresh_verify.equals("")==false){
            return true;
        }
        return false;
    }

    public void logout(Context ctx) {
        this.refresh_verify="";
        SharedPreferences mPerferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = mPerferences.edit();
        editor.putString("refresh_verify", "");
        editor.apply();
    }
}
