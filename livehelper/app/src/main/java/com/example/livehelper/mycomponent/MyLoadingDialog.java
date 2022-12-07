package com.zrkaxt.testmachine.mycomponent;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zrkaxt.testmachine.R;


public class MyLoadingDialog extends Dialog {

    public MyLoadingDialog(@NonNull Context context) {
        super(context);
    }

    public MyLoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyLoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setContent(String content) {
        ((TextView) findViewById(R.id.content)).setText(content);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String content;


        public Builder(Context context) {
            this.context = context;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public MyLoadingDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme

            final MyLoadingDialog dialog = new MyLoadingDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.my_loadingdialog, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            if (content != null) {
                ((TextView) layout.findViewById(R.id.content)).setText(content);
            }

            dialog.setContentView(layout);
            dialog.setCanceledOnTouchOutside(false);

            dialog.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            return dialog;
        }


    }
}
