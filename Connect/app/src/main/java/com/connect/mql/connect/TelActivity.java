package com.connect.mql.connect;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelActivity extends AppCompatActivity implements DialogDelete {
    public static final int TELREQUEST=123;
    public static final String TELACTIVITY_NAME="TELACTIVITY_NAME";
    public static final String TELACTIVITY_TEL="LETACTIVITY_TEL";
    private TextView ttel;
    private Button tel;
    private Button mag;
    private String name;
    private String stel;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel);

        ttel=(TextView)findViewById(R.id.ttel);
        tel=(Button)findViewById(R.id.telyes);
        mag=(Button)findViewById(R.id.message);



        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("android.intent.action.CALL",Uri.parse("tel:"+stel));
                startActivity(intent);
            }
        });

        mag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("android.intent.action.SENDTO",Uri.parse("smsto:"+stel));
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        name=intent.getStringExtra(ListActivity.CONTENT_NAME);
        stel=intent.getStringExtra(ListActivity.CONTENT_TEL);
        ttel.setText(stel);

        mToolbar=(Toolbar)findViewById(R.id.toobar);
        setSupportActionBar(mToolbar);//把布局中的Toolbar当作ActionBar
        getSupportActionBar().setTitle(name);//设置标题

        //设置返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.detilemenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.delete){
            FragmentManager manager=getSupportFragmentManager();
            Dialog_del dialog=new Dialog_del(this);
            dialog.show(manager,"TelActivity");
        }else if(item.getItemId()==R.id.change){
            Intent intent=new Intent(this,AddActivity.class);
            intent.putExtra(TELACTIVITY_NAME,name);
            intent.putExtra(TELACTIVITY_TEL,stel);
            startActivityForResult(intent,TELREQUEST);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    //删除数据
    @Override
    public void delete() {
        SQLiteDatabase db=ListActivity.dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM "+Group_adapt.name+" WHERE name = '"+name+"' AND" +
              " tel = '"+stel+"';");
        Toast.makeText(this,"已删除："+name,Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void quxiaodelete() {

    }
}
