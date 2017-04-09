package com.networktest.mql.networktest;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int SHOW=0;
    private Button mButton;
    private TextView mTextView1;
    private ImageView mImageView1;
    private TextView mTextView2;
    private ImageView mImageView2;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW:
                    List<List> lists=(List<List>) msg.obj;
                    List<String> names=lists.get(0);
                    List<Bitmap> images=lists.get(1);
                    mTextView1.setText(names.get(0));
                    mImageView1.setImageBitmap(images.get(0));
                    mTextView2.setText(names.get(1));
                    mImageView2.setImageBitmap(images.get(1));
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=(Button)findViewById(R.id.button);
        mTextView1=(TextView)findViewById(R.id.textView1);
        mImageView1=(ImageView)findViewById(R.id.imageView1);
        mTextView2=(TextView)findViewById(R.id.textView2);
        mImageView2=(ImageView)findViewById(R.id.imageView2);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendrequset();//点击Button后发起网络请求
            }
        });
    }
    private void sendrequset(){
        HttpUtil.sendHttpRequest("http://10.0.2.2:8080/android/Json.json", new HttpCallback() {//调用网络请求静态类并重写接口中的方法
            @Override
            public void onFinish(List<String> names, List<Bitmap> images) {
                Message message=new Message();
                message.what=SHOW;
                List<List> lists=new ArrayList<List>();
                lists.add(names);
                lists.add(images);
                message.obj=lists;
                mHandler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
//                Message message=new Message();
//                message.what=SHOW;
//                message.obj="获取失败";
//                mHandler.sendMessage(message);
            }
        });
    }

}
