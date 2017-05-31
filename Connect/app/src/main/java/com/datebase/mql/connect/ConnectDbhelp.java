package com.datebase.mql.connect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by MQL on 2017/3/6.
 */
public class ConnectDbhelp extends SQLiteOpenHelper {
    private static final String TAG = "ConnectDbhelp";
    private Context mContext;
    private String tablename;
    private static final String CREATE_TABLE="create table if not exists ";
    private static final String CREAT2=" ("
            +"id integer primary key autoincrement,"
            +"name String,"
            +"letter String,"
            +"tel String)";
    public ConnectDbhelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }
    public void setTablename(String tablename){
        this.tablename=tablename;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if (tablename.equals("alltable")){
            sqLiteDatabase.execSQL("create table if not exists alltable"+"(tablename String not NULL);");//创建数据库

        }else{
            sqLiteDatabase.execSQL(CREATE_TABLE+tablename+CREAT2);//创建数据库
            Log.d(TAG, "onCreate: 创建成功"+tablename);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
