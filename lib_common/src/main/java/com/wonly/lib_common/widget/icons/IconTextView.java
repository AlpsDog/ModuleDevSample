package com.wonly.lib_common.widget.icons;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.wonly.lib_base.utils.PixelUtils;
import com.wonly.lib_common.R;

public class IconTextView extends AppCompatTextView {

    private String iconUcode = "";

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.IconTextView);
        iconUcode = a.getString(R.styleable.IconTextView_itv_ucode);
        a.recycle();
        int[] androidAttrs = new int[]{
                android.R.attr.textSize
        };
        TypedArray a1 = getContext().obtainStyledAttributes(attrs, androidAttrs);
        float textSize = a1.getDimension(0, 0);
        if (textSize > 0) {
            textSize = PixelUtils.px2sp(getContext(), textSize) * 5 / 4;
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }
        a1.recycle();
        this.setText(iconUcode);
        this.setTypeface(IconLibs.getIconfont(getContext()));
    }

    public void setIconUcode(String iconUcode) {
        if (TextUtils.isEmpty(iconUcode)) {
            return;
        }
        if (iconUcode.startsWith("&#x")) {
            iconUcode = iconUcode.replace("&#x", "\\u");
        }
        if (iconUcode.endsWith(";")) {
            iconUcode = iconUcode.substring(0, iconUcode.length() - 1);
        }
        this.setText(decode(iconUcode));
        this.setTypeface(IconLibs.getIconfont(getContext()));
    }

    public String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5) && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }
}
