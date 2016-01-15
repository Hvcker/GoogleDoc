package com.hvcker.doc;

import com.hvcker.doc.base.aty.ButtonListActivity;
import com.hvcker.doc.base.aty.HvckerActivity;
import com.hvcker.doc.graphics_anim.DisplayBitmapEfficientlyTestAty;
import com.hvcker.doc.graphics_anim.DisplayGraphicsByOpenGLTestAty;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends ButtonListActivity {

    @Override
    public Map<String, Class<? extends HvckerActivity>> getAtyMappings() {
        Map<String, Class<? extends HvckerActivity>> maps = new LinkedHashMap<>();
        maps.put("Displaying Bitmaps Efficiently", DisplayBitmapEfficientlyTestAty.class);
        maps.put("Displaying Graphics with OpenGL ES", DisplayGraphicsByOpenGLTestAty.class);
        return maps;
    }
}
