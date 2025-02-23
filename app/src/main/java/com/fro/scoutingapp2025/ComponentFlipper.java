package com.fro.scoutingapp2025;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class ComponentFlipper extends LinearLayout {

    // Instantiates the view flipper
    ViewFlipper flipper;
    // Text type box
    EditText textTypeBox;
    // Text Dropdown
    Spinner textDropdown;

    public ComponentFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.component_flipper, this);
        flipper = (ViewFlipper) findViewById(R.id.flipper);
    }

    public void changeTo(CharSequence value) {
        if (value.equals("0")) {while (flipper.getCurrentView() != findViewById(R.id.textTypeBox)) {flipper.showNext();}}
        if (value.equals("1")) {while (flipper.getCurrentView() != findViewById(R.id.numberTypeBox)) {flipper.showNext();}}
        if (value.equals("2")) {while (flipper.getCurrentView() != findViewById(R.id.textDropdown)) {flipper.showNext();}}
        if (value.equals("3")) {while (flipper.getCurrentView() != findViewById(R.id.numberDropdown)) {flipper.showNext();}}
        if (value.equals("4")) {while (flipper.getCurrentView() != findViewById(R.id.toggle)) {flipper.showNext();}}
        if (value.equals("5")) {while (flipper.getCurrentView() != findViewById(R.id.counter)) {flipper.showNext();}}
        if (value.equals("6")) {while (flipper.getCurrentView() != findViewById(R.id.stopwatch)) {flipper.showNext();}}
    }

    public boolean getView(String type) {
        if (type.equals("textTypeBox") && flipper.getCurrentView() != findViewById(R.id.textTypeBox)) {return true;}
        if (type.equals("numberTypeBox") && flipper.getCurrentView() == findViewById(R.id.numberTypeBox)) {return true;}
        if (type.equals("textDropdown") && flipper.getCurrentView() != findViewById(R.id.textDropdown)) {return true;}
        if (type.equals("numberDropdown") && flipper.getCurrentView() != findViewById(R.id.numberDropdown)) {return true;}
        if (type.equals("toggle") && flipper.getCurrentView() != findViewById(R.id.toggle)) {return true;}
        if (type.equals("counter") && flipper.getCurrentView() != findViewById(R.id.counter)) {return true;}
        return type.equals("stopwatch") && flipper.getCurrentView() != findViewById(R.id.stopwatch);
    }

    public void setPadding(int horizontalChange, int verticalChange){
        //Sets the padding to change per screen size
        Context con = this.getContext();
        flipper.post(new Runnable() {
            @Override public void run() {
                if(horizontalChange != 0 && verticalChange != 0){
                    int height = flipper.getHeight();
                    flipper.setPadding(
                            (height / horizontalChange), // left
                            (height / verticalChange), // top
                            (height / horizontalChange),  // right
                            (height / verticalChange)   // bottom
                    );
                }
                else{
                    Toast.makeText(con, "Error 0: " + String.valueOf(horizontalChange)+" | "+String.valueOf(verticalChange), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void createTextTypeBox(String name) {
        //Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {
            Values.data.put(name, "");
        }

        //Creates the type box
        textTypeBox = findViewById(R.id.text_type_box);

        // Set inputted text
        if (Values.data.containsKey(name) && (CharSequence) Values.data.get(name) != null) {
            textTypeBox.setText((CharSequence) Values.data.get(name));
        }

        // Updates data when text is changed
        textTypeBox.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {Values.data.put(name, textTypeBox.getText());}
        });
    }

    public void createTextDropdown(ArrayList<String> array, String name) {
        //Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {
            Values.data.put(name, -1);
        }

        // Adds 'dropdown' to the beginning of the list to be a default value
        array.add(0, "Dropdown");

        // Creates the dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        textDropdown = findViewById(R.id.text_dropdown);
        textDropdown.setAdapter(adapter);

        // Sets the dropdown to be below the spinner border
        textDropdown.post(new Runnable() {
            @Override public void run() {
                textDropdown.setDropDownVerticalOffset(5);}
        });

        // Set text dropdown selection
        if (Values.data.containsKey(name) && (Integer) Values.data.get(name) != null) {
            textDropdown.setSelection((Integer) Values.data.get(name));
        }

        // Updates data when new item is selected in dropdown
        textDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {Values.data.put(name, pos);}
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

}