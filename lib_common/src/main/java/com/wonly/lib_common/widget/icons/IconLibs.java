package com.wonly.lib_common.widget.icons;

import android.content.Context;
import android.graphics.Typeface;

/**
 * @Author: HouShengLi
 * @Time: 2018/7/9 18:03
 * @E-mail: 13967189624@163.com
 * @Description:
 */
public class IconLibs {

    private static Typeface iconfont = null;
    private static Typeface traditionalFont = null;

    public static Typeface getIconfont(Context context) {
        if (iconfont == null) {
            iconfont = Typeface.createFromAsset(context.getAssets(), "fonts/iconfont.ttf");
        }
        return iconfont;
    }

//    public static Typeface getTraditionalfont(Context context) {
//        if (traditionalFont == null) {
//            traditionalFont = Typeface.createFromAsset(context.getAssets(), "fonts/AGENCYB.ttf");
//        }
//        return traditionalFont;
//    }
}
