package com.zrkaxt.testmachine.obj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FaceQuestion {

    public static List<FaceQuestion> LoadList(JSONArray jsonarr) {
        List<FaceQuestion> list=new ArrayList<>();
        for(int i=0;i<jsonarr.length();i++){
            try {
                JSONObject json=jsonarr.getJSONObject(i);
                FaceQuestion cookbook=new FaceQuestion(json);
                list.add(cookbook);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  list;
    }

    String subjectId,subjectType,subjectTitle;
    JSONArray answer;

    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public JSONArray getAnswer() {
        return answer;
    }

    public Object anwserObj=null;



    public FaceQuestion(JSONObject json){
        try {
            this.subjectId=json.getString("subjectId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.subjectType=json.getString("subjectType");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.subjectTitle=json.getString("subjectTitle");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.answer=json.getJSONArray("answer");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
