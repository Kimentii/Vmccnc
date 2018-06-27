package com.mzal;

import android.arch.core.util.Function;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class ImageDownloaderAsyncTask extends AsyncTask<Void, Void, Bitmap> {
    private static final String TAG = ImageDownloaderAsyncTask.class.getSimpleName();

    private ImageView mImageView;
    private String mUrl;
    private Function<Bitmap, Void> mSaveFunction;

    public ImageDownloaderAsyncTask(ImageView imageView, String url, Function<Bitmap, Void> saveFunction) {
        this.mImageView = imageView;
        this.mUrl = url;
        this.mSaveFunction = saveFunction;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap result = getBitmapFromURL(mUrl);
        if (mSaveFunction != null) {
            mSaveFunction.apply(result);
        }
        return result;
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        mImageView.setImageBitmap(image);
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            long startTime = System.currentTimeMillis();
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            long endTime = System.currentTimeMillis();
            Log.d(TAG, "getBitmapFromURL: downloading time: " + String.valueOf(endTime - startTime));
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
