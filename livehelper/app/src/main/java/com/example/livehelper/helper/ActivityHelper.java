package com.zrkaxt.testmachine.helper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.zrkaxt.testmachine.LoginActivity;
import com.zrkaxt.testmachine.SuperActivity;


public class ActivityHelper {
    public static final int CookHistoryActivity=1006;

    public static final int TongueActivity=1007;
    public static final int FaceDetectActivity=1008;
public static final int HandActivity=1009;
public static  final int AcupointActivity=1010;

    public static  final int PhysiqueGuideActivity=1011;

    public static  final int ConsitutionGuideActivity=1012;

    public static void openLoginActivityHelp(SuperActivity activity, int reqcode){

        Intent intent=new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent,reqcode);
    }

    public static void ActivityInit(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


    }


}
