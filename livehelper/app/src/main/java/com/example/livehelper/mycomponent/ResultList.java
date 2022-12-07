package com.zrkaxt.testmachine.mycomponent;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ResultList  extends ListView {


        public ResultList(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //根据模式计算每个child的高度和宽度
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }

}
