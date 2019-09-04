package com.mad.lk.Film_Details_Maintain.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Utils {
    public static  byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }
    public static Bitmap getImage(byte[] photo1, byte[] photo2, byte[] photo3, byte[] photo4){
        BitmapFactory.decodeByteArray(photo1,0,photo1.length);
        BitmapFactory.decodeByteArray(photo2,0,photo2.length);
        BitmapFactory.decodeByteArray(photo3,0,photo3.length);
      return   BitmapFactory.decodeByteArray(photo4,0,photo4.length);


    }
}
