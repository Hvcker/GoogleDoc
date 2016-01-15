package com.hvcker.doc.graphics_anim;

import com.hvcker.doc.base.aty.ButtonListActivity;
import com.hvcker.doc.base.aty.HvckerActivity;
import com.hvcker.doc.graphics_anim.efficient.BitmapOffTheUITestAty;
import com.hvcker.doc.graphics_anim.efficient.CachingBitmapsTestAty;
import com.hvcker.doc.graphics_anim.efficient.LoadLargeBitmapTestAty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Hvcker on 2016/1/6 0006.
 * Good good study,day day up!
 */
public class DisplayBitmapEfficientlyTestAty extends ButtonListActivity {
    @Override
    public Map<String, Class<? extends HvckerActivity>> getAtyMappings() {
        Map<String, Class<? extends HvckerActivity>> mappings = new LinkedHashMap<>();
        mappings.put("Loading Large Bitmaps Efficiently", LoadLargeBitmapTestAty.class);
        mappings.put("Processing Bitmaps Off the UI Thread", BitmapOffTheUITestAty.class);
        mappings.put("Caching Bitmaps", CachingBitmapsTestAty.class);
        return mappings;
    }
}
