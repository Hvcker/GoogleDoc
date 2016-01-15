package com.hvcker.doc.graphics_anim.efficient.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.hvcker.doc.graphics_anim.efficient.helper.BitmapCache2;
import com.hvcker.doc.graphics_anim.efficient.utils.BitmapUtils;

/**
 * Created by Hvcker on 2016/1/11 0011.
 * Good good study,day day up!
 */
public class MemoryImageAdapter2 extends BaseImageAdapter {
    private BitmapCache2 mCache;

    public MemoryImageAdapter2(Context context, BitmapCache2 cache, int[] resIds) {
        super(context, resIds);
        this.mCache = cache;
    }

    @Override
    protected void loadImage(Context context, int resId, ImageView imageView) {
        BitmapUtils.loadBitmap(context.getResources(), resId, this.mCache, imageView);
    }
}
