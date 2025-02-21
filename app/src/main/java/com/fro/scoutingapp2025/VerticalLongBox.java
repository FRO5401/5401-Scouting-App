package com.fro.scoutingapp2025;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VerticalLongBox extends LinearLayout{

    TextView text1View;
    TextView text2View;
    TextView text3View;
    TextView text4View;
    TextView text5View;
    ComponentFlipper box1Flipper;
    ComponentFlipper box2Flipper;
    ComponentFlipper box3Flipper;
    ComponentFlipper box4Flipper;
    ComponentFlipper box5Flipper;
    public VerticalLongBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.vertical_long_box, this);

        CharSequence text1 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_1");
        CharSequence text2 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_2");
        CharSequence text3 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_3");
        CharSequence text4 =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_4");
        CharSequence text5 =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text_5");

        CharSequence box1 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_1_type");
        CharSequence box2 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_2_type");
        CharSequence box3 = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_3_type");
        CharSequence box4 =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_4_type");
        CharSequence box5 =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "box_5_type");

        initComponents();

        setText1(text1);
        setText2(text2);
        setText3(text3);
        setText4(text4);
        setText5(text5);
        setBox1(box1);
        setBox2(box2);
        setBox3(box3);
        setBox4(box4);
        setBox5(box5);

    }

    private void initComponents() {
        text1View = findViewById(R.id.text1);
        text2View = findViewById(R.id.text2);
        text3View = findViewById(R.id.text3);
        text4View = findViewById(R.id.text4);
        text5View = findViewById(R.id.text5);
        box1Flipper = findViewById(R.id.box1);
        box2Flipper = findViewById(R.id.box2);
        box3Flipper = findViewById(R.id.box3);
        box4Flipper = findViewById(R.id.box4);
        box5Flipper = findViewById(R.id.box5);
    }

    public void setText1(CharSequence value) {
        text1View.setText(value);
    }
    public void setText2(CharSequence value) {
        text2View.setText(value);
    }
    public void setText3(CharSequence value) {
        text3View.setText(value);
    }
    public void setText4(CharSequence value) {
        text4View.setText(value);
    }
    public void setText5(CharSequence value) {
        text5View.setText(value);
    }

    public void setBox1(CharSequence value) {
        if (value == null) {return;}
        box1Flipper.changeTo(value);
    }
    public void setBox2(CharSequence value) {
        if (value == null) {return;}
        box2Flipper.changeTo(value);
    }
    public void setBox3(CharSequence value) {
        if (value == null) {return;}
        box3Flipper.changeTo(value);
    }
    public void setBox4(CharSequence value) {
        if (value == null) {return;}
        box4Flipper.changeTo(value);
    }
    public void setBox5(CharSequence value) {
        if (value == null) {return;}
        box5Flipper.changeTo(value);
    }
}