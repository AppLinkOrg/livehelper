package com.zrkaxt.testmachine.httphelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpReturnData {
    int responseCode=0;
    String json;
    String error="";

    public int getResponseCode() {
        return responseCode;
    }

    public HttpReturnData(int responseCode, String json) {
        this.responseCode = responseCode;
        this.json =json;
    }

    public HttpReturnData(int responseCode, String json, String error) {
        this(responseCode,json);
        this.error=error;
    }


    public boolean loadDataSuccess(){
        return responseCode==200;
    }


    public JSONObject getJSON() throws JSONException {
        if(json.equals("null"))
        {
            return  null;
        }
        return new JSONObject(json);
    }
    public JSONArray getJSONArry() throws JSONException {
        if(json.equals("null"))
        {
            return  null;
        }
        return new JSONArray(json);
    }
    public String getString() {
        return json;
    }

}
