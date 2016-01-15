package com.hvcker.doc.graphics_anim.efficient.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.hvcker.doc.graphics_anim.efficient.helper.BitmapCache;
import com.hvcker.doc.graphics_anim.efficient.utils.BitmapUtils;

/**
 * Created by Hvcker on 2016/1/11 0011.
 * Good good study,day day up!
 */
public class MemoryImageAdapter extends BaseImageAdapter {
    private BitmapCache mCache;

    public MemoryImageAdapter(Context context, BitmapCache cache, int[] resIds) {
        super(context, resIds);
        this.mCache = cache;
    }

    @Override
    protected void loadImage(Context context, int resId, ImageView imageView) {
        BitmapUtils.loadBitmap(context.getResources(), resId, this.mCache, imageView);
    }
}
