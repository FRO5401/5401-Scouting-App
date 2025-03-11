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
public class RowTopText extends LinearLayout {
    // Declare variables
    TextView textView;
    ImageView underline;

    public RowTopText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_top_text, this);

        // Get the text and color mode from xml input
        CharSequence text = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text");
        CharSequence colorMode = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "color_mode");

        // Init variables
        textView = findViewById(R.id.top_text);
        underline = findViewById(R.id.top_text_underline);

        // Set text and color mode
        setText(textView, text);
        setColorMode(colorMode);
    }

    public void setText(TextView textview, CharSequence value) { textview.setText(value); }

    public void setColorMode(CharSequence value) {
        if (value == null) {return;}
        if (value.equals("0")){
            textView.setTextColor(getResources().getColor(R.color.white, getContext().getTheme()));
            underline.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white, getContext().getTheme())));
        }
        if (value.equals("1")){
            textView.setTextColor(getResources().getColor(R.color.text_grey, getContext().getTheme()));
            underline.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.text_grey, getContext().getTheme())));
        }
    }
}
