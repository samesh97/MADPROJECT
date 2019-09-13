package com.mad.lk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.annotation.Target;

public class DBHelper_Lali extends SQLiteOpenHelper {

    public static final String DATABSE_NAME = "booking.db";
    public static final String TABLE_NAME = "booking";
    public static final String COL_1 = "booking_ID";
    public static final String COL_2 = "seatqua";
    public static final String COL_3 = "spinner";

    public DBHelper_Lali(Context context) {
        super(context, DATABSE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,seats int NOT NULL,apinner TEXT NOT NULL) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertbooking(int seatqua,String spinner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,seatqua);
        contentValues.put(COL_3,spinner);
        long result=db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllbooking(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
