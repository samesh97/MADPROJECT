package com.mad.lk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

//import Favorites.FavoriteContract;

import static com.mad.lk.DatabaseHelper.DATABSE_NAME;

public class FavoriteDatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Favorite.db";

    public static final String TABLE_NAME = "Favorite_Details";

    public static final String FAVORITE_COL_1 = "NAME";
    public static final String FAVORITE_COL_2 = "IMAGE";
    public static final String FAVORITE_COL_3 = "DESCRIPTION";
    public static final String FAVORITE_COL_4 = "RANKINGS";
    public static final String FAVORITE_COL_5 = "DATE";
    public static final String FAVORITE_COL_6 = "TIME";
    public static final String FAVORITE_COL_7 = "SEATS";
    public static final String FAVORITE_COL_8 = "NOTES";
    public static final String FAVORITE_COL_9 = "USERNAME";


    public FavoriteDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                "ID INTEGER PRIMARY KEY ," +
                "IMAGE BLOB," +
                "NAME TEXT NOT NULL," +
                "DESCRIPTION TEXT NOT NULL," +
                "RANKINGS TEXT NOT NULL," +
                "DATE TEXT NOT NULL," +
                "TIME TEXT NOT NULL," +
                "SEATS INT NOT NULL," +
                "NOTES TEXT NOT NULL," +
                "USERNAME TEXT NOT NULL) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


   /* public Cursor getAllFavorites() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);
        return cursor;
    }*/

    public Cursor getAllFavorites(String fusername) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = '" + fusername + "'",  null);
        return cursor;
    }

    public boolean addfavorites(int id,String fname, String fdes, String frankings, String fdate, String ftime, int fseats, byte[] fimage, String fnotes,String fusername) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("ID", id);
        contentValues.put("NAME", fname);
        contentValues.put("DESCRIPTION", fdes);
        contentValues.put("IMAGE", fimage);
        contentValues.put("RANKINGS", frankings);
        contentValues.put("DATE", fdate);
        contentValues.put("TIME", ftime);
        contentValues.put("SEATS", fseats);
        contentValues.put("NOTES", fnotes);
        contentValues.put("USERNAME", fusername);

        Long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }



    public byte[] getBytes(Bitmap fimage)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        fimage.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    public Bitmap getImage(byte[] fimage)
    {
        return BitmapFactory.decodeByteArray(fimage, 0, fimage.length);
    }



    public boolean deleteFavoriteFilm(String id)
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

    public boolean updateFavorite(String notes,String id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOTES", notes);

        long res = db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        if(res <= -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean isFilmAlreadyExistInFavourites(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id, null);
        if(cursor.getCount() <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }



}
