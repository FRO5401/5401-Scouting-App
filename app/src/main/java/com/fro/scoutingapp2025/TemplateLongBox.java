package com.fro.scoutingapp2025;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TemplateLongBox extends LinearLayout {

    TextView leftTextView;
    TextView middleTextView;
    TextView rightTextView;
    ComponentFlipper leftFlipper;
    ComponentFlipper middleFlipper;
    ComponentFlipper rightFlipper;

    //Elements to change the box color
    View middleView1;
    View middleView2;
    LinearLayout middleLayout;
    LinearLayout backgroundLayout;

    public TemplateLongBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.template_long_box, this);

        CharSequence leftText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_text");
        CharSequence middleText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "middle_text");
        CharSequence rightText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_text");
        CharSequence leftBox =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_box_type");
        CharSequence middleBox =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "middle_box_type");
        CharSequence rightBox = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_box_type");
        CharSequence colorPattern = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "color_pattern");

        initComponents();

        Toast.makeText(context, String.valueOf(colorPattern)+" |", Toast.LENGTH_SHORT).show();

        setLeftText(leftText);
        setMiddleText(middleText);
        setRightText(rightText);
        setLeftBox(leftBox);
        setMiddleBox(middleBox);
        setRightBox(rightBox);
        setColorPattern(colorPattern);
    }

    private void initComponents() {
        leftTextView = findViewById(R.id.leftText);
        middleTextView = findViewById(R.id.middleText);
        rightTextView = findViewById(R.id.rightText);
        leftFlipper = findViewById(R.id.leftFlipper);
        middleFlipper = findViewById(R.id.middleFlipper);
        rightFlipper = findViewById(R.id.rightFlipper);
        middleView1 = findViewById(R.id.middleView1);
        middleView2 = findViewById(R.id.middleView2);
        middleLayout = findViewById(R.id.middleLayout);
        backgroundLayout = findViewById(R.id.backgroundLayout);
    }

    public void setLeftText(CharSequence value) {
        leftTextView.setText(value);
    }

    public void setMiddleText(CharSequence value) {
        middleTextView.setText(value);
    }

    public void setRightText(CharSequence value) {
        rightTextView.setText(value);
    }

    public void setLeftBox(CharSequence value) {
        if (value == null) {return;}
        leftFlipper.changeTo(value);
    }
    public void setMiddleBox(CharSequence value) {
        if (value == null) {return;}
        middleFlipper.changeTo(value);
    }
    public void setRightBox(CharSequence value) {
        if (value == null) {return;}
        rightFlipper.changeTo(value);
    }
    public void setColorPattern(CharSequence value) {
        if (value == null) {return;}
        if (value.equals("0")){
            backgroundLayout.setBackgroundResource(R.drawable.box_background);
            middleView1.setBackgroundResource(R.color.light_grey);
            middleView2.setBackgroundResource(R.color.light_grey);
            middleLayout.setBackgroundResource(R.color.light_grey);
        }
        if (value.equals("1")){
            backgroundLayout.setBackgroundResource(R.drawable.box_background_grey);
            middleView1.setBackgroundResource(R.color.white);
            middleView2.setBackgroundResource(R.color.white);
            middleLayout.setBackgroundResource(R.color.white);
        }
    }
}