package com.fro.scoutingapptest;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class ComponentFlipper extends LinearLayout {

    // Instantiates the view flipper
    ViewFlipper flipper;
    // Text type box
    EditText typeBox;
    // Text Dropdown
    Spinner textDropdown;
    // Team Number Dropdown
    AutoCompleteTextView numberDropdown;
    // Toggle
    SwitchCompat toggle;
    // Counter
    ImageButton counterPlus;
    ImageButton counterMinus;
    TextView counterNumber;
    // Multi Counter
    TextView multiCounterCount;
    ImageButton multiCounterPlus1;
    ImageButton multiCounterMinus1;
    ImageButton multiCounterPlus2;
    ImageButton multiCounterMinus2;
    ImageButton multiCounterPlus3;
    ImageButton multiCounterMinus3;
    TextView multiCounterIncrement1;
    TextView multiCounterIncrement2;
    TextView multiCounterIncrement3;
    LinearLayout multiCounterLayout;
    LinearLayout multiCounterLayout1;
    LinearLayout multiCounterLayout2;
    LinearLayout multiCounterLayout3;
    // Stopwatch
    TextView stopwatchTimer;
    TextView stopwatchRestart;
    TextView stopwatchStart;
    TextView stopwatchStop;
    String stopwatchName;
    int stopwatchSeconds, stopwatchMinutes, stopwatchElapsedTimeMillis = 0, stopwatchStartTimeMillis = 0, stopwatchSavedTimeMillis = 0;
    Looper stopwatchLooper = Looper.getMainLooper();
    Handler stopwatchHandler = new Handler(stopwatchLooper);

    private final Runnable stopwatchRunnable = new Runnable() {
        @Override
        public void run() {
            // Difference of what system time time we started the stopwatch and the current system time
            stopwatchElapsedTimeMillis = (int) SystemClock.uptimeMillis() - stopwatchStartTimeMillis;
            // Gets the previous time and adds it to the elapsed time (/1000 to turn into seconds)
            stopwatchSeconds = ((stopwatchSavedTimeMillis + stopwatchElapsedTimeMillis)/1000);
            // Saves seconds in data
            Values.data.put(stopwatchName, stopwatchSeconds);
            // Gets minutes and seconds to print
            stopwatchMinutes = stopwatchSeconds / 60;
            stopwatchSeconds = stopwatchSeconds % 60;
            // Sets the text view to the time in 00:00 format
            String text = stopwatchMinutes + ":" + String.format(Locale.getDefault(), "%02d", stopwatchSeconds);
            stopwatchTimer.setText(text);
            // Uses recursion to run this function forever with no delay
            stopwatchHandler.postDelayed(this, 0);
        }
    };

    public ComponentFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.component_flipper, this);
        flipper = findViewById(R.id.flipper);

    }

    public void changeTo(CharSequence value) {
        if (value.equals("0")) {while (flipper.getCurrentView() != findViewById(R.id.type_box_layout)) {flipper.showNext();}}
        if (value.equals("1")) {while (flipper.getCurrentView() != findViewById(R.id.text_dropdown_layout)) {flipper.showNext();}}
        if (value.equals("2")) {while (flipper.getCurrentView() != findViewById(R.id.number_dropdown_layout)) {flipper.showNext();}}
        if (value.equals("3")) {while (flipper.getCurrentView() != findViewById(R.id.toggle_layout)) {flipper.showNext();}}
        if (value.equals("4")) {while (flipper.getCurrentView() != findViewById(R.id.counter_layout)) {flipper.showNext();}}
        if (value.equals("5")) {while (flipper.getCurrentView() != findViewById(R.id.multi_counter_layout)) {flipper.showNext();}}
        if (value.equals("6")) {while (flipper.getCurrentView() != findViewById(R.id.stopwatch_layout)) {flipper.showNext();}}
        if (value.equals("7")) {flipper.setVisibility(INVISIBLE);}
    }

    public void setPadding(int verticalChange, int horizontalChange, int height, int width) {
        if (horizontalChange != 0 && verticalChange != 0) {
            flipper.setPadding(
                    (width / horizontalChange), // left
                    (height / verticalChange), // top
                    (width / horizontalChange),  // right
                    (height / verticalChange)   // bottom
            );
        } else {
            Toast.makeText(getContext(), "Error divide by 0: " + horizontalChange + " or " + verticalChange, Toast.LENGTH_SHORT).show();
        }
    }

    public int getPadding(){return  flipper.getPaddingTop();}

    public void createTypeBox(String name, int type, int maxCharacters) {
        //Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {Values.data.put(name, "");}

        //Creates the type box
        typeBox = findViewById(R.id.type_box);

        // Sets the input type (number or text)
        if (type == Values.inputType_number) {
            typeBox.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            typeBox.setSingleLine(false);
        }

        // Sets the max amount of characters
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(maxCharacters);
        typeBox.setFilters(filters);

        // Set inputted text
        if (Values.data.containsKey(name) && Values.data.get(name) != null) {
            typeBox.setText((CharSequence) Values.data.get(name));
        }

        // Updates data when text is changed
        typeBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                Values.data.put(name, typeBox.getText());
            }
        });
    }

    public void createTextDropdown(String name, ArrayList<String> array) {
        //Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {
            Values.data.put(name, "");
        }

        // Adds 'dropdown' to the beginning of the list to be a default value
        array.add(0, "Dropdown");

        // Creates the dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        textDropdown = findViewById(R.id.text_dropdown);
        textDropdown.setAdapter(adapter);

        // Sets the dropdown to be below the spinner border
        textDropdown.setDropDownVerticalOffset(5);

        // Set text dropdown selection
        if (Values.data.containsKey(name) && Values.data.get(name) != null) {
            int index = 0;
            for (String item: array){
                if (item.equals(Values.data.get(name))){
                    index = array.indexOf(item);
                }
            }
            textDropdown.setSelection(index);
        }

        // Updates data when new item is selected in dropdown
        textDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Values.data.put(name, textDropdown.getSelectedItem());
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void createTeamNumberDropdown(String name) {
        //Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {
            Values.data.put(name, "");
        }

        // Creates the dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), R.layout.spinner_dropdown, TeamNumbers.ramp_riot);
        numberDropdown = findViewById(R.id.number_dropdown);
        numberDropdown.setThreshold(1);
        numberDropdown.setAdapter(adapter);

        // Sets the dropdown to be below the spinner border
        numberDropdown.post(() -> numberDropdown.setDropDownVerticalOffset(5));

        // Set text to data value
        if (Values.data.containsKey(name)) {
            numberDropdown.setText(String.valueOf(Values.data.get(name)));
        }

        // Updates value when text is changed
        numberDropdown.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                String text = numberDropdown.getText().toString();
                if (!text.isEmpty()) {
                    Values.data.put(name, numberDropdown.getText().toString());
                }
            }
        });
    }

    public void createToggle(String name) {
        // Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {
            Values.data.put(name, false);
        }

        // Creates the toggle
        toggle = findViewById(R.id.toggle);
        LinearLayout toggleLayout = findViewById(R.id.toggle_layout);

        // Sets the track and thumb drawables
        toggle.setTrackResource(R.drawable.toggle_track);
        toggle.setThumbResource(R.drawable.toggle_thumb);

        // Scales the toggle based on the height
        toggle.setScaleX(Values.toggle_scale);
        toggle.setScaleY(Values.toggle_scale);
        toggleLayout.post(() -> {
            if (toggle.getScaleX() == 0) {
                Values.toggle_scale = (float) (toggleLayout.getHeight() * toggleLayout.getHeight() * toggleLayout.getHeight()) / (Values.screen_height * Values.screen_width * 5) * 2;
                toggle.setScaleX(Values.toggle_scale);
                toggle.setScaleY(Values.toggle_scale);
            }
        });

        // Set if checked or not
        if (Values.data.containsKey(name) && Values.data.get(name) != null) {
            toggle.setChecked((Boolean) Values.data.get(name));
        }

        // Updates value when checked is changed
        toggle.setOnCheckedChangeListener((buttonView, isChecked) -> Values.data.put(name, isChecked));
    }

    public void createCounter(String name, int maxValue) {
        // Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {
            Values.data.put(name, 0);
        }

        // Creates the buttons and text view
        counterMinus = findViewById(R.id.counter_minus);
        counterPlus = findViewById(R.id.counter_plus);
        counterNumber = findViewById(R.id.counter_number);

        // Set text to data value
        if (Values.data.containsKey(name)) {
            counterNumber.setText(String.valueOf(Values.data.get(name)));
        }

        // Updates value when plus is hit
        counterPlus.setOnClickListener(v -> {
            //gets the num from the view
            int num = Integer.parseInt(counterNumber.getText().toString());
            //sets the view to the num + 1
            if (num < maxValue) {
                num++;
                counterNumber.setText(String.valueOf(num));
            }
            Values.data.put(name, num);
        });

        // Updates value when minus is hit
        counterMinus.setOnClickListener(v -> {
            //gets the number from the view
            int num = Integer.parseInt(counterNumber.getText().toString());
            //sets the view to the number - 1
            if (num > 0) {
                num--;
                counterNumber.setText(String.valueOf(num));
            }
            Values.data.put(name, num);
        });
    }
    // Multi counter with 1 increment set
    public void createMultiCounter(String name, int maxValue, int colorPattern, int incAmount1) {
        // Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {
            Values.data.put(name, 0);
        }

        // Creates the buttons and text view
        multiCounterCount = findViewById(R.id.multi_counter_count);
        multiCounterPlus1 = findViewById(R.id.multi_counter_plus_1);
        multiCounterMinus1 = findViewById(R.id.multi_counter_minus_1);
        multiCounterIncrement1 = findViewById(R.id.multi_counter_increment_1);
        multiCounterLayout = findViewById(R.id.multi_counter_layout);
        multiCounterLayout1 = findViewById(R.id.multi_counter_layout_1);
        multiCounterLayout2 = findViewById(R.id.multi_counter_layout_2);
        multiCounterLayout3 = findViewById(R.id.multi_counter_layout_3);

        // Makes the 3rd layout not exist, so there are only 2
        multiCounterLayout3.setVisibility(GONE);
        multiCounterLayout2.setVisibility(GONE);
        multiCounterLayout.setWeightSum(2f);

        // Set text to data value
        if (Values.data.containsKey(name)) {
            multiCounterCount.setText(String.valueOf(Values.data.get(name)));
        }

        // Sets the color pattern of the counter background
        if (colorPattern == Values.colorType_greyWhiteGrey){
            multiCounterLayout.setBackgroundResource(R.color.light_grey);
            multiCounterLayout1.setBackgroundResource(R.color.white);
            multiCounterLayout2.setBackgroundResource(R.color.light_grey);
        } else if (colorPattern == Values.colorType_whiteGreyWhite){
            multiCounterLayout.setBackgroundResource(R.color.white);
            multiCounterLayout1.setBackgroundResource(R.color.light_grey);
            multiCounterLayout2.setBackgroundResource(R.color.white);
        } else if (colorPattern == Values.colorType_allGrey){
            multiCounterLayout.setBackgroundResource(R.color.light_grey);
            multiCounterLayout1.setBackgroundResource(R.color.light_grey);
            multiCounterLayout2.setBackgroundResource(R.color.light_grey);
        } else {
            multiCounterLayout.setBackgroundResource(R.color.white);
            multiCounterLayout1.setBackgroundResource(R.color.white);
            multiCounterLayout2.setBackgroundResource(R.color.white);
        }

        // Sets no padding around flipper
        flipper.setPadding(0,0,0,0);
        // Dynamically sets padding between layouts the first time the flipper
        flipper.post(() -> {
            multiCounterLayout1.setPadding((flipper.getWidth() / 20), (flipper.getHeight() / 20),
                    (flipper.getWidth() / 20), (flipper.getHeight() / 20));
            multiCounterLayout2.setPadding((flipper.getWidth() / 20), (flipper.getHeight() / 20),
                    (flipper.getWidth() / 20), (flipper.getHeight() / 20));
        });

        // Set text views to show correct increment amount
        multiCounterIncrement1.setText("+- " + incAmount1);

        /*  Incrementors    */
        // Adds to value by increment amount 1 when plus is hit
        multiCounterPlus1.setOnClickListener(v -> {
            //gets the num from the view
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            //sets the view to the num + inc
            if (num <= maxValue-incAmount1) {
                num += incAmount1;
                multiCounterCount.setText(String.valueOf(num));
            }
            Values.data.put(name, num);
        });

        // Subtracts from value by increment amount 1 when minus is hit
        multiCounterMinus1.setOnClickListener(v -> {
            //gets the number from the view
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            //sets the view to the number - inc
            if (num >= incAmount1) {
                num -= incAmount1;
                multiCounterCount.setText(String.valueOf(num));
            }
            Values.data.put(name, num);
        });
    }
    // Multi counter with 2 increment sets
    public void createMultiCounter(String name, int maxValue, int colorPattern, int incAmount1, int incAmount2) {
        // Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {
            Values.data.put(name, 0);
        }

        // Creates the buttons and text view
        multiCounterCount = findViewById(R.id.multi_counter_count);
        multiCounterPlus1 = findViewById(R.id.multi_counter_plus_1);
        multiCounterMinus1 = findViewById(R.id.multi_counter_minus_1);
        multiCounterPlus2 = findViewById(R.id.multi_counter_plus_2);
        multiCounterMinus2 = findViewById(R.id.multi_counter_minus_2);
        multiCounterIncrement1 = findViewById(R.id.multi_counter_increment_1);
        multiCounterIncrement2 = findViewById(R.id.multi_counter_increment_2);
        multiCounterLayout = findViewById(R.id.multi_counter_layout);
        multiCounterLayout1 = findViewById(R.id.multi_counter_layout_1);
        multiCounterLayout2 = findViewById(R.id.multi_counter_layout_2);
        multiCounterLayout3 = findViewById(R.id.multi_counter_layout_3);

        // Makes the 3rd layout not exist, so there are only 2
        multiCounterLayout3.setVisibility(GONE);
        multiCounterLayout.setWeightSum(3f);

        // Set text to data value
        if (Values.data.containsKey(name)) {
            multiCounterCount.setText(String.valueOf(Values.data.get(name)));
        }

        // Sets the color pattern of the counter background
        if (colorPattern == Values.colorType_greyWhiteGrey){
            multiCounterLayout.setBackgroundResource(R.color.light_grey);
            multiCounterLayout1.setBackgroundResource(R.color.white);
            multiCounterLayout2.setBackgroundResource(R.color.light_grey);
        } else if (colorPattern == Values.colorType_whiteGreyWhite){
            multiCounterLayout.setBackgroundResource(R.color.white);
            multiCounterLayout1.setBackgroundResource(R.color.light_grey);
            multiCounterLayout2.setBackgroundResource(R.color.white);
        } else if (colorPattern == Values.colorType_allGrey){
            multiCounterLayout.setBackgroundResource(R.color.light_grey);
            multiCounterLayout1.setBackgroundResource(R.color.light_grey);
            multiCounterLayout2.setBackgroundResource(R.color.light_grey);
        } else {
            multiCounterLayout.setBackgroundResource(R.color.white);
            multiCounterLayout1.setBackgroundResource(R.color.white);
            multiCounterLayout2.setBackgroundResource(R.color.white);
        }

        // Sets no padding around flipper
        flipper.setPadding(0,0,0,0);
        // Dynamically sets padding between layouts the first time the flipper
        flipper.post(() -> {
            multiCounterLayout1.setPadding((flipper.getWidth() / 20), (flipper.getHeight() / 20),
                    (flipper.getWidth() / 20), (flipper.getHeight() / 20));
            multiCounterLayout2.setPadding((flipper.getWidth() / 20), (flipper.getHeight() / 20),
                    (flipper.getWidth() / 20), (flipper.getHeight() / 20));
        });

        // Set text views to show correct increment amount
        multiCounterIncrement1.setText("+- " + incAmount1);
        multiCounterIncrement2.setText("+- " + incAmount2);

        /*  Incrementors    */
        // Adds to value by increment amount 1 when plus is hit
        multiCounterPlus1.setOnClickListener(v -> {
            //gets the num from the view
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            //sets the view to the num + inc
            if (num <= maxValue-incAmount1) {
                num += incAmount1;
                multiCounterCount.setText(String.valueOf(num));
            }
            Values.data.put(name, num);
        });

        // Subtracts from value by increment amount 1 when minus is hit
        multiCounterMinus1.setOnClickListener(v -> {
            //gets the number from the view
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            //sets the view to the number - inc
            if (num >= incAmount1) {
                num -= incAmount1;
                multiCounterCount.setText(String.valueOf(num));
            }
            Values.data.put(name, num);
        });

        // Adds to value by increment amount 2 when plus is hit
        multiCounterPlus2.setOnClickListener(v -> {
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            if (num <= maxValue-incAmount2) {
                num += incAmount2;
                multiCounterCount.setText(String.valueOf(num));
            } Values.data.put(name, num);
        });

        // Subtracts from value by increment amount 2 when minus is hit
        multiCounterMinus2.setOnClickListener(v -> {
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            if (num >= incAmount2) {
                num -= incAmount2;
                multiCounterCount.setText(String.valueOf(num));
            } Values.data.put(name, num);
        });
    }
    // Multi counter with 3 increment sets
    public void createMultiCounter(String name, int maxValue, int colorPattern, int incAmount1, int incAmount2, int incAmount3) {
        // Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {
            Values.data.put(name, 0);
        }

        // Creates the buttons and text view
        multiCounterCount = findViewById(R.id.multi_counter_count);
        multiCounterPlus1 = findViewById(R.id.multi_counter_plus_1);
        multiCounterMinus1 = findViewById(R.id.multi_counter_minus_1);
        multiCounterPlus2 = findViewById(R.id.multi_counter_plus_2);
        multiCounterMinus2 = findViewById(R.id.multi_counter_minus_2);
        multiCounterPlus3 = findViewById(R.id.multi_counter_plus_3);
        multiCounterMinus3 = findViewById(R.id.multi_counter_minus_3);
        multiCounterIncrement1 = findViewById(R.id.multi_counter_increment_1);
        multiCounterIncrement2 = findViewById(R.id.multi_counter_increment_2);
        multiCounterIncrement3 = findViewById(R.id.multi_counter_increment_3);
        multiCounterLayout = findViewById(R.id.multi_counter_layout);
        multiCounterLayout1 = findViewById(R.id.multi_counter_layout_1);
        multiCounterLayout2 = findViewById(R.id.multi_counter_layout_2);
        multiCounterLayout3 = findViewById(R.id.multi_counter_layout_3);

        // Set text to data value
        if (Values.data.containsKey(name)) {
            multiCounterCount.setText(String.valueOf(Values.data.get(name)));
        }

        // Sets the color pattern of the counter background
        if (colorPattern == Values.colorType_greyWhiteGrey){
            multiCounterLayout.setBackgroundResource(R.color.light_grey);
            multiCounterLayout1.setBackgroundResource(R.color.white);
            multiCounterLayout2.setBackgroundResource(R.color.light_grey);
            multiCounterLayout3.setBackgroundResource(R.color.white);
        } else if (colorPattern == Values.colorType_whiteGreyWhite){
            multiCounterLayout.setBackgroundResource(R.color.white);
            multiCounterLayout1.setBackgroundResource(R.color.light_grey);
            multiCounterLayout2.setBackgroundResource(R.color.white);
            multiCounterLayout3.setBackgroundResource(R.color.light_grey);
        } else if (colorPattern == Values.colorType_allGrey){
            multiCounterLayout.setBackgroundResource(R.color.light_grey);
            multiCounterLayout1.setBackgroundResource(R.color.light_grey);
            multiCounterLayout2.setBackgroundResource(R.color.light_grey);
            multiCounterLayout3.setBackgroundResource(R.color.light_grey);
        } else {
            multiCounterLayout.setBackgroundResource(R.color.white);
            multiCounterLayout1.setBackgroundResource(R.color.white);
            multiCounterLayout2.setBackgroundResource(R.color.white);
            multiCounterLayout3.setBackgroundResource(R.color.white);
        }

        // Sets no padding around flipper
        flipper.setPadding(0,0,0,0);
        // Dynamically sets padding between layouts the first time the flipper
        flipper.post(() -> {
            multiCounterLayout1.setPadding((flipper.getWidth() / 20), (flipper.getHeight() / 20),
                    (flipper.getWidth() / 20), (flipper.getHeight() / 20));
            multiCounterLayout2.setPadding((flipper.getWidth() / 20), (flipper.getHeight() / 20),
                    (flipper.getWidth() / 20), (flipper.getHeight() / 20));
            multiCounterLayout3.setPadding((flipper.getWidth() / 20), (flipper.getHeight() / 20),
                    (flipper.getWidth() / 20), (flipper.getHeight() / 20));
        });

        // Set text views to show correct increment amount
        multiCounterIncrement1.setText("+- " + incAmount1);
        multiCounterIncrement2.setText("+- " + incAmount2);
        multiCounterIncrement3.setText("+- " + incAmount3);

        /*  Incrementors    */
        // Adds to value by increment amount 1 when plus is hit
        multiCounterPlus1.setOnClickListener(v -> {
            //gets the num from the view
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            //sets the view to the num + inc
            if (num <= maxValue-incAmount1) {
                num += incAmount1;
                multiCounterCount.setText(String.valueOf(num));
            }
            Values.data.put(name, num);
        });

        // Subtracts from value by increment amount 1 when minus is hit
        multiCounterMinus1.setOnClickListener(v -> {
            //gets the number from the view
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            //sets the view to the number - inc
            if (num >= incAmount1) {
                num -= incAmount1;
                multiCounterCount.setText(String.valueOf(num));
            }
            Values.data.put(name, num);
        });

        // Adds to value by increment amount 2 when plus is hit
        multiCounterPlus2.setOnClickListener(v -> {
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            if (num <= maxValue-incAmount2) {
                num += incAmount2;
                multiCounterCount.setText(String.valueOf(num));
            } Values.data.put(name, num);
        });

        // Subtracts from value by increment amount 2 when minus is hit
        multiCounterMinus2.setOnClickListener(v -> {
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            if (num >= incAmount2) {
                num -= incAmount2;
                multiCounterCount.setText(String.valueOf(num));
            } Values.data.put(name, num);
        });

        // Adds to value by increment amount 3 when plus is hit
        multiCounterPlus3.setOnClickListener(v -> {
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            if (num <= maxValue-incAmount3) {
                num += incAmount3;
                multiCounterCount.setText(String.valueOf(num));
            } Values.data.put(name, num);
        });

        // Subtracts from value by increment amount 3 when minus is hit
        multiCounterMinus3.setOnClickListener(v -> {
            int num = Integer.parseInt(multiCounterCount.getText().toString());
            if (num >= incAmount3) {
                num -= incAmount3;
                multiCounterCount.setText(String.valueOf(num));
            } Values.data.put(name, num);
        });

    }

    public void createStopwatch(String name) {
        // Creates the data in the hashmap
        if (!Values.data.containsKey(name)) {Values.data.put(name, 0);}

        // Sets this file's stopwatch name to be the name so the runnable can use it
        stopwatchName = name;

        // Creates the buttons and text view
        stopwatchTimer = findViewById(R.id.stopwatch_timer);
        stopwatchRestart = findViewById(R.id.stopwatch_restart);
        stopwatchStart = findViewById(R.id.stopwatch_start);
        stopwatchStop = findViewById(R.id.stopwatch_stop);

        // Set text to data value
        if (Values.data.containsKey(name)) {
            stopwatchSeconds = Integer.parseInt(Objects.requireNonNull(Values.data.get(name)).toString());
            stopwatchMinutes = stopwatchSeconds / 60;
            stopwatchSeconds = stopwatchSeconds % 60;
            String text = (stopwatchMinutes + ":" + String.format(Locale.getDefault(), "%02d", stopwatchSeconds));
            stopwatchTimer.setText(text);
        }

        stopwatchStart.setOnClickListener(v -> {
            // Gets the start system time
            stopwatchStartTimeMillis = (int) SystemClock.uptimeMillis();
            // Sets the saved time to the time saved in data
            stopwatchSavedTimeMillis = (int) Values.data.get(stopwatchName)*1000;
            // Starts the stopwatch
            stopwatchHandler.postDelayed(stopwatchRunnable, 0);
            // Disables specific buttons
            stopwatchRestart.setClickable(false);
            stopwatchRestart.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline_tinted, getContext().getTheme()));
            stopwatchStop.setClickable(true);
            stopwatchStop.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline, getContext().getTheme()));
            stopwatchStart.setClickable(false);
            stopwatchStart.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline_tinted, getContext().getTheme()));
        });

        stopwatchStop.setOnClickListener(v -> {
            // Saves the current time
            stopwatchSavedTimeMillis += stopwatchElapsedTimeMillis;
            // Pauses the stopwatch
            stopwatchHandler.removeCallbacks(stopwatchRunnable);
            // Disables specific buttons
            stopwatchRestart.setClickable(true);
            stopwatchRestart.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline, getContext().getTheme()));
            stopwatchStop.setClickable(false);
            stopwatchStop.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline_tinted, getContext().getTheme()));
            stopwatchStart.setClickable(true);
            stopwatchStart.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline, getContext().getTheme()));
        });

        stopwatchRestart.setOnClickListener(v ->{
            // Sets all values to 0 to restart timer
            stopwatchElapsedTimeMillis = 0;
            stopwatchStartTimeMillis = 0;
            stopwatchSavedTimeMillis = 0;
            stopwatchSeconds = 0;
            stopwatchMinutes = 0;
            Values.data.put(name, 0);
            String text = (((int)Values.data.get(name) / 60) + ":" + String.format(Locale.getDefault(), "%02d", ((int)Values.data.get(name) % 60)));
            stopwatchTimer.setText(text);
        });
    }

    public void pauseStopwatch(){
        // When the stopwatch is not 0:00, pause it (same code as stopwatchStop.setOnClickListener)
        if (!stopwatchTimer.getText().toString().equals("0:00")){
            // Saves the current time
            stopwatchSavedTimeMillis += stopwatchElapsedTimeMillis;
            // Pauses the stopwatch
            stopwatchHandler.removeCallbacks(stopwatchRunnable);
            // Disables specific buttons
            stopwatchRestart.setClickable(true);
            stopwatchRestart.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline, getContext().getTheme()));
            stopwatchStop.setClickable(false);
            stopwatchStop.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline_tinted, getContext().getTheme()));
            stopwatchStart.setClickable(true);
            stopwatchStart.setForeground(ResourcesCompat.getDrawable(getResources(), R.drawable.box_outline, getContext().getTheme()));

        }
    }
}