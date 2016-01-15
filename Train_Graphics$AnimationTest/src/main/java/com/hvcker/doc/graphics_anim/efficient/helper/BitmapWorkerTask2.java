package com.hvcker.doc.graphics_anim.efficient.helper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.hvcker.doc.graphics_anim.efficient.utils.BitmapUtils;

import java.lang.ref.WeakReference;

/**
 * Created by Hvcker on 2016/1/7 0007.
 * Good good study,day day up!
 */
public class BitmapWorkerTask2 extends AsyncTask<Integer, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    private final Resources mRes;
    public int data = 0;

    public BitmapWorkerTask2(Resources res, ImageView imageView) {
        //Use a WeakReference to ensure the ImageView can be garbage collected
        this.imageViewReference = new WeakReference<>(imageView);
        this.mRes = res;
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        this.data = params[0];
        return BitmapUtils.decodeSampleBitmapFromResource(this.mRes, this.data, 400, 400);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }
        if (this.imageViewReference != null && bitmap != null) {
            final ImageView imageView = this.imageViewReference.get();
            final BitmapWorkerTask2 bitmapWorkerTask = BitmapUtils.getBtimapWorkerTask(imageView);
            if (this == bitmapWorkerTask && imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}