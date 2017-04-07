package com.networktest.mql.networktest;

import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    public static final int SHOW=0;
    private Button mButton;
    private TextView mTextView;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW:
                    String respone=(String)msg.obj;
                    mTextView.setText(respone);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=(Button)findViewById(R.id.button);
        mTextView=(TextView)findViewById(R.id.textView);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendrequset();
            }
        });
    }
    private void sendrequset(){
        HttpUtil.sendHttpRequest("http://10.0.2.2:8080/android/Json.json", new HttpCallback() {
            @Override
            public void onFinish(String response) {
                Message message=new Message();
                message.what=SHOW;
                message.obj=response;
                mHandler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                Message message=new Message();
                message.what=SHOW;
                message.obj="获取失败";
                mHandler.sendMessage(message);
            }
        });
    }

}
