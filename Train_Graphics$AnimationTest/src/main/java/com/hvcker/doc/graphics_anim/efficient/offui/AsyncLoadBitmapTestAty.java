package com.hvcker.doc.graphics_anim.efficient.offui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hvcker.doc.R;
import com.hvcker.doc.base.aty.HvckerActivity;
import com.hvcker.doc.graphics_anim.efficient.helper.BitmapWorkerTask;

/**
 * Created by Hvcker on 2016/1/7 0007.
 * Good good study,day day up!
 */
public class AsyncLoadBitmapTestAty extends HvckerActivity {
    private ImageView mShowIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.aty_async_load_test);
        this.mShowIv = (ImageView) findViewById(R.id.iv_image_show);
        super.findViewById(R.id.btn_image_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAsyncImage(R.mipmap.test);
            }
        });
    }

    private void loadAsyncImage(int resId) {
        new BitmapWorkerTask(getResources(), this.mShowIv).execute(resId);
    }


}
