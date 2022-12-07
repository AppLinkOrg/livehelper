package com.zrkaxt.testmachine.obj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Acupoint {

    public Acupoint(JSONObject json) {

        try {
            bodyName=json.getString("bodyName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            bodyId=json.getInt("bodyId");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getBodyName() {
        return bodyName;
    }

    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public int getBodyId() {
        return bodyId;
    }

    public void setBodyId(int bodyId) {
        this.bodyId = bodyId;
    }

    String bodyName;
    int bodyId;




    public static List<Acupoint> LoadList(JSONArray jsonarr) {
        List<Acupoint> list=new ArrayList<>();
        for(int i=0;i<jsonarr.length();i++){
            try {
                JSONObject json=jsonarr.getJSONObject(i);
                Acupoint cookbook=new Acupoint(json);
                list.add(cookbook);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  list;
    }
}
