package com.example.wyr.cbimh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by wyr on 2018/3/21.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_USERTABLE = "create table UserTable(" +
            "id integer primary key autoincrement," +
            "phoneNumber text" +
            "password text," +
            "name text," +
            "school text," +
            "class text)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USERTABLE);
        Toast.makeText(mContext,"Create succeeded", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists UserTable");
        onCreate(db);
    }
}
