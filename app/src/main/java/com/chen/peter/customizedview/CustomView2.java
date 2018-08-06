package com.chen.peter.customizedview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomView2 extends LinearLayout {

    TextView testview1;
    TextView testview2;

    final static String DOWN = "Down";
    final static String UP = "Up";
    final static String MOVE = "Move";

    public CustomView2(Context context) {
        super(context);
    }

    public CustomView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.testview_layout,this,true);
        testview1 = findViewById(R.id.textview1);
        testview2 = findViewById(R.id.textview2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                testview1.setText(DOWN);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                testview1.setText(UP);
                break;
            case MotionEvent.ACTION_MOVE:
                testview1.setText(MOVE);
                break;
        }
        return true;
    }
}
