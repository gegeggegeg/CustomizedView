package com.chen.peter.customizedview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class EmotionView extends View implements View.OnClickListener {

    private Paint mPaint;
    private int size = 320;
    private RectF rectF;
    private Path mPath;

    public static final long HAPPY = 0L;
    public static final long SAD = 1L;

    private long happinessState = HAPPY;
    private int faceColor = Color.YELLOW;
    private int eyeColor = Color.BLACK;
    private int mouthColor = Color.BLACK;
    private int borderColor = Color.BLACK;
    private float borderWidth = 4.0f;
    private Canvas canvas;

    private static final String TAG = "EmotionView";


    public EmotionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupAttributes(attrs);
        mPaint = new Paint();
        rectF = new RectF();
        mPath = new Path();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(faceColor);
        mPaint.setStyle(Paint.Style.FILL);
        float radius = size/2f;
        canvas.drawCircle(radius,radius,radius,mPaint);
        mPaint.setColor(eyeColor);
        rectF.set(size*0.32f,size*0.23f, size*0.43f,size*0.50f);
        canvas.drawOval(rectF,mPaint);
        rectF.set(size*0.57f,size*0.23f,size*0.68f,size*0.50f);
        canvas.drawOval(rectF,mPaint);
        mPath.moveTo(size*0.22f,size*0.7f);
        if(happinessState == HAPPY) {
            mPath.quadTo(size * 0.57f, size * 0.80f, size * 0.78f, size * 0.70f);
            mPath.quadTo(size * 0.57f, size * 0.90f, size * 0.22f, size * 0.70f);
            Log.d(TAG, "onDraw: draw happy");
        }else if(happinessState == SAD){
            mPath.quadTo(size * 0.5f, size * 0.50f, size * 0.78f, size * 0.7f);
            mPath.quadTo(size * 0.5f, size * 0.60f, size * 0.22f, size * 0.7f);
            Log.d(TAG, "onDraw: draw sad");
        }
        mPaint.setColor(borderColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mPath,mPaint);
        mPath.reset();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        size = Math.min(getMeasuredWidth(),getMeasuredHeight());
        setMeasuredDimension(size,size);
    }

    private void setupAttributes(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.EmotionView);
        faceColor = typedArray.getColor(R.styleable.EmotionView_faceColor, Color.YELLOW);
        eyeColor = typedArray.getColor(R.styleable.EmotionView_eyesColor,Color.BLACK);
        mouthColor = typedArray.getColor(R.styleable.EmotionView_mouthColor,Color.BLACK);
        borderColor = typedArray.getColor(R.styleable.EmotionView_borderColor,Color.BLACK);
        borderWidth = typedArray.getDimension(R.styleable.EmotionView_borderWidth,4.0f);
        happinessState = ((long) typedArray.getInt(R.styleable.EmotionView_state, (int) HAPPY));
        typedArray.recycle();
    }

    public long getHappinessState() {
        return happinessState;
    }

    public void setHappinessState(long happinessState) {
        this.happinessState = happinessState;
    }


    @Override
    public void onClick(View view) {
        if(getHappinessState() == HAPPY) {
            setHappinessState(SAD);
            Log.d(TAG, "onClick: " + getHappinessState());
        }else{
            setHappinessState(HAPPY);
            Log.d(TAG, "onClick: "+getHappinessState());
        }
        invalidate();
    }

    @Override
    public void sendAccessibilityEvent(int eventType) {
        super.sendAccessibilityEvent(eventType);
    }
}
