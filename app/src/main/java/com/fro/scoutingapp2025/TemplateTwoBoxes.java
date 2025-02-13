package com.fro.scoutingapp2025;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.StyleableRes;

public class TemplateTwoBoxes extends LinearLayout {

    TextView leftTextView;
    TextView rightTextView;
    ComponentFlipper leftFlipper;
    ComponentFlipper rightFlipper;

    public TemplateTwoBoxes(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.template_two_boxes, this);

        CharSequence leftText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_text");
        CharSequence rightText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_text");
        CharSequence leftBox =attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "left_box_type");
        CharSequence rightBox = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "right_box_type");

        initComponents();

        setLeftText(leftText);
        setRightText(rightText);
        setLeftBox(leftBox);
        setRightBox(rightBox);
    }

    private void initComponents() {
        leftTextView = findViewById(R.id.leftText);
        rightTextView = findViewById(R.id.rightText);
        leftFlipper = findViewById(R.id.leftFlipper);
        rightFlipper = findViewById(R.id.rightFlipper);
    }

    public void setLeftText(CharSequence value) {
        leftTextView.setText(value);
    }

    public void setRightText(CharSequence value) {
        rightTextView.setText(value);
    }

    public void setLeftBox(CharSequence value) {
        if (value == null) {return;}
        leftFlipper.changeTo(value);
    }
    public void setRightBox(CharSequence value) {
        if (value == null) {return;}
        rightFlipper.changeTo(value);
    }
}
