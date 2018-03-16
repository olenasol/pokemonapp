package com.example.olena.pokemonapp.util;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class ImageUtil {

    public static byte[] getImgToByteFromURL(String urlS) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap image = null;
        try {
            image = new GetImageAsyncTask().execute(urlS).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (image != null) {
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        }
        return stream.toByteArray();
    }

    private static class GetImageAsyncTask extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(strings[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                if (url != null){
                    return BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }
                else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public static Bitmap byteArrToBitmap(byte[] data){
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

}
