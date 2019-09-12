package com.mad.lk.Film_Details_Maintain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.mad.lk.DatabaseHelper.DATABSE_NAME;

public class FavoriteDatabaseHelper extends SQLiteOpenHelper {



    public static final String TABLE_NAME = "FavoriteDetails";
    public static final String FILM_COL_1 = "NAME";
    public static final String FILM_COL_2 = "DESCRIPTION";
    public static final String FILM_COL_3 = "RANKINGS";
    public static final String FILM_COL_4 = "DATE";
    public static final String FILM_COL_5 = "TIME";
    public static final String FILM_COL_6 = "SEATS";
    public static final String FILM_COL_7 = "NOTES";

    public FavoriteDatabaseHelper(Context context)
    {
        super(context, DATABSE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,IMAGE BLOB NOT NULL,NAME TEXT NOT NULL,DESCRIPTION TEXT NOT NULL,RANKINGS TEXT NOT NULL,DATE TEXT NOT NULL,TIME TEXT NOT NULL,SEATS INT NOT NULL,NOTES TEXT NOT NULL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public Cursor getAllUserData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return cursor;
    }

    public Cursor getAllFavoriteFimsDetails()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return cursor;
    }

    public boolean insertAFilm(String fname,String fdes,String frankings,String fdate,String ftime,int fseats,byte[] image,String fnotes)
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
        contentValues.put("NOTES",fnotes);

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
    public boolean deleteFilm(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int val = db.delete(TABLE_NAME, "ID =?", new String[]{id});

        if(val > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
