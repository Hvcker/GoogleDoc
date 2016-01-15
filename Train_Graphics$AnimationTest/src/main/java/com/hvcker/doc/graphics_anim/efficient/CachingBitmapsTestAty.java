package com.hvcker.doc.graphics_anim.efficient;

import com.hvcker.doc.base.aty.ButtonListActivity;
import com.hvcker.doc.base.aty.HvckerActivity;
import com.hvcker.doc.graphics_anim.efficient.cache.DiskCacheTestAty;
import com.hvcker.doc.graphics_anim.efficient.cache.HandleConfigurationChangeTestAty;
import com.hvcker.doc.graphics_anim.efficient.cache.MemoryCacheTestAty;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Hvcker on 2016/1/11 0011.
 * Good good study,day day up!
 */
public class CachingBitmapsTestAty extends ButtonListActivity {
    @Override
    public Map<String, Class<? extends HvckerActivity>> getAtyMappings() {
        Map<String, Class<? extends HvckerActivity>> atymappings = new LinkedHashMap<>();
        atymappings.put("Use a Memory Cache", MemoryCacheTestAty.class);

        //TODO 有待完善！！！
        atymappings.put("Use a Disk Cache", DiskCacheTestAty.class);

        atymappings.put("Handle Configuration Changes", HandleConfigurationChangeTestAty.class);
        return atymappings;
    }
}
