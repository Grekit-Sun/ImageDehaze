package com.yifan.dehaze.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BitmapUtil {


    /**
     * 写入bitmap
     *
     * @param bmp
     * @param filename
     */
    public static void saveBitmap2file(Bitmap bmp, String filename) {
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/" + filename + ".jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        try {
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读出bitmap
     *
     * @param filename
     * @return
     */
    public static Bitmap getBitmapFromFile(String filename) {
        InputStream stream = null;
        try {
            stream = new FileInputStream(Environment.getExternalStorageDirectory().getPath() + "/" + filename + ".jpg");
            return BitmapFactory.decodeStream(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bitmap转Base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmaoToBase64(Bitmap bitmap) {
        String res = null;
        ByteArrayOutputStream os = null;
        if (bitmap != null) {
            os = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            try {
                os.flush();
                os.close();

                byte[] bitmapByte = os.toByteArray();
                res = Base64.encodeToString(bitmapByte, Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    try {
                        os.flush();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return res;
    }

    /**
     * base64转bitmap
     * @param base64Data
     * @return
     */
    public  static  Bitmap base64ToBitmap(String base64Data){
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

}
