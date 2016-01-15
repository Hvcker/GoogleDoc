package com.hvcker.doc.graphics_anim.efficient.helper;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.lang.ref.WeakReference;

/**
 * Created by Hvcker on 2016/1/7 0007.
 * Good good study,day day up!
 */
public class AsyncDrawable extends BitmapDrawable {
    private final WeakReference<BitmapWorkerTask2> bitmapWorkerTaskReference;

    public AsyncDrawable(Resources res, Bitmap bitmap, BitmapWorkerTask2 bitmapWorkerTask) {
        super(res, bitmap);
        this.bitmapWorkerTaskReference = new WeakReference<>(bitmapWorkerTask);
    }

    public BitmapWorkerTask2 getBitmapWorderTask() {
        return bitmapWorkerTaskReference.get();
    }
}
