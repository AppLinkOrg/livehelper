package com.zrkaxt.testmachine.obj;

import org.json.JSONException;
import org.json.JSONObject;

public class Member {
    int id;
    String openid;
    String avatarUrl;
    String nickName;
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }



    public int getId() {
        return id;
    }

    public String getOpenid() {
        return openid;
    }

    public  Member(JSONObject json){
        try {
            id=json.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            openid=json.getString("openid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            avatarUrl=json.getString("avatarUrl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            nickName=json.getString("nickName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
