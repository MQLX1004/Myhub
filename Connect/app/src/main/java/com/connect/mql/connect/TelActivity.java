package com.connect.mql.connect;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelActivity extends AppCompatActivity {
    public static final int TELREQUEST=123;
    public static final String TELACTIVITY_NAME="TELACTIVITY_NAME";
    public static final String TELACTIVITY_TEL="LETACTIVITY_TEL";
    private TextView tname;
    private TextView ttel;
    private Button tel;
    private Button mag;
    private String name;
    private String stel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel);

        tname=(TextView)findViewById(R.id.tname);
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
        tname.setText(name);
        ttel.setText(stel);

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
            SQLiteDatabase db=ListActivity.dbHelper.getWritableDatabase();
            db.execSQL("DELETE FROM BOOK WHERE name = '"+name+"' AND" +
                    " tel = '"+stel+"';");
            finish();
        }else if(item.getItemId()==R.id.change){
            Intent intent=new Intent(this,AddActivity.class);
            intent.putExtra(TELACTIVITY_NAME,name);
            intent.putExtra(TELACTIVITY_TEL,stel);
            startActivityForResult(intent,TELREQUEST);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==TELREQUEST){
            if(resultCode==RESULT_OK){
                SQLiteDatabase db=ListActivity.dbHelper.getWritableDatabase();
                db.delete("book","tel=?",new String[]{stel});
                finish();
            }else if(resultCode==RESULT_CANCELED){
                finish();
            }
        }
    }
}
