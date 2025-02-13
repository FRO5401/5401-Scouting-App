package com.fro.scoutingapp2025;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TemplateOneLargeBox extends LinearLayout {

    TextView textView;
    ComponentFlipper flipper;

    public TemplateOneLargeBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.template_one_large_box, this);

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
