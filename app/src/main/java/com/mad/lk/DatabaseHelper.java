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

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABSE_NAME = "Data.db";

    public static final String USER_TABLE_NAME = "User_Details";
    public static final String FILM_TABLE_NAME = "Film_Details";
    public static final String NOTICE_TABLE_NAME = "Notices";
    public static final String Favourite_TABLE_NAME = "Favorite_Details";
    public static final String Booking_TABLE_NAME = "booking";



    public DatabaseHelper(Context context)
    {
        super(context, DATABSE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + NOTICE_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,MESSAGE TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT NOT NULL,IMAGE BLOB NOT NULL,EMAIL TEXT NOT NULL,PASSWORD TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + FILM_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,IMAGE BLOB NOT NULL,NAME TEXT NOT NULL,DESCRIPTION TEXT NOT NULL,RANKINGS TEXT NOT NULL,DATE TEXT NOT NULL,TIME TEXT NOT NULL,SEATS INT NOT NULL) ");
        db.execSQL("CREATE TABLE " + Booking_TABLE_NAME + "(ID INTEGER PRIMARY KEY NOT NULL,FILMNAME TEXT NOT NULL,SEATCOUNT int NOT NULL,TYPE TEXT NOT NULL,TIME TEXT NOT NULL,DATE TEXT NOT NULL,IMAGE BLOB NOT NULL) " );
        db.execSQL("CREATE TABLE " + Favourite_TABLE_NAME + "(" + "ID INTEGER PRIMARY KEY ," + "IMAGE BLOB," + "NAME TEXT NOT NULL," + "DESCRIPTION TEXT NOT NULL," + "RANKINGS TEXT NOT NULL," + "DATE TEXT NOT NULL," + "TIME TEXT NOT NULL," + "SEATS INT NOT NULL," + "NOTES TEXT NOT NULL," + "USERNAME TEXT NOT NULL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FILM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Favourite_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Booking_TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String username,String email,String password,byte[] image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("USERNAME",username);
        contentValues.put("EMAIL",email);
        contentValues.put("PASSWORD",password);
        contentValues.put("IMAGE",image);
        Long result = db.insert(USER_TABLE_NAME,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean insertNotice(String message)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MESSAGE",message);

        Long ret = db.insert(NOTICE_TABLE_NAME,null,contentValues);

        if(ret == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor getAllNotices()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + NOTICE_TABLE_NAME,null);
        return cursor;
    }
    public Cursor getAllUserData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME,null);
        return cursor;
    }
    public boolean updateSeatCount(String id,int seats)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SEATS", seats);
        if(db.update(FILM_TABLE_NAME, contentValues, "ID=?", new String[] {id}) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isUserNameExist(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE USERNAME = '" + name + "'",null);
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
    public boolean updateUserData(String preUserName,String username,String password,String email,byte[] image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if(image == null)
        {
            contentValues.put("USERNAME", username);
            contentValues.put("PASSWORD", password);
            contentValues.put("EMAIL", email);
        }
        else
        {
            contentValues.put("USERNAME", username);
            contentValues.put("PASSWORD", password);
            contentValues.put("IMAGE", image);
            contentValues.put("EMAIL", email);
        }

        if(db.update(USER_TABLE_NAME, contentValues, "USERNAME=?", new String[] {preUserName}) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }


    }
    public Cursor getUserData(String uname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME +" WHERE USERNAME = '" + uname + "'",null);
        return cursor;
    }
    public Cursor getFilmData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FILM_TABLE_NAME +" WHERE ID = '" + id + "'",null);
        return cursor;
    }
    public int getTicketCount(String id)
    {
        Cursor cursor = getFilmData(id);
        while (cursor.moveToNext())
        {
            return cursor.getInt(7);
        }
        return 0;

    }
    public Cursor getAllFimsDetails()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FILM_TABLE_NAME,null);
        return cursor;
    }
    public boolean deleteAUser(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int val = db.delete(USER_TABLE_NAME, "ID =?", new String[]{id});

        if(val > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
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
    public boolean deleteANotice(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(NOTICE_TABLE_NAME, "ID =?", new String[]{id});

        if(res > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean updateNotice(String message,String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MESSAGE", message);
        long res = db.update(NOTICE_TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        if(res == -1)
        {
            return false;
        }
        else
        {
            return true;
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
    public boolean updateAFilmWithImage(String fname,String fdes,String frankings,String fdate,String ftime,int fseats,byte[] image,String id)
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

        long res = db.update(FILM_TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        if(res <= -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public boolean updateAFilmWithoutImage(String fname,String fdes,String frankings,String fdate,String ftime,int fseats,String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", fname);
        contentValues.put("DESCRIPTION", fdes);
        contentValues.put("RANKINGS", frankings);
        contentValues.put("DATE", fdate);
        contentValues.put("TIME", ftime);
        contentValues.put("SEATS",fseats);

        long res = db.update(FILM_TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        if(res <= -1)
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
        if(bitmap != null)
        {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }
        return  null;

    }
    public Bitmap getImage(byte[] image)
    {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    /*Tharsha's methods*/

    public Cursor getAllFavorites(String fusername) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Favourite_TABLE_NAME + " WHERE USERNAME = '" + fusername + "'",  null);
        return cursor;
    }
    public Cursor getAllFavorites(String fusername,String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Favourite_TABLE_NAME + " WHERE USERNAME = '" + fusername + "' AND ID = '" + id +"'",  null);
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

        Long result = db.insert(Favourite_TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }
    public boolean deleteFavoriteFilm(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int val = db.delete(Favourite_TABLE_NAME, "ID =?", new String[]{id});

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

        long res = db.update(Favourite_TABLE_NAME,contentValues,"ID = ?",new String[]{id});
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
        Cursor cursor = db.rawQuery("SELECT * FROM " + Favourite_TABLE_NAME + " WHERE ID = " + id, null);
        if(cursor.getCount() <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /*Lalindu's methods*/

    public boolean insertbooking(String filmname,int seats,String type,String time,String date,byte[] image,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("FILMNAME",filmname);
        contentValues.put("SEATCOUNT",seats);
        contentValues.put("TYPE",type);
        contentValues.put("TIME",time);
        contentValues.put("DATE",date);
        contentValues.put("IMAGE",image);

        long result=db.insert(Booking_TABLE_NAME,null,contentValues);

        if(result <= 0) {
            return false;
        }
        else
        {
            return true;
        }

    }

    public Cursor getAllbooking()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+Booking_TABLE_NAME,null);
        return res;
    }

    public boolean updatebooking(String booking_ID,int seatqua,String spinner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",booking_ID);
        contentValues.put("FILMNAME",seatqua);
        contentValues.put("SEATCOUNT",spinner);
        db.update(Booking_TABLE_NAME,contentValues, "ID = ?",new String[]{booking_ID});
        return true;
    }

    public boolean deletebooking(String booking_ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long res =  db.delete(Booking_TABLE_NAME,"ID = ?",new String[] {booking_ID});
        if(res <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean isTranscationAlreadyExist(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Booking_TABLE_NAME + " WHERE ID = " + id, null);
        if(cursor.getCount() <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean updateBooking(String seats, String id, String spinner){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SEATCOUNT", Integer.parseInt(seats));
        contentValues.put("TYPE", spinner);

        long res = db.update(Booking_TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        if(res <= -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
