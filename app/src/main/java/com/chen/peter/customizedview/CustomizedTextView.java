package com.chen.peter.customizedview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


public class CustomizedTextView extends android.support.v7.widget.AppCompatTextView {

    private  Rect  mRect;
    private  Paint mPaint;

    public CustomizedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0x800000FF);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0; i<getLineCount();i++){
            int baseline = getLineBounds(i,mRect);
            canvas.drawLine(mRect.left, baseline+1, mRect.right, baseline+1,mPaint );
        }
        super.onDraw(canvas);
    }
}
