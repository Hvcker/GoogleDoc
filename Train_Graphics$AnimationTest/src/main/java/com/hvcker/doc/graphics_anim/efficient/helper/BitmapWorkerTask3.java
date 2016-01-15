package com.hvcker.doc.graphics_anim.efficient.helper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.hvcker.doc.graphics_anim.efficient.utils.BitmapUtils;

/**
 * Created by Hvcker on 2016/1/11 0011.
 * Good good study,day day up!
 */
public class BitmapWorkerTask3 extends AsyncTask<Integer, Void, Bitmap> {
    private ImageView mImageView;
    private Resources mRes;
    private BitmapCache mMemoryCache;

    public BitmapWorkerTask3(Resources res,ImageView imageView, BitmapCache memoryCache) {
        this.mImageView = imageView;
        this.mRes = res;
        this.mMemoryCache = memoryCache;
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        //Decode images in background
        final Bitmap bitmap = BitmapUtils.decodeSampleBitmapFromResource(this.mRes,
                params[0], 100, 100);
        mMemoryCache.addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }
}
