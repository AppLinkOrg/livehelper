package com.zrkaxt.testmachine.mycomponent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.zrkaxt.testmachine.R;
import com.zrkaxt.testmachine.helper.FileUtil;
import com.zrkaxt.testmachine.httphelper.DataHandle;
import com.zrkaxt.testmachine.httphelper.HttpReturnData;
import com.zrkaxt.testmachine.timer.AdvertiseTimer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AcupointSelectorView extends View {

    private Paint mPaint;
    int viewWidth = 0;
    int viewHeight = 0;
    int bitmapWidth = 0;
    int bitmapHeight = 0;
    int acupointId = 0;
    private Bitmap resbitmap = null;

    JSONArray acupointlist = null;
    List<XueWei> listLeft = new ArrayList<>();
    List<XueWei> listRight = new ArrayList<>();

    public AcupointSelectorView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public AcupointSelectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    public AcupointSelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
    }

    public AcupointSelectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        viewHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (resbitmap != null) {
            Log.d("xueweino3", "getesbit");
            Rect src = new Rect(0, 0, bitmapWidth, bitmapHeight);
            float cHeight = bitmapHeight * viewWidth / bitmapWidth;
            RectF desc = new RectF(0, 0, viewWidth, cHeight);
            canvas.drawBitmap(resbitmap, src, desc, mPaint);
            float sp38 = getContext().getResources().getDimensionPixelSize(R.dimen.sp38);
            mPaint.setTextSize(sp38);
            mPaint.setColor(getResources().getColor(R.color.white));
            //mPaint.setStyle(Paint.Style.STROKE);//不填充
            //mPaint.setStrokeWidth(1);  //线的宽度
            //canvas.drawText("爱老虎油",100,100,mPaint);
            Paint selectpaint = new Paint();
            selectpaint.setTextSize(sp38);
            selectpaint.setColor(getResources().getColor(R.color.orange));

            float rate = viewWidth * 1.0f / bitmapWidth;


            Paint kuangpaint = new Paint();
            //kuangpaint.setStyle(Paint.Style.STROKE);//不填充
            kuangpaint.setColor(getResources().getColor(R.color.white));
            kuangpaint.setStrokeWidth(3);  //线的宽度


            Paint selectkuangpaint = new Paint();
            //kuangpaint.setStyle(Paint.Style.STROKE);//不填充
            selectkuangpaint.setColor(getResources().getColor(R.color.orange));
            selectkuangpaint.setStrokeWidth(3);  //线的宽度
            if (acupointlist != null) {


                int k40 = 50;
                int ace = 10;
                int ziwidth = (int) sp38;
                int start = viewHeight / 2 - listLeft.size() / 2 * k40;
                for (int i = 0; i < listLeft.size(); i++) {
                    XueWei xuewei = listLeft.get(i);
                    int xAxis = xuewei.x;
                    int yAxis = xuewei.y;
                    String xueweiname = xuewei.name;
                    int xueweinamestart = ziwidth * xueweiname.length();
                    Log.d("xueweiname", xueweiname + ":" + String.valueOf(xueweiname.length()));

                    Rect rect = new Rect(20, (int) k40 * i + start, xueweinamestart + 20, (int) k40 * (i + 1) + start);
                    //canvas.drawRect(rect,  kuangpaint);
                    canvas.drawText(xueweiname, 20, k40 * (i + 1) + start - ace, acupointId != xuewei.acupointId ? mPaint : selectpaint);
                    canvas.drawLine(20 + xueweinamestart, k40 * (i + 1) + start - ziwidth / 2, xAxis * rate, yAxis * rate, acupointId != xuewei.acupointId ? kuangpaint : selectkuangpaint);
                    canvas.drawCircle(xAxis * rate, yAxis * rate, 5, acupointId != xuewei.acupointId ? mPaint : selectpaint);
                    xuewei.updateRect(rect);
                }

                start = viewHeight / 2 - listRight.size() / 2 * k40;
                for (int i = 0; i < listRight.size(); i++) {
                    XueWei xuewei = listRight.get(i);
                    int xAxis = xuewei.x;
                    int yAxis = xuewei.y;
                    String xueweiname = xuewei.name;
                    int xueweinamestart = ziwidth * xueweiname.length();
                    Log.d("xueweiname", xueweiname + ":" + String.valueOf(xueweiname.length()));

                    Rect rect = new Rect(viewWidth - 20 - xueweinamestart, (int) k40 * i + start, viewWidth - 20, (int) k40 * (i + 1) + start);
                    //canvas.drawRect(rect,  kuangpaint);
                    canvas.drawText(xueweiname, viewWidth - 20 - xueweinamestart, k40 * (i + 1) + start - ace, acupointId != xuewei.acupointId ? mPaint : selectpaint);
                    canvas.drawLine(viewWidth - 20 - xueweinamestart, k40 * (i + 1) + start - ziwidth / 2, xAxis * rate, yAxis * rate, acupointId != xuewei.acupointId ? kuangpaint : selectkuangpaint);
                    canvas.drawCircle(xAxis * rate, yAxis * rate, 5, acupointId != xuewei.acupointId ? mPaint : selectpaint);
                    xuewei.updateRect(rect);
                }
            }
        }
    }

    public void setImage(String url) {
        FileUtil.GetInstance().loadImageToBitmap(url, new DataHandle<Bitmap>() {
            @Override
            public void loadSuccess(Bitmap data) {
                bitmapWidth = data.getWidth();
                bitmapHeight = data.getHeight();
                //viewWidth= getContext().getResources().getDimensionPixelSize(R.dimen.dp800);
                //viewHeight= getContext().getResources().getDimensionPixelSize(R.dimen.dp1070);

                //Log.d("myheight",String.valueOf(bitmapHeight)+"*"+String.valueOf(viewWidth)+"/"+String.valueOf(bitmapWidth));
                //viewHeight=bitmapHeight*viewWidth/bitmapWidth;
                resbitmap = data;
                //setViewHeight(viewHeight);
                postInvalidate();
            }

            @Override
            public void loadFailure(HttpReturnData failRet) {

            }
        });
    }

    public void setAcupointList(JSONArray acupointList) {
        Log.d("xueweino", String.valueOf(acupointList.length()));
        this.acupointlist = acupointList;

        listLeft.clear();
        listRight.clear();
        Log.d("xueweino2", String.valueOf(acupointlist.length()));
        for (int i = 0; i < acupointlist.length(); i++) {
            try {
                JSONObject xuewei = acupointlist.getJSONObject(i);
                JSONObject position = xuewei.getJSONObject("position");
                int xAxis = position.getInt("xAxis");
                int yAxis = position.getInt("yAxis");
                int acupointId = xuewei.getInt("acupointId");
                String xueweiname = xuewei.getString("parentAcupointName");
                String description = xuewei.getString("description");
                if (xAxis < (bitmapWidth / 2)) {
                    listLeft.add(new XueWei(acupointId, xueweiname, xAxis, yAxis,description));
                } else {
                    listRight.add(new XueWei(acupointId, xueweiname, xAxis, yAxis,description));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(listLeft, comparator);
        Collections.sort(listRight, comparator);

        if (listLeft.size() > 0) {
            acupointId = listLeft.get(0).acupointId;
            if (onXueweiClickListener != null) {
                onXueweiClickListener.onClick(listLeft.get(0));
            }
        } else if (listRight.size() > 0) {
            acupointId = listRight.get(0).acupointId;
            if (onXueweiClickListener != null) {
                onXueweiClickListener.onClick(listRight.get(0));
            }
        }


        postInvalidate();
    }

    public class XueWei {
        int acupointId = 0;
        String name = "";
        String description = "";
        int x, y;
        Rect rect;

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public XueWei(int acupointId, String name, int x, int y, String description) {
            this.acupointId = acupointId;
            this.name = name;
            this.x = x;
            this.y = y;
            this.description=description;
        }

        public void updateRect(Rect rect) {
            this.rect = rect;
        }

        public boolean inRect(float curX, float curY) {
            return curX >= rect.left && curX <= rect.right && curY >= rect.top && curY <= rect.bottom;
        }
    }

    Comparator<XueWei> comparator = new Comparator<XueWei>() {
        @Override
        public int compare(XueWei details1, XueWei details2) {
            //排序规则，按照价格由大到小顺序排列("<"),按照价格由小到大顺序排列(">"),
            if (details1.y > details2.y)
                return 1;
            else {
                return -1;
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("dispatchTouchEvent", String.valueOf(System.currentTimeMillis()));
        calcPointRange(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_UP:
                //抬起时启动定时

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void calcPointRange(MotionEvent event) {

        float curX = event.getRawX();
        float curY = event.getRawY();
        for (int i = 0; i < listLeft.size(); i++) {
            XueWei xw = listLeft.get(i);
            if (xw.inRect(curX, curY)) {
                this.acupointId = xw.acupointId;
                if (onXueweiClickListener != null) {
                    onXueweiClickListener.onClick(xw);
                }
                postInvalidate();
                break;
            }
        }
        for (int i = 0; i < listRight.size(); i++) {
            XueWei xw = listRight.get(i);
            if (xw.inRect(curX, curY)) {
                this.acupointId = xw.acupointId;
                if (onXueweiClickListener != null) {
                    onXueweiClickListener.onClick(xw);
                }
                postInvalidate();
                break;
            }
        }
    }

    XueweiClickListener onXueweiClickListener;

    public void setOnXueweiClickListener(XueweiClickListener onXueweiClickListener) {
        this.onXueweiClickListener = onXueweiClickListener;
    }

    public interface XueweiClickListener {
        public void onClick(XueWei xuewei);
    }
}
