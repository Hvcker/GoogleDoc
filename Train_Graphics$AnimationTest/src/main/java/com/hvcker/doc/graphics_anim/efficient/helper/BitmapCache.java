package com.hvcker.doc.graphics_anim.efficient.helper;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

/**
 * Created by Hvcker on 2016/1/11 0011.
 * Good good study,day day up!
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class BitmapCache {
    private LruCache<String, Bitmap> mMemoryCache;

    public BitmapCache() {
        //Get max available VM memory,exceeding this amount will thrown an
        //OutOffMemory exception.Stored in kilobytes as LurCache takes an
        //int int its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        //Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

}
