package com.connect.mql.connect;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.datebase.mql.connect.ConnectDbhelp;

public class AddActivity extends AppCompatActivity {
    private EditText name;
    private EditText tel;
    private Button yes;
    private Button no;
    private String tname;
    private String ttel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name=(EditText)findViewById(R.id.addName);
        tel=(EditText)findViewById(R.id.addTel);
        yes=(Button)findViewById(R.id.yesAdd);
        no=(Button)findViewById(R.id.noAdd);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                setResult(RESULT_CANCELED,intent);//向调用此Activity的Activity返回数据
                finish();//关闭此Activity
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tname=name.getText().toString();//从EdiText获取用户输入的文本
                ttel=tel.getText().toString();
                if(tname.equals("")&&ttel.equals("")){//如果文本为空，无法提示保存
                    Toast.makeText(AddActivity.this,"没有可保存的内容，无法保存",Toast.LENGTH_SHORT).show();
                }else{
                    //向数据库插入数据
                    SQLiteDatabase db=ListActivity.dbHelper.getWritableDatabase();
                    ContentValues values=new ContentValues();
                    values.put("name",tname);
                    values.put("tel",ttel);
                    db.insert("book",null,values);
                }
                //向父Activity返回数据
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                //关闭Activity
                finish();
            }
        });
        //获取调用此Activity的Intent
        Intent intent=getIntent();
        //获取intent上附带的信息
        if(intent.getSerializableExtra(TelActivity.TELACTIVITY_NAME)!=null){
            String sname=intent.getStringExtra(TelActivity.TELACTIVITY_NAME);//提取intent附带的信息
            String stel=intent.getStringExtra(TelActivity.TELACTIVITY_TEL);
            name.setText(sname);
            tel.setText(stel);
        }
    }
}
