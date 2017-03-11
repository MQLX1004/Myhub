package com.datebase.mql.connect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by MQL on 2017/3/6.
 */
public class ConnectDbhelp extends SQLiteOpenHelper {
    private Context mContext;
    private static final String CREATE_TABLE="create table book ("
            +"id integer primary key autoincrement,"
            +"name String,"
            +"tel String)";
    public ConnectDbhelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);//创建数据库
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
