package com.owm.lottery.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.owm.lottery.model.common.CGP;

/**
 * 中文TextView
 * Created by ouweiming on 2017/3/30.
 */

public class ChatTextView extends TextView {
    private Paint mTextPaint;
    private float mTextSize;

    public ChatTextView(Context context) {
        super(context);
        setTypeface(context);
    }

    public ChatTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context);
    }

    public ChatTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context);
    }

    private void setTypeface(Context context) {
        setTypeface(CGP.getCharTypeFace(context));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        refitText(this.getText().toString(), this.getWidth());
    }
    /**
     * Re size the font so the specified text fits in the text box assuming the
     * text box is the specified width.
     *
     * @param text 文本
     * @param textViewWidth 文本宽度
     */
    private void refitText(String text, int textViewWidth) {
        if (text == null || textViewWidth <= 0)
            return;
        mTextPaint = new Paint();
        mTextPaint.set(this.getPaint());
        int availableTextViewWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        float[] charsWidthArr = new float[text.length()];
        Rect boundsRect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), boundsRect);
        int textWidth = boundsRect.width();
        mTextSize = getTextSize();
        while (textWidth > availableTextViewWidth) {
            mTextSize -= 1;
            mTextPaint.setTextSize(mTextSize);
            textWidth = mTextPaint.getTextWidths(text, charsWidthArr);
        }
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
    }

}
