package com.hvcker.doc.graphics_anim.efficient.helper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.hvcker.doc.graphics_anim.efficient.utils.BitmapUtils;

/**
 * Created by Hvcker on 2016/1/12 0012.
 * Good good study,day day up!
 */
public class BitmapWorkerTask5 extends AsyncTask<Integer,Void,Bitmap> {

    private ImageView mImageView;
    private Resources mRes;
    private BitmapCache2 mMemoryCache;

    public BitmapWorkerTask5(Resources res,ImageView imageView, BitmapCache2 memoryCache) {
        this.mImageView = imageView;
        this.mRes = res;
        this.mMemoryCache = memoryCache;
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        //Decode images in background
        final Bitmap bitmap = BitmapUtils.decodeSampleBitmapFromResource(this.mRes,
                params[0], 100, 100);
        mMemoryCache.addBitmapToCache(String.valueOf(params[0]), bitmap);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
    }
}
