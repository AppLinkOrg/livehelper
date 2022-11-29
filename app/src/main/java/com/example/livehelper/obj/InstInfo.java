package com.zrkaxt.testmachine.obj;

import org.json.JSONException;
import org.json.JSONObject;

public class InstInfo {

    int id;
    String unicode,name,appversion;

    public int getId() {
        return id;
    }

    public String getUnicode() {
        return unicode;
    }

    public String getName() {
        return name;
    }

    public String getAppversion() {
        return appversion;
    }

    public InstInfo(JSONObject json) {
        try {
            id=json.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            unicode=json.getString("unicode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            name=json.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            appversion=json.getString("appversion");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
