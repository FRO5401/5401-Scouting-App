package com.fro.scoutingapp2025;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Creates a row with the top header text. <br/>
 * Usually, the layout weight of this row is 0.5 out of a weight sum of 5
 *
 * @requires app:text=""
 * <br> The text that will be displayed in the top header
 * @requires app:color_mode=""
 * <ul>
 *     <li> light - White text and underline</li>
 *     <li> dark - Grey text and underline</li>
 * </ul>
 */
public class RowTopImage extends LinearLayout {

    ImageView image;
    ImageView underline;

    public RowTopImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_top_image, this);

        CharSequence colorMode = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "color_mode");

        initComponents();

        setColorMode(colorMode);
    }

    private void initComponents() {
        image = findViewById(R.id.topImage);
        underline = findViewById(R.id.topUnderline);
    }
    public void setColorMode(CharSequence value) {
        if (value == null) {return;}
        if (value.equals("0")){
            //maybe change color of image?
            underline.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white, getContext().getTheme())));
        }
        if (value.equals("1")){
            //maybe change color of image?
            underline.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.text_grey, getContext().getTheme())));
        }
    }
}
