package com.example.administrator.framstacktestapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import com.rey.material.widget.Button;

/**
 * 目前还有bug，当text为英文字符时，会有三个字符换行，无关长短
 * Created by Administrator on 2016/12/17.
 */

public class LeftDrawableButton extends Button {

    private Drawable[] drawables;
    private float textWidth;
    private float bodyWidth;


    public LeftDrawableButton(Context context) {
        super(context);
        init();
    }

    public LeftDrawableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LeftDrawableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        drawables = getCompoundDrawables();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        textWidth = getPaint().measureText(getText().toString());
        Drawable drawableLeft = drawables[0];//left、top、right、bottom,left——>0
        int totalWidth = getWidth();
        if (drawableLeft != null) {
            int drawableWidth = drawableLeft.getIntrinsicWidth();
            int drawablePadding = getCompoundDrawablePadding() * 2;
            bodyWidth = textWidth + drawableWidth + drawablePadding;
            Log.e("TAG ", "textWidth: " + textWidth + "  drawableWidth: " + drawableWidth + "  drawablePadding:" + drawablePadding + "  compoundPaddingRight: " + getCompoundPaddingRight());
            setPadding(0, 0, (int) (totalWidth - bodyWidth), 0);//目前还有bug，当text为英文字符时，会有三个字符换行，无关长短

        }
    }

    public void setText(String text) {
        if (text.equals(getText().toString()))
            return;
        super.setText(text);
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        canvas.translate((width - bodyWidth) / 2, 0);
        super.onDraw(canvas);
    }
}
