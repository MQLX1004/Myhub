package com.connect.mql.connect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by MQL on 2017/5/16
 */
public class LetterIndexView extends View {
    private static final String TAG = "LetterIndexView";
    //当前手指滑动到的位置 没有滑动时为-1
    private int choosedPosition = -1;
    //画文字的画笔
    private Paint paint;
    //右边的所有文字
    private String[] letters = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    //页面正中央的TextView，用来显示手指当前滑动到的位置的文本
    private TextView textViewDialog;
    //接口变量，该接口主要用来实现当手指在右边的滑动控件上滑动时RecycleView能够跟着滚动
    private UpdateListView updateListView;

    public LetterIndexView(Context context) {
        this(context, null);
    }

    public LetterIndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        paint.setTextSize(34);
    }
    //接收传递过来的隐藏TextView
    public void setTextViewDialog(TextView textViewDialog) {
        this.textViewDialog = textViewDialog;
    }
    /**
     * 绘制图形
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        int perTextHeight = getHeight() / letters.length;//计算每个字母的高度
        for (int i = 0; i < letters.length; i++) {
            if (i == choosedPosition) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.BLACK);
            }
            canvas.drawText(letters[i], (getWidth() - paint.measureText(letters[i])) / 2, (i + 1) * perTextHeight, paint);
        }
    }

    /**
     * 处理触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int perTextHeight = getHeight() / letters.length;//获取每个字母的高度
        float y = event.getY();
        int currentPosition = (int) (y / perTextHeight);
        String letter = letters[currentPosition];
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP://如果事件为不触摸
                setBackgroundColor(Color.TRANSPARENT);//设置背景色
                if (textViewDialog != null) {
                    textViewDialog.setVisibility(View.GONE);//隐藏TextView
                }
                break;
            default:
                setBackgroundColor(Color.parseColor("#cccccc"));//设置背景色
                if (currentPosition > -1 && currentPosition < letters.length) {
                    if (textViewDialog != null) {
                        textViewDialog.setVisibility(View.VISIBLE);//显示TextView
                        textViewDialog.setText(letter);//设置文字
                    }
                    if (updateListView != null) {
                        updateListView.updateListView(letter);//接口回调
                    }
                    choosedPosition = currentPosition;//将选择位置更新为当前位置
                }
                break;
        }
        invalidate();//刷新
        return true;
    }

    public void setUpdateListView(UpdateListView updateListView) {
        this.updateListView = updateListView;
    }
    //点击字母后回调的接口
    public interface UpdateListView {
        public void updateListView(String currentChar);
    }

    public void updateLetterIndexView(int currentChar) {
        for (int i = 0; i < letters.length; i++) {
            if (currentChar == letters[i].charAt(0)) {
                choosedPosition = i;
                invalidate();
                break;
            }
        }
    }
}
