package com.connect.mql.connect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HuanyingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huanying);

        Thread timer=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openCalculator=new Intent(HuanyingActivity.this,ListActivity.class);
                    startActivity(openCalculator);
                }
            }
        };
        timer.start();
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
