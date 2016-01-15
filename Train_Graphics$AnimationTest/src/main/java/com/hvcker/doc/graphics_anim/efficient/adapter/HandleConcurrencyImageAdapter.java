package com.hvcker.doc.graphics_anim.efficient.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.hvcker.doc.graphics_anim.efficient.utils.BitmapUtils;

/**
 * Created by Hvcker on 2016/1/7 0007.
 * Good good study,day day up!
 */
public class HandleConcurrencyImageAdapter extends BaseImageAdapter {
    private final Bitmap mPlaceHolderBitmap;

    public HandleConcurrencyImageAdapter(Context context, int[] resIds, Bitmap placeHolderBitmap) {
        super(context, resIds);
        this.mPlaceHolderBitmap = placeHolderBitmap;
    }

    @Override
    protected void loadImage(Context context, int resId, ImageView imageView) {
        BitmapUtils.loadBitmap(context.getResources(), resId,
                imageView, mPlaceHolderBitmap);
    }
}
