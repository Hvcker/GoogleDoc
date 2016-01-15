package com.hvcker.doc.graphics_anim.efficient.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.hvcker.doc.R;
import com.hvcker.doc.graphics_anim.efficient.helper.AsyncDrawable;
import com.hvcker.doc.graphics_anim.efficient.helper.BitmapCache;
import com.hvcker.doc.graphics_anim.efficient.helper.BitmapCache2;
import com.hvcker.doc.graphics_anim.efficient.helper.BitmapWorkerTask2;
import com.hvcker.doc.graphics_anim.efficient.helper.BitmapWorkerTask3;
import com.hvcker.doc.graphics_anim.efficient.helper.BitmapWorkerTask5;
import com.hvcker.doc.utils.L;

/**
 * Created by Hvcker on 2016/1/7 0007.
 * Good good study,day day up!
 */
public class BitmapUtils {
    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        //First decode with inJustDecodeBounds = true to check dimensions
        final BitmapFactory.Options options = readBitmapDimensionsAndType(res, resId);
        //Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height >> 1;
            final int halfWidth = width >> 1;

            //Calculate the largest inSampleSize value that is a power of 2 and keeps both
            //height and width larger than the  requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize <<= 1;
            }
        }
        L.i("inSampleSize = " + inSampleSize);
        return inSampleSize;
    }

    private static BitmapFactory.Options readBitmapDimensionsAndType(Resources res, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        //Setting the inJustDecodeBounds property to true while decoding
        //avoids memory allocation,returning null for Bitmap Object but
        //setting outWidth,outHeight and outMineType.
        //options.inJustDecodeBounds = mFlagCb.isChecked();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
        L.i("Height = " + imageHeight + "\tWidth = " + imageWidth + "\tType = " + imageType);
        return options;
    }

    public static void loadBitmap(Resources res, int resId, ImageView imageView, Bitmap placeHolderBitmap) {
        if (cancelPotentialWork(resId, imageView)) {
            final BitmapWorkerTask2 task = new BitmapWorkerTask2(res, imageView);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(res, placeHolderBitmap, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(resId);
        }
    }

    public static void loadBitmap(Resources res, int resId, BitmapCache memoryCache, ImageView imageView) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = memoryCache.getBitmapFromMemCache(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.mipmap.avatar);
            BitmapWorkerTask3 task = new BitmapWorkerTask3(res, imageView, memoryCache);
            task.execute(resId);
        }
    }

    public static void loadBitmap(Resources res, int resId, BitmapCache2 memoryCache, ImageView imageView) {
        final String imageKey = String.valueOf(resId);

        final Bitmap bitmap = memoryCache.getBitmapFromCache(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.mipmap.avatar);
            BitmapWorkerTask5 task = new BitmapWorkerTask5(res, imageView, memoryCache);
            task.execute(resId);
        }
    }

    private static boolean cancelPotentialWork(int data, ImageView imageView) {
        final BitmapWorkerTask2 bitmapWorkerTask = getBtimapWorkerTask(imageView);
        if (bitmapWorkerTask != null) {
            final int bitmapData = bitmapWorkerTask.data;
            //if bitmapData is not yet set or it differs from the new data
            if (bitmapData == 0 || bitmapData != data) {
                //Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                //The same work is already in progress
                return false;
            }
        }
        //No task associated with the ImageView,or an existing task was cancelled
        return true;
    }

    public static BitmapWorkerTask2 getBtimapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorderTask();
            }
        }
        return null;
    }
}
