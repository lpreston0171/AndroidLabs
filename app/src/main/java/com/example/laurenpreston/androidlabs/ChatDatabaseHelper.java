package com.example.laurenpreston.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by laurenpreston on 2018-04-05.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Message";
    public static int VERSION_NUM = 8;
    public static String TABLE_NAME = "ChatTable";
    public static final String KEY_ID = "ID";
    public static final String KEY_MESSAGE = "Message";


    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+TABLE_NAME+"( "+KEY_ID+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_MESSAGE + " TEXT) ");
        Log.i("ChatDatabaseHelper", "Calling onCreate");
    }

    public void onUpgrade (SQLiteDatabase db, int oldVer, int newVer){
        Log.i("ChatDatabaseHelper", "Calling OnUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer){
        Log.i("ChatDatabaseHelper", "Calling onDowngrade");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    public void onOpen(SQLiteDatabase db){
        Log.i("ChatDatabaseHelper","DATABASE opened");
    }

}
