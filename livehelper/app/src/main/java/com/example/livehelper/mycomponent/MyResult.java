package com.zrkaxt.testmachine.mycomponent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zrkaxt.testmachine.R;
import com.zrkaxt.testmachine.helper.ViewGenerator;

import java.util.List;

public class MyResult extends LinearLayout {


    LinearLayout t1, t2, t1x, t2x, t1container, t2container;
    ScrollView t1p, t2p;

    public MyResult(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.mycomponent_result_layout, this);
        initView();
        initEvent();
        initData();
    }

    public MyResult(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.mycomponent_result_layout, this);
        initView();
        initEvent();
        initData();
    }

    public MyResult(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.mycomponent_result_layout, this);
        initView();
        initEvent();
        initData();
    }

    public MyResult(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater.from(context).inflate(R.layout.mycomponent_result_layout, this);
        initView();
        initEvent();
        initData();
    }


    protected void initView() {

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t1x = findViewById(R.id.t1x);
        t2x = findViewById(R.id.t2x);
        t1p = findViewById(R.id.t1p);
        t2p = findViewById(R.id.t2p);
        t1container = findViewById(R.id.t1container);
        t2container = findViewById(R.id.t2container);
        setT1T2(1);
    }

    void setT1T2(int t) {
        t1.setBackgroundColor(getContext().getColor(t == 1 ? R.color.white : R.color.lightblue));
        t2.setBackgroundColor(getContext().getColor(t == 2 ? R.color.white : R.color.lightblue));

        t1x.setVisibility(t == 1 ? View.VISIBLE : View.INVISIBLE);
        t2x.setVisibility(t == 2 ? View.VISIBLE : View.INVISIBLE);


        t1p.setVisibility(t == 1 ? View.VISIBLE : View.GONE);
        t2p.setVisibility(t == 2 ? View.VISIBLE : View.GONE);
    }

    protected void initEvent() {

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setT1T2(1);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setT1T2(2);
            }
        });
    }

    protected void initData() {

    }

    public void setData(List<IMyResultChild> lsZhengzhuang, List<IMyResultChild> lsFangan) {
        for (int i = 0; i < lsZhengzhuang.size(); i++) {
            t1container.addView(lsZhengzhuang.get(i).generate());
        }
        for (int i = 0; i < lsFangan.size(); i++) {
            t2container.addView(lsFangan.get(i).generate());
        }
    }


    public interface IMyResultChild {
        public View generate();
    }


    public static class MyResultChildTitle implements IMyResultChild {
        Context ctx;
        String title = "";

        public MyResultChildTitle(Context ctx, String title) {
            this.ctx = ctx;
            this.title = title;
        }

        @Override
        public View generate() {
            LinearLayout linearLayout = ViewGenerator.GenLinearLayout(ctx);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setGravity(Gravity.CENTER_VERTICAL);

            LinearLayout ball = new LinearLayout(ctx);
            LinearLayout.LayoutParams balllayoutParams = new LinearLayout.LayoutParams(ctx.getResources().getDimensionPixelSize(R.dimen.dp20), ctx.getResources().getDimensionPixelSize(R.dimen.dp20));
            ball.setLayoutParams(balllayoutParams);
            balllayoutParams.setMargins(0, 0, ctx.getResources().getDimensionPixelSize(R.dimen.dp20), 0);
            ball.setBackgroundResource(R.mipmap.ball);


            linearLayout.addView(ball);

            TextView textView = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.black), ctx.getResources().getDimensionPixelSize(R.dimen.sp32));
            textView.setText(title);
            textView.getPaint().setFakeBoldText(true);

            linearLayout.addView(textView);

            return linearLayout;
        }
    }



    public static class MyResultChildTitle2 implements IMyResultChild {
        Context ctx;
        String title = "";

        public MyResultChildTitle2(Context ctx, String title) {
            this.ctx = ctx;
            this.title = title;
        }



        @Override
        public View generate() {
            LinearLayout linearLayout = ViewGenerator.GenLinearLayout(ctx);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setGravity(Gravity.CENTER);

            TextView textView = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.black), ctx.getResources().getDimensionPixelSize(R.dimen.sp38));
            textView.setText(title);
            textView.getPaint().setFakeBoldText(true);

            linearLayout.addView(textView);

            return linearLayout;
        }
    }

    public static class MyResultChildTitle3 implements IMyResultChild {
        Context ctx;
        String title = "";

        public MyResultChildTitle3(Context ctx, String title) {
            this.ctx = ctx;
            this.title = title;
        }



        @Override
        public View generate() {
            LinearLayout linearLayout = ViewGenerator.GenLinearLayout(ctx);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setGravity(Gravity.CENTER);

            TextView textView = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.gray), ctx.getResources().getDimensionPixelSize(R.dimen.sp26));
            textView.setText(title);
            textView.getPaint().setFakeBoldText(true);

            linearLayout.addView(textView);

            return linearLayout;
        }
    }

    public static class MyResultChildContent implements IMyResultChild {
        Context ctx;
        String content = "";

        public MyResultChildContent(Context ctx, String content) {
            this.ctx = ctx;
            this.content = content;
        }

        @Override
        public View generate() {
            LinearLayout linearLayout = ViewGenerator.GenLinearLayout(ctx);

            TextView textView = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.black), ctx.getResources().getDimensionPixelSize(R.dimen.sp26));
            textView.setText(content);
            textView.setLineSpacing(1, 1.5f);

            linearLayout.addView(textView);

            return linearLayout;
        }
    }


    public static class MyResultChildAnalyze implements IMyResultChild {
        Context ctx;
        int photo = 0;
        String photourl = "";
        String title = "";
        String result = "";
        String analyze = "";
        String hazard = "";

        public MyResultChildAnalyze(Context ctx, String title, int photo, String result, String analyze, String hazard) {
            this.ctx = ctx;
            this.title = title;
            this.photo = photo;
            this.result = result;
            this.analyze = analyze;
            this.hazard = hazard;
        }


        public MyResultChildAnalyze(Context ctx, String title, String photourl, String result, String analyze, String hazard) {
            this.ctx = ctx;
            this.title = title;
            this.photourl = photourl;
            this.result = result;
            this.analyze = analyze;
            this.hazard = hazard;
        }

        @Override
        public View generate() {

            LinearLayout linearLayout = ViewGenerator.GenLinearLayout(ctx);

            View titleview = (new MyResultChildTitle(ctx, title)).generate();
            linearLayout.addView(titleview);


            LinearLayout resultlayout = ViewGenerator.GenLinearLayout(ctx);
            resultlayout.setGravity(Gravity.CENTER);

            ImageView imageView = null;
            if (photo > 0) {
                imageView = ViewGenerator.GenImageView(ctx,ctx.getResources().getDimensionPixelSize(R.dimen.dp220), photo);
            }
            if (photourl.equals("") == false) {
                imageView = ViewGenerator.GenImageView(ctx,ctx.getResources().getDimensionPixelSize(R.dimen.dp220), photourl);
            }
            if (imageView != null) {
                ((LinearLayout.LayoutParams) imageView.getLayoutParams()).setMargins(0, ctx.getResources().getDimensionPixelSize(R.dimen.dp20), 0, 0);
                resultlayout.addView(imageView);
            }


            TextView resulttext = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.black), ctx.getResources().getDimensionPixelSize(R.dimen.sp38));
            resulttext.setText(result);
            resulttext.getPaint().setFakeBoldText(true);
            ((LinearLayout.LayoutParams) resulttext.getLayoutParams()).setMargins(0, ctx.getResources().getDimensionPixelSize(R.dimen.dp20), 0, 0);
            resultlayout.addView(resulttext);

            linearLayout.addView(resultlayout);

            LinearLayout fanganlayout = ViewGenerator.GenLinearLayout(ctx);
            fanganlayout.setGravity(Gravity.CENTER);
            fanganlayout.setOrientation(HORIZONTAL);
            //fanganlayout.setBackgroundColor(ctx.getColor(R.color.black));

            LinearLayout fenxilayout = ViewGenerator.GenLinearLayoutNoMargin(ctx, ctx.getResources().getDimensionPixelSize(R.dimen.dp220));
            fenxilayout.setGravity(Gravity.CENTER);
            fenxilayout.setBackgroundResource(R.drawable.tabboxfill);

            TextView fenxitext = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.white), ctx.getResources().getDimensionPixelSize(R.dimen.sp26));
            fenxitext.setText("病情分析");
            fenxilayout.addView(fenxitext);


            LinearLayout weihailayout = ViewGenerator.GenLinearLayoutNoMargin(ctx, ctx.getResources().getDimensionPixelSize(R.dimen.dp220));
            weihailayout.setGravity(Gravity.CENTER);
            weihailayout.setBackgroundResource(R.drawable.txtbox);

            TextView weihaitext = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.blue), ctx.getResources().getDimensionPixelSize(R.dimen.sp26));
            weihaitext.setText("相关危害");
            weihailayout.addView(weihaitext);


            fanganlayout.addView(fenxilayout);
            fanganlayout.addView(ViewGenerator.GenLinearLayout(ctx, ctx.getResources().getDimensionPixelSize(R.dimen.dp20)));
            fanganlayout.addView(weihailayout);

            TextView analyzetextView = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.black), ctx.getResources().getDimensionPixelSize(R.dimen.sp26));
            ((LinearLayout.LayoutParams) analyzetextView.getLayoutParams()).setMargins(0, ctx.getResources().getDimensionPixelSize(R.dimen.dp20), 0, 0);
            analyzetextView.setText(analyze);
            analyzetextView.setLineSpacing(1, 1.5f);

            TextView hazardtextView = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.black), ctx.getResources().getDimensionPixelSize(R.dimen.sp26));
            ((LinearLayout.LayoutParams) hazardtextView.getLayoutParams()).setMargins(0, ctx.getResources().getDimensionPixelSize(R.dimen.dp20), 0, 0);
            hazardtextView.setText(hazard);
            hazardtextView.setLineSpacing(1, 1.5f);
            hazardtextView.setVisibility(GONE);

            linearLayout.addView(fanganlayout);


            linearLayout.addView(analyzetextView);
            linearLayout.addView(hazardtextView);


            fenxilayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    fenxilayout.setBackgroundResource(R.drawable.tabboxfill);
                    fenxitext.setTextColor(ctx.getColor(R.color.white));
                    analyzetextView.setVisibility(VISIBLE);


                    weihailayout.setBackgroundResource(R.drawable.txtbox);
                    weihaitext.setTextColor(ctx.getColor(R.color.blue));
                    hazardtextView.setVisibility(GONE);

                }
            });


            weihailayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    fenxilayout.setBackgroundResource(R.drawable.txtbox);
                    fenxitext.setTextColor(ctx.getColor(R.color.blue));
                    analyzetextView.setVisibility(GONE);

                    weihailayout.setBackgroundResource(R.drawable.tabboxfill);
                    weihaitext.setTextColor(ctx.getColor(R.color.white));
                    hazardtextView.setVisibility(VISIBLE);

                }
            });

            return linearLayout;
        }
    }


    public static class MyResultChildXuewei implements IMyResultChild {
        Context ctx;
        String name = "";
        String photourl = "";

        public MyResultChildXuewei(Context ctx, String name, String photourl) {
            this.ctx = ctx;
            this.name = name;
            this.photourl = photourl;
        }

        @Override
        public View generate() {
            LinearLayout linearLayout = ViewGenerator.GenLinearLayout(ctx);
            linearLayout.setGravity(Gravity.CENTER);

            TextView textView = ViewGenerator.GenTextView(ctx, ctx.getColor(R.color.black), ctx.getResources().getDimensionPixelSize(R.dimen.sp26));
            textView.setText(name);
            linearLayout.addView(textView);

            ImageView imageView = ViewGenerator.GenImageView(ctx,ctx.getResources().getDimensionPixelSize(R.dimen.dp500), photourl);;
            linearLayout.addView(imageView);


            return linearLayout;
        }
    }


    public static class MyResultChildImage implements IMyResultChild {
        Context ctx;
        int photo ;
        String photourl = "";

        public MyResultChildImage(Context ctx, String photourl) {
            this.ctx = ctx;
            this.photourl = photourl;
        }
        public MyResultChildImage(Context ctx, int photo) {
            this.ctx = ctx;
            this.photo = photo;
        }

        @Override
        public View generate() {
            LinearLayout linearLayout = ViewGenerator.GenLinearLayout(ctx);
            linearLayout.setGravity(Gravity.CENTER);

            ImageView imageView = null;
            if (photo > 0) {
                imageView = ViewGenerator.GenImageView(ctx,ctx.getResources().getDimensionPixelSize(R.dimen.dp500), photo);

            }
            if (photourl.equals("") == false) {
                imageView = ViewGenerator.GenImageView(ctx,ctx.getResources().getDimensionPixelSize(R.dimen.dp500), photourl);

            }
            if (imageView != null) {
                linearLayout.addView(imageView);

            }
            return linearLayout;
        }
    }

}
