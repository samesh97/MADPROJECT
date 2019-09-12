package com.mad.lk.Film_Details_Maintain;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper_Lali extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="Ticket.db";
    public static final String TABLE_NAME = "Ticket.table";
    public static final String COL_1 = "ID";
    public static final String COL_2 ="date";
    public static final String COL_3 ="time";
    public static final String COL_4 ="phoneNumber";
    public static final String COL_5 ="adult";
    public static final String COL_6 ="child";

    public DatabaseHelper_Lali(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,date String NOT NULL,time String NOT NULL,phoneNumber String NOT NULL,adult String NOT NULL,child String NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(db);
    }

    public boolean  insertData(String date, String time, String phoneNumber, String adult, String child){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,time);
        contentValues.put(COL_4,phoneNumber);
        contentValues.put(COL_5,adult);
        contentValues.put(COL_6,child);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1){
            return  false;
        }
        else{
            return true;
        }
    }
}
