package com.fro.scoutingapp2025;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Creates a row with one large box. <br/>
 * Usually, the layout weight of this row is 2 out of a weight sum of 5
 *
 * @requires app:text=""
 * <br> The text that will be displayed in the box header
 *
 * @requires app:box_type=""
 * <br> The type of component that will be in the box
 * <ul>
 *     <li>text_type_box - Typed input</li>
 *     <li>number_type_box - Typed input with numbers only</li>
 *     <li>text_dropdown - A dropdown list</li>
 *     <li>team_number_dropdown - A searchable dropdown of team numbers</li>
 *     <li>toggle - An on/off switch</li>
 *     <li>counter - A plus/minus counter starting at 0</li>
 *     <li>stopwatch - A stopwatch with on, off, and reset</li>
 * </ul>
 */
public class RowLargeBox extends LinearLayout {

    TextView textView;
    ComponentFlipper flipper;

    public RowLargeBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.row_large_box, this);

        CharSequence text = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text");
        CharSequence box =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_type");

        initComponents();

        setText(text);
        setBox(box);
    }

    private void initComponents() {
        textView = findViewById(R.id.oneText);
        flipper = findViewById(R.id.oneFlipper);
    }

    public void setText(CharSequence value) {
        textView.setText(value);
    }

    public void setBox(CharSequence value) {
        if (value == null) {return;}
        flipper.changeTo(value);
    }
}
