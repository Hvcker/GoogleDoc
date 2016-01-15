package com.hvcker.doc.graphics_anim.efficient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Hvcker on 2016/1/7 0007.
 * Good good study,day day up!
 */
public abstract class BaseImageAdapter extends BaseAdapter {
    private final Context mContext;
    private final int[] mResIds;

    public BaseImageAdapter(Context context, int[] resIds) {
        this.mContext = context;
        this.mResIds = resIds;
    }

    @Override
    public int getCount() {
        return this.mResIds == null ? 0 : mResIds.length;
    }

    @Override
    public Object getItem(int position) {
        return this.mResIds[position];
    }

    @Override
    public long getItemId(int position) {
        return this.mResIds[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new ImageView(this.mContext);
            convertView.setLayoutParams(new AbsListView.LayoutParams(400, 400));
        }
        if (convertView instanceof ImageView) {
            loadImage(this.mContext, (int) getItem(position), (ImageView) convertView);
        }
        return convertView;
    }

    protected abstract void loadImage(Context context, int resId, ImageView imageView);
}
