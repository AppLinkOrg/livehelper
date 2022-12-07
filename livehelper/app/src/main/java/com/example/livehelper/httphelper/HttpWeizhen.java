package com.zrkaxt.testmachine.httphelper;

import android.media.FaceDetector;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.zrkaxt.testmachine.obj.Acupoint;
import com.zrkaxt.testmachine.obj.FaceWentiResult;
import com.zrkaxt.testmachine.obj.InstInfo;
import com.zrkaxt.testmachine.obj.Member;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpWeizhen extends HttpBase{
    public void wenti(int member_id,String faceImgPath,String faceLeftImgPath,String faceRightImgPath,int delay,DataHandle<FaceWentiResult> ret) {
        Map<String,String> param=new HashMap<>();
        param.put("member_id",String.valueOf(member_id));
        param.put("faceImgPath",faceImgPath);
        param.put("faceLeftImgPath",faceLeftImgPath);
        param.put("faceRightImgPath",faceRightImgPath);
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();
                        FaceWentiResult result=new FaceWentiResult(info);
                        ret.loadSuccess(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="weizhen/wenti";
        this.PostJSON(url,param,handler,delay);
    }

    public void wenti2(String faceId,String str,int delay,DataHandle<FaceWentiResult> ret) {
        Map<String,String> param=new HashMap<>();
        param.put("faceId",faceId);
        param.put("str",str);
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();
                        FaceWentiResult result=new FaceWentiResult(info);
                        ret.loadSuccess(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="weizhen/wenti2";
        this.PostJSON(url,param,handler,delay);
    }

    public void facejieguo(String faceId,String id,DataHandle<JSONObject> ret) {
        Map<String,String> param=new HashMap<>();
        param.put("faceId",faceId);
        param.put("id",id);
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();
                        //FaceWentiResult result=new FaceWentiResult(info);
                        ret.loadSuccess(info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="wenzhen/facejieguo";
        this.PostJSON(url,param,handler);
    }
   public void   shuju3(int member_id,String tongueFrontImgPath,String tongueBottomImgPath,int delay,DataHandle<JSONObject> ret){
       Map<String,String> param=new HashMap<>();
       param.put("member_id",String.valueOf(member_id));
       param.put("tongueFrontImgPath",tongueFrontImgPath);
       param.put("tongueBottomImgPath",tongueBottomImgPath);

       Handler handler=new Handler(){
           @Override
           public void handleMessage(@NonNull Message msg) {
               super.handleMessage(msg);
               HttpReturnData data=(HttpReturnData)msg.obj;
               if (data.loadDataSuccess()) {
                   try {
                       JSONObject info=data.getJSON();

                       ret.loadSuccess(info);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }else{
                   ret.loadFailure(data);
               }
           }
       };
       String url="weizhen/shuju3";
       this.PostJSON(url,param,handler,delay);
   }


   public void  jieguo(String notifyCode ,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("notifyCode",notifyCode);


        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();

                        ret.loadSuccess(info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="weizhen/jieguo";
        this.PostJSON(url,param,handler,delay);
    }
    public void   analysis(int member_id,String leftHandImgPath,String rigthHandImgPath,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();
        param.put("member_id",String.valueOf(member_id));
        param.put("leftHandImgPath",leftHandImgPath);
        param.put("rigthHandImgPath",rigthHandImgPath);

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();

                        ret.loadSuccess(info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="hand/analysis";
        this.PostJSON(url,param,handler,delay);
    }


    public void  detail(String notifyCode ,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("notifyCode",notifyCode);


        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)
                        {
                            Log.d("TAG", "handleMessage: "+data);
                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="hand/detail";
        this.PostJSON(url,param,handler,delay);
    }
    public void acupointlist(DataHandle<List<Acupoint>> ret) {
        Map<String,String> param=new HashMap<>();
        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONArray arr=data.getJSON().getJSONArray("data");
                        List<Acupoint> list=Acupoint.LoadList(arr);

                        ret.loadSuccess(list);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="acupoint/list";
        this.PostJSON(url,param,handler);
    }

    public void   findacupointbyparam(int member_id,String path,String bodyId, int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();
        param.put("member_id",String.valueOf(member_id));
        param.put("path",path);
        param.put("bodyId",bodyId);

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        JSONObject info=data.getJSON();

                        ret.loadSuccess(info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="acupoint/findacupointbyparam";
        this.PostJSON(url,param,handler,delay);
    }

    public void  acupointdetail(String notifyCode ,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("notifyCode",notifyCode);


        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="acupoint/detail";
        this.PostJSON(url,param,handler,delay);
    }


    public void  jiuzhenren(int member_id,String patientName ,String guanxi,Number patientSex,String patientAge,String patientHeight,String patientWeight,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("member_id",String.valueOf(member_id));
        param.put("patientName",patientName);
        param.put("guanxi",guanxi);
        param.put("patientSex",String.valueOf(patientSex));
        param.put("patientAge",patientAge);
        param.put("patientHeight",patientHeight);
        param.put("patientWeight",patientWeight);
//        param.put("primary_id",id);


        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="inst/addjiuzhenren";
        this.PostJSON(url,param,handler);
    }

    public void  jiuzhenren2(int member_id,String patientName ,String guanxi,Number patientSex,String patientAge,String patientHeight,String patientWeight,String id,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("member_id",String.valueOf(member_id));
        param.put("patientName",patientName);
        param.put("guanxi",guanxi);
        param.put("patientSex",String.valueOf(patientSex));
        param.put("patientAge",patientAge);
        param.put("patientHeight",patientHeight);
        param.put("patientWeight",patientWeight);
        param.put("primary_id",id);


        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="inst/addjiuzhenren";
        this.PostJSON(url,param,handler);
    }

    public void  inst(int member_id ,DataHandle<JSONArray> ret){
        Map<String,String> param=new HashMap<>();

        param.put("member_id",String.valueOf(member_id));


        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONArray info=data.getJSONArry();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="inst/jiuzhenren";
        this.PostJSON(url,param,handler);
    }

    public void  Jiuzhenreninfo(String id ,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("id",id);


        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="inst/jiuzhenreninfo";
        this.PostJSON(url,param,handler);
    }

    public void  Getkeshi(DataHandle<JSONArray> ret){
        Map<String,String> param=new HashMap<>();



        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONArray info=data.getJSONArry();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="intelligentconsultation/getkeshi";
        this.PostJSON(url,param,handler);
    }

    public void  deletejiuzhenren(String id,DataHandle<String> ret){
        Map<String,String> param=new HashMap<>();
        param.put("id",id);



        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            String info=data.getString();

//                            JSONObject info=data;
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="inst/deletejiuzhenren";
        this.PostJSON(url,param,handler);
    }

    public void  start(String jiuzhenid,String keshi_id ,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("patientId",jiuzhenid);
        param.put("epartmeneId",keshi_id);
        Log.d("jiuzhenid","jiuzhenid"+jiuzhenid);
        Log.d("epartmeneId","keshi_id"+keshi_id);

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="intelligentconsultation/start";
        this.PostJSON(url,param,handler,delay);
    }

    public void  next(String recordId,String flowId,String  processId, String answerstr, int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("recordId",recordId);
        param.put("flowId",flowId);
        param.put("processId",processId);
        param.put("answerstr",answerstr);
//        param.put("notifyCode",notifyCode);


        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="intelligentconsultation/next";
        this.PostJSON(url,param,handler,delay);
    }

    public void  detailintell(String recordId,int member_id,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("recordId",recordId);
        param.put("member_id",String.valueOf(member_id));

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="intelligentConsultation/detail";
        this.PostJSON(url,param,handler,delay);
    }

    public void  handle(String recordNo,String questionCode,String answerCode,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("recordNo",recordNo);

        param.put("questionCode",questionCode);
        param.put("answerCode",answerCode);

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="corporeitydiagnose/handle";
        this.PostJSON(url,param,handler,delay);
    }


    public void  corstart(String sex,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("sex",sex);

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="corporeitydiagnose/start";
        this.PostJSON(url,param,handler,delay);
    }

    public void  result(String recordNo,int member_id,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("recordNo",recordNo);
        param.put("member_id",String.valueOf(member_id));

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="corporeitydiagnose/result";
        this.PostJSON(url,param,handler,delay);
    }

    public void  addtonguerecord(String recordNo,String tongueNotifyCode,int delay,DataHandle<JSONObject> ret){
        Map<String,String> param=new HashMap<>();

        param.put("recordNo",recordNo);
        param.put("tongueNotifyCode",tongueNotifyCode);


        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                HttpReturnData data=(HttpReturnData)msg.obj;
                if (data.loadDataSuccess()) {
                    try {
                        if(data!=null)

                        {
                            Log.d("TAG", "handleMessage: "+data);

                            JSONObject info=data.getJSON();
                            ret.loadSuccess(info);
                        }
                        else {
                            ret.loadSuccess(null);
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    ret.loadFailure(data);
                }
            }
        };
        String url="corporeitydiagnose/addtonguerecord";
        this.PostJSON(url,param,handler,delay);
    }




}
