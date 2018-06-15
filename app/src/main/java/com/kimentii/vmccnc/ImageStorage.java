package com.kimentii.vmccnc;

import android.arch.core.util.Function;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ImageStorage {
    private static final String TAG = ImageStorage.class.getSimpleName();

    private static ConcurrentHashMap<String, Bitmap> imagePool = new ConcurrentHashMap<>();

    public static void setImageFromUrlToImageView(ImageView imageView, final String url) {
        Bitmap imageBitmap = imagePool.get(url);
        if (imageBitmap == null) {
            Log.d(TAG, "setImageFromUrlToImageView: no image");
            Function<Bitmap, Void> saveFunction = new Function<Bitmap, Void>() {
                @Override
                public Void apply(Bitmap image) {
                    imagePool.put(url, image);
                    return null;
                }
            };
            ImageDownloaderAsyncTask imageDownloaderAsyncTask = new ImageDownloaderAsyncTask(imageView, url, saveFunction);
            imageDownloaderAsyncTask.execute();
        } else {
            Log.d(TAG, "setImageFromUrlToImageView: image from url");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
