package com.zrkaxt.testmachine.obj;

import org.json.JSONException;
import org.json.JSONObject;

public class OutputResult {
    String code,result,_return;

    public String getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

    public String getReturn() {
        return _return;
    }

    public OutputResult(JSONObject json) {
        try {
            code=json.getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            result=json.getString("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            _return=json.getString("return");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
