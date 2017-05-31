package com.connect.mql.connect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;

/**
 * Created by MQL on 2017/5/17.
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SectionDecoration";
    private DecorationCallback mCallback;
    private TextPaint mTextPaint;
    private Paint paint;
    private int dividerHeight;
    private String flag;
    private Paint.FontMetrics fontMetrics;

    public SectionDecoration(Context context,DecorationCallback callback) {
        Resources res=context.getResources();
        dividerHeight=res.getDimensionPixelSize(R.dimen.letter);

        this.mCallback = callback;
        paint=new Paint();
        paint.setColor(res.getColor(R.color.letter));
        mTextPaint=new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(70);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.getFontMetrics(fontMetrics);
        fontMetrics = new Paint.FontMetrics();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int childCount=parent.getChildCount();
        flag=mCallback.getGroupFirstLine(0);
        for (int i=0;i<childCount;i++){
            if((isFirstInGroup(i))||i==0){
                outRect.top=dividerHeight;
            }else{
                outRect.top=0;
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount=parent.getChildCount();
        flag=mCallback.getGroupFirstLine(0);
        for (int i=0;i<childCount;i++){
            c.save();
            if (isFirstInGroup(i)||i==0){
                View view= parent.getChildAt(i);
                float top=view.getTop()-dividerHeight;
                float bottom=view.getTop();
                float left=parent.getPaddingLeft();
                float right=parent.getWidth()-parent.getPaddingRight();
                c.drawRect(left,top,right,bottom,paint);
                c.drawText(flag,left+50,bottom-10,mTextPaint);
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }
    //判断第一个字母
    private boolean isFirstInGroup(int pos) {
        String letter=mCallback.getGroupFirstLine(pos);
        if (letter.equals(flag)){
            return false;
        }else{
            flag=letter;
            return true;
        }
    }
}
