package com.zrkaxt.testmachine.helper;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zrkaxt.testmachine.R;

public class ViewGenerator {
    public static LinearLayout GenLinearLayout(Context ctx) {

        LinearLayout linearLayout = new LinearLayout(ctx);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        //linearLayout.setBackgroundResource(R.drawable.txtbox);
        //linearLayout.setGravity(Gravity.CENTER);
        layoutParams.setMargins(0, ctx.getResources().getDimensionPixelSize(R.dimen.dp20), 0, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        return linearLayout;
    }
    public static LinearLayout GenLinearLayoutNoMargin(Context ctx) {

        LinearLayout linearLayout = new LinearLayout(ctx);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        //linearLayout.setBackgroundResource(R.drawable.txtbox);
        //linearLayout.setGravity(Gravity.CENTER);
        //layoutParams.setMargins(0, ctx.getResources().getDimensionPixelSize(R.dimen.dp20), 0, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        return linearLayout;
    }

    public static LinearLayout GenLinearLayout(Context ctx, int width) {

        LinearLayout linearLayout = new LinearLayout(ctx);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        //linearLayout.setBackgroundResource(R.drawable.txtbox);
        //linearLayout.setGravity(Gravity.CENTER);
        layoutParams.setMargins(0, ctx.getResources().getDimensionPixelSize(R.dimen.dp20), 0, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        return linearLayout;
    }


    public static LinearLayout GenLinearLayoutNoMargin(Context ctx, int width) {

        LinearLayout linearLayout = new LinearLayout(ctx);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        //linearLayout.setBackgroundResource(R.drawable.txtbox);
        //linearLayout.setGravity(Gravity.CENTER);
        //layoutParams.setMargins(0, ctx.getResources().getDimensionPixelSize(R.dimen.dp20), 0, 0);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        return linearLayout;
    }


    public static TextView GenTextView(Context ctx, int textcolor, float textsize) {
        TextView textView = new TextView(ctx);
        LinearLayout.LayoutParams txtlay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(txtlay);
        textView.setTextColor(textcolor);
        textView.setTextSize(textsize);

        return textView;
    }

    public static ImageView GenImageView(Context ctx,int width, int photo) {
        ImageView imageView = new ImageView(ctx);
        LinearLayout.LayoutParams imageViewParameter = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageViewParameter);
        imageView.setImageResource(photo);

        return imageView;
    }

    public static ImageView GenImageView(Context ctx,int width, String photourl) {
        ImageView imageView = new ImageView(ctx);
        LinearLayout.LayoutParams imageViewParameter = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(imageViewParameter);

        Glide.with(ctx).load(photourl).into(imageView);
        return imageView;
    }
}
