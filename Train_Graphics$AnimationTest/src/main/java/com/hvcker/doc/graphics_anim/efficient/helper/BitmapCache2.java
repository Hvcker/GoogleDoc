package com.hvcker.doc.graphics_anim.efficient.helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.LruCache;

import com.hvcker.doc.graphics_anim.efficient.utils.SystemUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hvcker on 2016/1/11 0011.
 * Good good study,day day up!
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class BitmapCache2 {
    private LruCache<String, Bitmap> mMemoryCache;
    private Context mContext;
    private DiskLruCache mDiskLruCache;
    private final Object mDiskCacheLock = new Object();
    private boolean mDiskCacheStarting = true;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10;//10MB
    private static final String DISK_CACHE_SUMDIR = "thumbnails";

    public BitmapCache2(Context context) {
        //Get max available VM memory,exceeding this amount will thrown an
        //OutOffMemory exception.Stored in kilobytes as LurCache takes an
        //int int its constructor.
        this.mContext = context;
        initMemoryCache();
        initDiskCache();


    }

    private void initMemoryCache() {
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

    private void initDiskCache() {
        //Initialize disk cache on background thread
        File cacheDir = getDiskCacheDir(this.mContext, DISK_CACHE_SUMDIR);
        new InitDiskCacheTask().execute(cacheDir);
    }

    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    private class InitDiskCacheTask extends AsyncTask<File, Void, Void> {
        @Override
        protected Void doInBackground(File... params) {
            synchronized (mDiskCacheLock) {
                File cacheDir = params[0];
                try {
                    mDiskLruCache = DiskLruCache.open(cacheDir, SystemUtils.getAppVersion(mContext),
                            1, DISK_CACHE_SIZE);
                    mDiskCacheStarting = false;//Finished initialization
                    mDiskCacheLock.notifyAll();//Wake any waiting threads
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    //Creates a unique subdirectory of the designated app cache directory.Tries to use external
    //but if not mounted,falls back on internal storage
    private File getDiskCacheDir(Context context, String uniqueName) {
        //Check if media is mounted of storage is built-in,if so, try and use external cache dir
        //otherwise use internal cache dir
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                !Environment.isExternalStorageRemovable() ? context.getExternalCacheDir().getPath() :
                context.getCacheDir().getPath();
        return new File(cachePath + File.separator + uniqueName);
    }

    public void addBitmapToCache(String key, Bitmap bitmap) {
        //Add to memory cache as before
        addBitmapToMemoryCache(key, bitmap);
        //Also add to disk cache
        addBitmapToDiskCache(key, bitmap);
    }

    public Bitmap getBitmapFromCache(String key) {
        Bitmap bitmap = getBitmapFromMemCache(key);
        if (bitmap == null) {
            bitmap = getBitmapFromDiskCache(key);
        }
        return bitmap;
    }

    private void addBitmapToDiskCache(String key, Bitmap bitmap) {
        synchronized (mDiskCacheLock) {
            try {
                if (mDiskLruCache != null && mDiskLruCache.get(key) == null) {
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        InputStream is = editor.newInputStream(0);
                        if (is != null) {
                            ByteArrayOutputStream os = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                            is = new ByteArrayInputStream(os.toByteArray());
                            editor.commit();
                            is.close();
                            os.close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getBitmapFromDiskCache(String key) {
        //Wait while disk cache is started from background thread
        while (mDiskCacheStarting) {
            try {
                mDiskCacheLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (mDiskLruCache != null) {
            InputStream is = null;
            try {
                is = mDiskLruCache.get(key).getInputStream(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (is != null) {
                return BitmapFactory.decodeStream(is);
            }
        }

        return null;
    }
}
