package com.mzal;

import android.arch.core.util.Function;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ImageStorage {
    private static final String TAG = ImageStorage.class.getSimpleName();

    public static final String IMAGE_URL = "http://mzalmasherova.ru/resources/assets/images/products/%s/%s";

    private static ConcurrentHashMap<String, Bitmap> imagePool = new ConcurrentHashMap<>();

    public static void setImageFromUrlToImageView(ImageView imageView, String folder, String imageName) {
        final String url = String.format(IMAGE_URL, folder, imageName);
        Bitmap imageBitmap = imagePool.get(url);
        if (imageBitmap == null) {
            Log.d(TAG, "setImageFromUrlToImageView: no image");
            Function<Bitmap, Void> saveFunction = new Function<Bitmap, Void>() {
                @Override
                public Void apply(Bitmap image) {
                    if (image != null) {
                        imagePool.put(url, image);
                    }
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
