package com.connect.mql.connect;

/**
 * Created by MQL on 2017/5/6.
 */

public interface onMoveAndSwipedListener {
    boolean onItemMove(int fromPosition,int toPosition);//Item被移动时调用
    void onItemRight(int position);//Item被右滑时调用
    void onItemLift(int position);//Item被左滑的时候调用
}
