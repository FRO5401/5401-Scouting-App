package com.fro.scoutingapp2025;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TemplateThreeBoxes extends LinearLayout {

    TextView leftTextView;
    TextView middleTextView;
    TextView rightTextView;
    ComponentFlipper leftFlipper;
    ComponentFlipper middleFlipper;
    ComponentFlipper rightFlipper;

    public TemplateThreeBoxes(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.template_three_boxes, this);

        CharSequence leftText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_text");
        CharSequence middleText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "middle_text");
        CharSequence rightText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_text");
        CharSequence leftBox =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_box_type");
        CharSequence middleBox =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "middle_box_type");
        CharSequence rightBox = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_box_type");

        initComponents();

        setLeftText(leftText);
        setMiddleText(middleText);
        setRightText(rightText);
        setLeftBox(leftBox);
        setMiddleBox(middleBox);
        setRightBox(rightBox);
    }

    private void initComponents() {
        leftTextView = findViewById(R.id.leftText);
        middleTextView = findViewById(R.id.middleText);
        rightTextView = findViewById(R.id.rightText);
        leftFlipper = findViewById(R.id.leftFlipper);
        middleFlipper = findViewById(R.id.middleFlipper);
        rightFlipper = findViewById(R.id.rightFlipper);
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
}
