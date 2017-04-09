package com.networktest.mql.networktest;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MQL on 2017/4/7.
 */

public class HttpUtil {
    private static List<String> names;
    private static List<Bitmap> images;
    public static void sendHttpRequest(final String address,final HttpCallback listener) {
        names=new ArrayList<>();
        images=new ArrayList<>();
        new Thread(new Runnable() {//开启子线程
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//设置请求方式
                    connection.setConnectTimeout(8000);//设置链接超时时间
                    connection.setReadTimeout(8000);//设置读取超时时间
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    //Bitmap bitmap= BitmapFactory.decodeStream(in);
                    //listener.onFinish(bitmap);
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    try{
                        JSONArray jsonArray=new JSONArray(response.toString());
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            names.add(jsonObject.getString("name"));
                            images.add(getbitmap(jsonObject.getString("image")));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    listener.onFinish(names,images);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onError(e);//接口回掉
                } finally {
                    if (connection != null) {
                        connection.disconnect();//关闭连接
                    }
                }
            }
        }).start();

    }
    private static Bitmap getbitmap(String Url){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(Url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");//设置请求方式
            connection.setConnectTimeout(8000);//设置链接超时时间
            connection.setReadTimeout(8000);//设置读取超时时间
            connection.setDoInput(true);
            connection.setDoOutput(true);
            InputStream in = connection.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(in);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
