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
public class BitmapWorkerTask extends AsyncTask<Integer,Void,Bitmap>{
    private final WeakReference<ImageView> imageViewReference;
    private int data = 0;
    private final Resources mRes;

    public BitmapWorkerTask(Resources res,ImageView imageView) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<>(imageView);
        this.mRes = res;
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(Integer... params) {
        data = params[0];
        return BitmapUtils.decodeSampleBitmapFromResource(mRes,data,400,400);
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
