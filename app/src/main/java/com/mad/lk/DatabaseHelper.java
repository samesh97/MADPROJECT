package com.mad.lk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABSE_NAME = "Data.db";


    public static final String TABLE_NAME = "User_Details";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";

    public static final String FILM_TABLE_NAME = "Film_Details";



    public DatabaseHelper(Context context)
    {
        super(context, DATABSE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT NOT NULL,IMAGE BLOB NOT NULL,EMAIL TEXT NOT NULL,PASSWORD TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + FILM_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,IMAGE BLOB NOT NULL,NAME TEXT NOT NULL,DESCRIPTION TEXT NOT NULL,RANKINGS TEXT NOT NULL,DATE TEXT NOT NULL,TIME TEXT NOT NULL,SEATS INT NOT NULL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FILM_TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String username,String email,String password,byte[] image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,username);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,password);
        contentValues.put("IMAGE",image);
        Long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor getAllUserData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return cursor;
    }
    public boolean isUserNameExist(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = '" + name + "'",null);
        if(cursor.getCount() == 0)
        {
            cursor.close();
            return false;
        }
        else
        {
            cursor.close();
            return true;
        }

    }
    public boolean update(String username,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", username);
        contentValues.put("PASSWORD", password);


        if(db.update(TABLE_NAME, contentValues, "USERNAME=?", new String[] {username}) == 0)
        {
            return false;
        }
        else
        {
            return true;
        }


    }
    public Cursor getUserData(String uname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +" WHERE USERNAME = '" + uname + "'",null);
        return cursor;
    }
    public Cursor getAllFimsDetails()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FILM_TABLE_NAME,null);
        return cursor;
    }
    public boolean deleteAFilm(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int val = db.delete(FILM_TABLE_NAME, "ID =?", new String[]{id});

        if(val > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean insertAFilm(String fname,String fdes,String frankings,String fdate,String ftime,int fseats,byte[] image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", fname);
        contentValues.put("DESCRIPTION", fdes);
        contentValues.put("IMAGE", image);
        contentValues.put("RANKINGS", frankings);
        contentValues.put("DATE", fdate);
        contentValues.put("TIME", ftime);
        contentValues.put("SEATS",fseats);
        Long result = db.insert(FILM_TABLE_NAME,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public byte[] getBytes(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    public Bitmap getImage(byte[] image)
    {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
