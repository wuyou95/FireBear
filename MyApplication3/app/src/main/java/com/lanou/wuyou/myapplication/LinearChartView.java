package com.lanou.wuyou.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dllo on 17/4/18.
 */

public class LinearChartView extends View{
    private int[] values = new int[5];
    private String[] names = new String[5];
    private Paint paint;
    private static final int PADDING_WIDTH = 100;
    private static final int PADDING_HEIGHT = 150;
    private static final int COUNT_X_MARK = 5;
    private static final int COUNT_Y_MARK = 5;
    private int width;
    private int height;

    public LinearChartView(Context context) {
        this(context,null);
    }
    public LinearChartView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public LinearChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti();
    }

    private void inti() {
        values[0] = 10;
        values[1] = 20;
        values[2] = 30;
        values[3] = 40;
        values[4] = 50;

        names[0] = "一月";
        names[1] = "二月";
        names[2] = "三月";
        names[3] = "四月";
        names[4] = "五月";

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAxisX(canvas);
        drawAxisY(canvas);
        drawMarkX(canvas);
        drawMarkY(canvas);
    }

    private void drawMarkY(Canvas canvas) {

    }

    private void drawMarkX(Canvas canvas) {
        int length = (width - PADDING_WIDTH * 2) / COUNT_X_MARK;
        for (int i = 0; i < 5; i++) {
            float startX = PADDING_WIDTH + length * i + 1;
            float startY = width - PADDING_HEIGHT - 20;
            float stopY = width - PADDING_HEIGHT;
            canvas.drawLine(startX,startY,startX,stopY,paint);
            float x = startX - 20;
            float y = stopY + 20;
            paint.setTextSize(40);
            canvas.drawText(names[i],x,y,paint);
        }
    }

    private void drawAxisX(Canvas canvas) {
        float startX = PADDING_WIDTH;
        float startY = width - PADDING_HEIGHT;
        float stopX = width - PADDING_WIDTH;
        float stopY = width - PADDING_HEIGHT;
        paint.setColor(0xff0000);
        canvas.drawLine(startX,startY,stopX,stopY,paint);
    }
    private void drawAxisY(Canvas canvas){
        float startY = PADDING_HEIGHT;
        float stopY = height - PADDING_HEIGHT;
        float startX = PADDING_WIDTH;
        float stopX = PADDING_WIDTH;
        paint.setColor(0xff0000);
        canvas.drawLine(startX,startY,stopX,stopY,paint);
    }
}
