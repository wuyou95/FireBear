package com.lanou.wuyou.fearbear.consumption;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dllo on 17/4/19.
 */

public class PointView extends View{
    private int width;
    private int height;
    private static final int PADDING_WIDTH = 1000;
    private Paint paint;
    public PointView(Context context) {
        this(context,null);
    }

    public PointView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height=  MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
