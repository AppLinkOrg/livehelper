package com.zrkaxt.testmachine.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.zrkaxt.testmachine.R;

public class DialogUtil {
    public static void InitDialog(Dialog dialog){
        dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        dialog.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        //布局位于状态栏下方
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        //全屏
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        //隐藏导航栏
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                uiOptions |= 0x00001000;
                dialog.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
    }

    public static void Alert(Context ctx, String str,DialogInterface.OnClickListener event) {
        AlertDialog alertDialog1 = new AlertDialog.Builder(ctx)
                .setTitle(R.string.tips)//标题
                .setMessage(str)//内容
                .setPositiveButton(R.string.ok, event)
                .create();
        alertDialog1.show();
    }
    public static void Alert(Context ctx, int str,DialogInterface.OnClickListener event) {
        AlertDialog alertDialog1 = new AlertDialog.Builder(ctx)
                .setTitle(R.string.tips)//标题
                .setMessage(str)//内容
                .setPositiveButton(R.string.ok, event)
                .create();
        alertDialog1.show();
    }
}
