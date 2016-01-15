package com.hvcker.doc.graphics_anim.efficient;

import com.hvcker.doc.base.aty.ButtonListActivity;
import com.hvcker.doc.base.aty.HvckerActivity;
import com.hvcker.doc.graphics_anim.efficient.offui.AsyncLoadBitmapTestAty;
import com.hvcker.doc.graphics_anim.efficient.offui.HandleConcurrencyTestAty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Hvcker on 2016/1/6 0006.
 * Good good study,day day up!
 */
public class BitmapOffTheUITestAty extends ButtonListActivity {

    @Override
    public Map<String, Class<? extends HvckerActivity>> getAtyMappings() {
        Map<String,Class<? extends HvckerActivity>> atyMappings = new LinkedHashMap<>();
        atyMappings.put("Use an AsyncTask", AsyncLoadBitmapTestAty.class);
        atyMappings.put("Handle Concurrency", HandleConcurrencyTestAty.class);
        return atyMappings;
    }
}
