package com.zrkaxt.testmachine.obj;

import org.json.JSONObject;

import java.util.List;

public class PhyPatient {
    String data;

//    public List<FaceQuestion> getFaceQuestion() {
//        return data;
//    }
    List<FaceQuestion> faceQuestion;

    public PhyPatient(JSONObject json){
        try {
            data = json.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
