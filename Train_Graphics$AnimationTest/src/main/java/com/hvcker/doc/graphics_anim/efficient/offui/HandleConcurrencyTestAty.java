package com.hvcker.doc.graphics_anim.efficient.offui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import com.hvcker.doc.R;
import com.hvcker.doc.base.aty.HvckerActivity;
import com.hvcker.doc.graphics_anim.efficient.adapter.HandleConcurrencyImageAdapter;

/**
 * Created by Hvcker on 2016/1/7 0007.
 * Good good study,day day up!
 */
public class HandleConcurrencyTestAty extends HvckerActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.aty_handle_concurrency_test);
        mListView = (ListView) super.findViewById(R.id.lv_show);
        final int[] resIds = new int[]{R.mipmap.test,
                R.mipmap.test2,
                R.mipmap.test3,
                R.mipmap.test4,
                R.mipmap.test5,
                R.mipmap.test6,
                R.mipmap.test7,
                R.mipmap.test8,
        };
        final Bitmap placeHolderBitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.avatar);
        mListView.setAdapter(new HandleConcurrencyImageAdapter(this, resIds, placeHolderBitmap));
    }

}
