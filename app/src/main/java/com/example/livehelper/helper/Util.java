package com.zrkaxt.testmachine.helper;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Util {
    /**
     * Java中的md5
     *
     * @param content 输入的值
     * @return 输出md5加密后的值
     */
    public static String MD5(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append(0);
            }
            hex.append(Integer.toHexString(b & 0xff));
        }

        return hex.toString();
    }


    public static String B2GB(long totalSize) {
        return String.format("%2d", totalSize / 1024 / 1024 / 1024) + "GB";
    }

    public static String getAppVersion(Context ctx) {
        String appVersion = "";
        PackageManager pManager = ctx.getPackageManager();
        try {
            PackageManager packageManager = ctx.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    ctx.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appVersion;
    }


    public static void getFullScreen(final Dialog dialog) {
//        dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        dialog.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
//            @Override
//            public void onSystemUiVisibilityChange(int visibility) {
//                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                        //布局位于状态栏下方
//                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
//                        //全屏
//                        View.SYSTEM_UI_FLAG_FULLSCREEN |
//                        //隐藏导航栏
//                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
//                uiOptions |= 0x00001000;
//                dialog.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
//            }
//        });
    }

    public static String getTopActivity(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        Log.d("测试", "pkg:" + cn.getPackageName());//包名
        Log.d("测试", "cls:" + cn.getClassName());//包名加类名
        return cn.getClassName();
    }

    public static Activity getCurrentActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean IsNotNullOrEmpty(String str) {
        if (str == null) {
            return false;
        }
        return str.isEmpty() == false;
    }


    public static void GetRedirectUrl(String url, Handler handle) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = url;
                URL url = null;
                try {
                    url = new URL(str);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setInstanceFollowRedirects(false);
                    conn.getResponseCode();
                    final String realUrl = conn.getURL().toString();
                    conn.disconnect();
                    String location = conn.getHeaderField("Location");
                    Log.e("asd", "真实URL:" + location);
                    Message msg = new Message();
                    msg.obj = location;
                    handle.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void recovery(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            context.sendBroadcast(new Intent("android.intent.action.MASTER_CLEAR"));
        } else {
            Intent intent = new Intent("android.intent.action.FACTORY_RESET");
            intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
            intent.setPackage("android");
            context.sendBroadcast(intent);
        }
    }

    public static String ImageToBase64(String path){

        InputStream is = null;
        byte[] data = null;
        String result = null;
        try{
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data,Base64.DEFAULT);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null !=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }
}
