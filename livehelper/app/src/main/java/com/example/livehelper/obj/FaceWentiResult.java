package com.zrkaxt.testmachine.obj;

import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FaceWentiResult {
    String code,msg,face_id,faceId,isFinalResult;
   JSONArray subject;


    public String getFaceId() {
        return faceId;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getFace_id() {
        return face_id;
    }

    public List<FaceQuestion> getFaceQuestion() {
        return faceQuestion;
    }

    public JSONArray getSubject() {
        return subject;
    }




    List<FaceQuestion> faceQuestion;

    public String getIsFinalResult() {
        return isFinalResult;
    }

    public FaceWentiResult(JSONObject json){
        try {
            code=json.getString("code");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            msg=json.getString("msg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            face_id=json.getString("face_id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            faceQuestion=FaceQuestion.LoadList(json.getJSONObject("data").getJSONArray("subject"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            subject=json.getJSONObject("data").getJSONArray("subject");
        } catch (Exception e) {
            e.printStackTrace();
        }




        try {
            faceId=json.getJSONObject("data").getString("faceId");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            isFinalResult=json.getJSONObject("data").getString("isFinalResult");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getAnwser() {
        String ret="";
        try{
            JSONArray anwser=new JSONArray();
            for(int i=0;i<getFaceQuestion().size();i++){
                FaceQuestion obj= getFaceQuestion().get(i);
                JSONObject anwjson=new JSONObject();
                try {
                    anwjson.put("subjectId",obj.subjectId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(obj.getSubjectType().equals("0")){
                    try {
                        String kk= (String)obj.anwserObj;
                        if(kk.isEmpty()){
                            kk="30";
                        }
                        anwjson.put("answerCode",kk);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(obj.getSubjectType().equals("1")){
                    try {
                        anwjson.put("answerCode",(String)obj.anwserObj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                anwser.put(anwjson);
            }
            ret=anwser.toString();
            Log.d("getAnwser",ret);
        }catch (Exception ex){
            Log.d("getAnwser",ex.getMessage());

        }
        return  ret;
    }
}
