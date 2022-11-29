package com.zrkaxt.testmachine.obj;

import com.zrkaxt.testmachine.timer.AdvertiseTimer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Advertising {


    public static List<Advertising> LoadList(JSONArray jsonarr) {
        List<Advertising> list=new ArrayList<>();
        for(int i=0;i<jsonarr.length();i++){
            try {
                JSONObject json=jsonarr.getJSONObject(i);
                Advertising cookbook=new Advertising(json);
                list.add(cookbook);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  list;
    }

    String pic;
    public Advertising(JSONObject json){
        try {
            pic=json.getString("pic");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPic() {
        return pic;
    }
}
