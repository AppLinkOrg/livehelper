package com.example.livehelper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {
    public static String[] storagePermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static void checkPermission(Context context, String[] permissions, int requestCode){
        if(hasPermission(context,permissions)){
            return;
        }
        ActivityCompat.requestPermissions((Activity) context,permissions,requestCode);
    }

    public static boolean hasPermission(Context context,String... permissions){
        for(String permission:permissions){
            if(ContextCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return  true;
    }
}
