package com.connect.mql.connect;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by MQL on 2017/5/6.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private onMoveAndSwipedListener mAdapter;
    private onStateChangedListener listener;
    public ItemTouchHelperCallback(onMoveAndSwipedListener adapter) {
        mAdapter = adapter;
    }

    //此方法设置拖动方向和侧滑方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //设置拖拽方向为上下
        final int dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        //设置侧滑方向为左右
        final int swipeFlags=ItemTouchHelper.END;
        //设置方向参数
        return makeMovementFlags(dragFlags,swipeFlags);
    }
    //当我们拖动Item时调用此方法
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //回调Adapter中的方法
        //mAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }
    //当我们侧滑Item时调用此方法
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(direction==ItemTouchHelper.END) {//判断，如果Item滑动方向为右
            mAdapter.onItemRight(viewHolder.getAdapterPosition());
//            listener=(onStateChangedListener)viewHolder;
//            listener.onItemSelected();
        }else if(direction==ItemTouchHelper.START){//判断，如果Item滑动方向为左
            mAdapter.onItemLift(viewHolder.getAdapterPosition());
        }
    }
    //当Item改变状态时调用此方法
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){//判断，如果Item状态为不空闲状态，表明Item正在被操作
            listener=(onStateChangedListener)viewHolder;
            listener.onItemSelected();
        }
        super.onSelectedChanged(viewHolder, actionState);
    }
    //当用户拖拽或者侧滑完一个Item时调用
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        listener=(onStateChangedListener)viewHolder;
        listener.onItemClear();
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){

        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
