package com.connect.mql.connect;

/**
 * Created by MQL on 2017/5/6.
 */

public interface onStateChangedListener {
    void onItemSelected();//Item被选择时给Item附加效果
    void onItemClear();//Item被选择完毕时清除Item上的效果
}
