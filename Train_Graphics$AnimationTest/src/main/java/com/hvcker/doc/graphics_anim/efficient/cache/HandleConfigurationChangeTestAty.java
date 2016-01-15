package com.hvcker.doc.graphics_anim.efficient.cache;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.widget.ListView;

import com.hvcker.doc.R;
import com.hvcker.doc.base.aty.HvckerActivity;
import com.hvcker.doc.graphics_anim.efficient.adapter.MemoryImageAdapter;
import com.hvcker.doc.graphics_anim.efficient.helper.BitmapCache;

/**
 * Created by Hvcker on 2016/1/11 0011.
 * Good good study,day day up!
 */
public class HandleConfigurationChangeTestAty extends HvckerActivity {

    private ListView mListView;

    private BitmapCache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCache();
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
        mListView.setAdapter(new MemoryImageAdapter(this, this.mCache, resIds));
    }

    private void initCache() {
        RetainFragment retainFragment = RetainFragment.findOrCreateRetainFragment(getFragmentManager());
        mCache = retainFragment.mRetainedCache;
        if (mCache == null) {
            mCache = new BitmapCache();
            retainFragment.mRetainedCache = mCache;
        }
    }

    static class RetainFragment extends Fragment {
        private static final String TAG = "RetainedCache";
        public BitmapCache mRetainedCache;

        public RetainFragment() {
        }

        ;

        public static RetainFragment findOrCreateRetainFragment(FragmentManager fm) {
            RetainFragment fragment = (RetainFragment) fm.findFragmentByTag(TAG);
            if (fragment == null) {
                fragment = new RetainFragment();
                fm.beginTransaction().add(fragment, TAG).commit();
            }
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }
    }
}
