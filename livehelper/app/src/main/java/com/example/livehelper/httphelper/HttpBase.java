package com.zrkaxt.testmachine.httphelper;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.auth.HmacSHA1Signature;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCustomSignerCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.zrkaxt.testmachine.StartApplication;
import com.zrkaxt.testmachine.config.ApiConfig;
import com.zrkaxt.testmachine.config.GlobalVar;
import com.zrkaxt.testmachine.helper.FileUtil;
import com.zrkaxt.testmachine.helper.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpBase {


    public void PostJSON(final String method, final Map<String, String> params, Handler handler) {
        this.PostJSON(method, params, handler, 0);
    }

    public void PostJSON(final String method, final Map<String, String> params, Handler handler, int delay) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (delay > 0) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                HttpReturnData ret = ActionPostJSON(method, params);
                if (handler != null) {
                    Message msg = new Message();
                    msg.obj = ret;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }
    public void UploadFile(final String module, String filepath, Handler handler) {
        this.UploadFile(module, filepath, handler, 0);
    }

    public void UploadFile(final String module, String filepath, Handler handler, int delay) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (delay > 0) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                HttpReturnData ret = ActionUpload(module, filepath);
                if (handler != null) {
                    Message msg = new Message();
                    msg.obj = ret;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    private HttpReturnData ActionPostJSON(String method, Map<String, String> params) {

        String result = "";
        BufferedReader reader = null;
        try {
            String urlPath = ApiConfig.GetApiUrl() + method;
            Log.d("hlhupload", "doJsonPost: url:" + urlPath);

            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("TOKEN", GlobalVar.GetMac());
            conn.setRequestProperty("UNICODE", ApiConfig.GetUnicode());
            conn.setRequestProperty("accept", "application/x-www-form-urlencoded");

            // 往服务器里面发送数据
            StringBuffer requestBuffer=getRequestData(params, "utf8");
            byte[] writebytes = requestBuffer.toString().getBytes();//获得请求体
            Log.d("hlhupload", "doJsonPost: push" + requestBuffer.toString());
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
            OutputStream outwritestream = conn.getOutputStream();
            outwritestream.write(writebytes);
            outwritestream.flush();
            outwritestream.close();
            Log.d("hlhupload", "doJsonPost: conn" + conn.getResponseCode());

            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
                Log.d("hlhupload", "doJsonPost: result" + result);
                return new HttpReturnData(conn.getResponseCode(), result);
            } else {
                return new HttpReturnData(conn.getResponseCode(), "{}");
            }
        } catch (Exception e) {
            Log.d("hlhupload", "doJsonPost: error" + e.getMessage());
            e.printStackTrace();
            return new HttpReturnData(-1, "{}", e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private HttpReturnData ActionUpload(String module, String filepath) {
        Map<String, String> params=new HashMap<>();
        params.put("base64","data:image/png;base64,"+Util.ImageToBase64(filepath));
        String result = "";
        BufferedReader reader = null;
        try {
            String urlPath = ApiConfig.GetFileUploadAPI() + "?rettype=json&module="+module;
            Log.d("hlhupload", "doJsonPost: url:" + urlPath);

            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("TOKEN", GlobalVar.GetMac());
            conn.setRequestProperty("UNICODE", ApiConfig.GetUnicode());
            conn.setRequestProperty("accept", "application/x-www-form-urlencoded");

            // 往服务器里面发送数据
            StringBuffer requestBuffer=getRequestData(params, "utf8");
            byte[] writebytes = requestBuffer.toString().getBytes();//获得请求体
            Log.d("hlhupload", "doJsonPost: push" + requestBuffer.toString());
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
            OutputStream outwritestream = conn.getOutputStream();
            outwritestream.write(writebytes);
            outwritestream.flush();
            outwritestream.close();
            Log.d("hlhupload", "doJsonPost: conn" + conn.getResponseCode());

            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
                Log.d("hlhupload", "doJsonPost: result" + result);
                return new HttpReturnData(conn.getResponseCode(), result);
            } else {
                return new HttpReturnData(conn.getResponseCode(), "{}");
            }
        } catch (Exception e) {
            Log.d("hlhupload", "doJsonPost: error" + e.getMessage());
            e.printStackTrace();
            return new HttpReturnData(-1, "{}", e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




    /*
     * Function  :   封装请求体信息
     * Param     :   params请求体内容，encode编码格式
     */
    public StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            Log.e("posturl_param", stringBuffer.toString());
            if(stringBuffer.toString().isEmpty()==false){
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
    public static String sign(String accessKey, String screctKey, String content) {

        String signature;

        try {
            signature = new HmacSHA1Signature().computeSignature(screctKey, content);
            signature = signature.trim();
        } catch (Exception e) {
            throw new IllegalStateException("Compute signature failed!", e);
        }

        return "OSS " + accessKey + ":" + signature;
    }
}
