package com.connect.mql.connect;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.datebase.mql.connect.ConnectDbhelp;

public class Create_group extends AppCompatActivity {

    private ConnectDbhelp mDbhelp;
    private Button create;
    private Button add;
    private EditText groupname;
    private Button quxiao;
    private Button baocun;
    private String det_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        groupname=(EditText)findViewById(R.id.addgroup);
        quxiao=(Button)findViewById(R.id.noAddgroup);
        baocun=(Button)findViewById(R.id.yesAddgroup);

        mDbhelp=new ConnectDbhelp(this,"Connects.db",null,1);//连接数据库
        final SQLiteDatabase sqLiteDatabase=mDbhelp.getWritableDatabase();//打开数据库

        create=(Button)findViewById(R.id.create);
        add=(Button)findViewById(R.id.addcy);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Create_group.this,AddActivity.class);//定义显式Intent
                startActivity(intent);//开始此意图
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=groupname.getText().toString();
                det_name=name;
                if(name.equals("")){
                    Toast.makeText(Create_group.this,"群组名为空！无法创建",Toast.LENGTH_SHORT).show();
                }else{
                    mDbhelp.setTablename(PinYin.getPinYin(name));//设置表名
                    mDbhelp.onCreate(sqLiteDatabase);//创建表
                    Toast.makeText(getApplicationContext(),"创建群组 "+name+" 成功",Toast.LENGTH_SHORT).show();
                    ContentValues values=new ContentValues();
                    values.put("tablename",name);
                    sqLiteDatabase.insert("alltable",null,values);//插入一个数据
                    add.setClickable(true);//打开添加成员按钮
                    add.setBackground(getResources().getDrawable(R.drawable.add_man));//为添加成员按钮设置背景
                }

            }
        });
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(det_name!=null){
                    sqLiteDatabase.execSQL("drop table if exists "+PinYin.getPinYin(det_name)+";");//删除不想保存的的表
                    sqLiteDatabase.execSQL("DELETE FROM "+"alltable"+" WHERE tablename = '"+det_name+"';");
                }
                finish();
            }
        });
    }
}
