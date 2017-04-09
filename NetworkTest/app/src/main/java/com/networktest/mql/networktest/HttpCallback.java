package com.networktest.mql.networktest;

import android.graphics.Bitmap;

import java.util.List;

/**
 * 实现接口回调机制的接口
 * Created by MQL on 2017/4/7.
 */

public interface HttpCallback {
    //获取成功调用
    void onFinish(List<String> names,List<Bitmap> images);
    //获取失败调用
    void onError(Exception e);
}
