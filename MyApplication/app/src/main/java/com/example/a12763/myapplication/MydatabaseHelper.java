package com.example.a12763.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 12763 on 2017/12/8.
 */

public class MydatabaseHelper extends SQLiteOpenHelper{
    private Context mContext;
    public MydatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory ,int version)
    {
       super(context,name,factory,version);
        mContext=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Login (userid text primary key,password text)");
        Toast.makeText(mContext,"数据库创建成功",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
