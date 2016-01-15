package com.hvcker.doc.graphics_anim.efficient;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.hvcker.doc.R;
import com.hvcker.doc.base.aty.HvckerActivity;
import com.hvcker.doc.utils.L;

/**
 * Created by Hvcker on 2016/1/6 0006.
 * Good good study,day day up!
 */
public class LoadLargeBitmapTestAty extends HvckerActivity {
    private ImageView mShowIv;
    private CheckBox mFlagCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_load_large_bitmap_test);
        mShowIv = (ImageView) super.findViewById(R.id.iv_image_show);
        mFlagCb = (CheckBox) super.findViewById(R.id.cb_inJustDecodeBounds_flag);
        super.findViewById(R.id.btn_image_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShowIv.setImageBitmap(decodeSampleBitmapFromResource(getResources(),
                        R.mipmap.test, mShowIv.getWidth(), mShowIv.getHeight()));
            }
        });
    }

    private Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        //First decode with inJustDecodeBounds = true to check dimensions
        final BitmapFactory.Options options = readBitmapDimensionsAndType(res, resId);
        //Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
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

    private BitmapFactory.Options readBitmapDimensionsAndType(Resources res, int resId) {
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
}
