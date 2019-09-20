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
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FILMNAME";
    public static final String COL_3 = "SEATCOUNT";
    public static final String COL_4 = "TYPE";
    public static final String COL_5 = "TIME";
    public static final String COL_6 = "DATE";

    public DBHelper_Lali(Context context) {
        super(context, DATABSE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FILMNAME TEXT NOT NULL,SEATCOUNT int NOT NULL,TYPE TEXT NOT NULL,TIME TEXT NOT NULL,DATE TEXT NOT NULL) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertbooking(String filmname,int seats,String type,String time,String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,filmname);
        contentValues.put(COL_3,seats);
        contentValues.put(COL_4,type);
        contentValues.put(COL_5,time);
        contentValues.put(COL_6,date);

        long result=db.insert(TABLE_NAME,null,contentValues);

        if(result <= 0) {
            return false;
        }
        else
        {
            return true;
        }

    }

    public Cursor getAllbooking(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updatebooking(String booking_ID,int seatqua,String spinner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,booking_ID);
        contentValues.put(COL_2,seatqua);
        contentValues.put(COL_3,spinner);
        db.update(TABLE_NAME,contentValues, "booking_ID = ?",new String[]{booking_ID});
        return true;
    }

    public Integer deletebooking(String booking_ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"booking_ID = ?",new String[] {booking_ID});
    }
}
