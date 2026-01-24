package com.fro.scoutingapptest;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Creates a generic row with multiple boxes. <br/>
 *
 * @requires Variants of app:position_text=""
 * <br> The text that will be displayed in the box header

 * @requires Variants of app:box_type=""
 * <br> The type of component that will be in the box
 * <ul>
 *     <li>text_type_box - Typed input</li>
 *     <li>number_type_box - Typed input with numbers only</li>
 *     <li>text_dropdown - A dropdown list</li>
 *     <li>team_number_dropdown - A searchable dropdown of team numbers</li>
 *     <li>toggle - An on/off switch</li>
 *     <li>counter - A plus/minus counter starting at 0, incremented by 1</li>
 *     <li>multi_counter - A plus/minus counter with multiple increment sets starting at 0. Can have 1/2/3 increment sets.</li>
 *     <li>stopwatch - A stopwatch with on, off, and reset</li>
 * </ul>
 */
public interface InterfaceMultiRow {
    public void setText(TextView textview, CharSequence value);
    public void setBox(ComponentFlipper flipper, CharSequence value);

    /**
     * Creates a text box
     * @param name The name the data will be exported as. Usually structured as page_data.
     * <br/> Ex. "Teleop_Pieces_Scored"
     * @param inputType Specifies type of input that will be accepted.
     *  <ul>
     *      <li>Values.inputType_text - Normal input</li>
     *      <li>Values.inputType_number - Typed input with numbers only</li>
     * </ul>
     * @param maxCharacters The max amount of characters the user is allowed to type in
     * @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - Left box</li>
     *      <li>Values.middle - Middle box</li>
     *      <li>Values.right - Right box</li>
     * </ul>
     */
    public void createTypeBox(String name, int inputType, int maxCharacters, int position);

    /**
     * Creates a normal dropdown list with text options
     * @param name The name the data will be exported as. Usually structured as page_data.
     * <br/> Ex. "Teleop_Pieces_Scored"
     * @param array An array list with the dropdown options.
     * <br/> Ex: (.., new ArrayList<>(Arrays.asList("O1", "O2", "O3")));
     * @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.middle - The component will be in the middle box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createTextDropdown(String name, ArrayList<String> array, int position);

    /**
     * Creates a searchable dropdown with team numbers. To update the team numbers, edit the current team list in TeamNumbers.java
     * @param name The name the data will be exported as. Usually structured as page_data.
     * <br/> Ex. "Teleop_Pieces_Scored"
     * @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.middle - The component will be in the middle box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createTeamNumberDropdown(String name, int position);
    /**
     * Creates an on/off switch
     * @param name The name the data will be exported as. Usually structured as page_data.
     * <br/> Ex. "Teleop_Pieces_Scored"
     * @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.middle - The component will be in the middle box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createToggle(String name, int position);
    /**
     * Creates an a plus/minus counter starting at 0, incremented by 1.
     * @param name The name the data will be exported as. Usually structured as page_data.
     * <br/> Ex. "Teleop_Pieces_Scored"
     * @param maxValue The max value that the user is able to count to
     * @param position The box that this component will be created in.
     * <ul>
     *      <li>Values.left - The component will be in the left box</li>
     *      <li>Values.middle - The component will be in the middle box</li>
     *      <li>Values.right - The component will be in the right box</li>
     * </ul>
     */
    public void createCounter(String name, int maxValue, int position);

    /**
     * Creates a plus/minus counter with multiple increment sets starting at 0.
     * Can have 1, 2, or 3 increment sets, based on how many increment values are put in the parameters.
     * @param name The name the data will be exported as. Usually structured as page_data.
     * <br/> Ex. "Teleop_Pieces_Scored"
     *
     * @param maxValue The max value that can be put in
     * @param position The box that this component is in.
     * <ul>
     *      <li>Values.left - The component is in the left box</li>
     *      <li>Values.middle - The component is in the middle box</li>
     *      <li>Values.right - The component is in the right box</li>
     * </ul>
     * @param colorPattern The the pattern that the background color sections will be
     *  <ul>
     *      <li>Values.greyWhiteGray - From top to bottom, alternates grey and white backgrounds</li>
     *      <li>Values.whiteGreyWhite - From top to bottom, alternates white and grey backgrounds</li>
     *      <li>Values.allWhite - Whole background is white</li>
     *      <li>Values.allGrey - Whole background is grey</li>
     * </ul>
     *
     * @param incAmount1 The amount to increment the first set of plus/minus buttons with
     * @implNote <b> incAmount2 </b> (Optional parameter) - The amount to increment the second set of plus/minus buttons with
     * <br/> <b> incAmount3 </b> (Optional parameter) - The amount to increment the third set of plus/minus buttons with
     */
    // With 1 increment set
    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1);

    /**
     * Creates a plus/minus counter with multiple increment sets starting at 0.
     * Can have 1, 2, or 3 increment sets, based on how many increment values are put in the parameters.
     * @param name The name the data will be exported as. Usually structured as page_data.
     * <br/> Ex. "Teleop_Pieces_Scored"
     *
     * @param maxValue The max value that can be put in
     * @param position The box that this component is in.
     * <ul>
     *      <li>Values.left - The component is in the left box</li>
     *      <li>Values.middle - The component is in the middle box</li>
     *      <li>Values.right - The component is in the right box</li>
     * </ul>
     * @param colorPattern The the pattern that the background color sections will be
     *  <ul>
     *      <li>Values.greyWhiteGray - From top to bottom, alternates grey and white backgrounds</li>
     *      <li>Values.whiteGreyWhite - From top to bottom, alternates white and grey backgrounds</li>
     *      <li>Values.allWhite - Whole background is white</li>
     *      <li>Values.allGrey - Whole background is grey</li>
     * </ul>
     *
     * @param incAmount1 The amount to increment the first set of plus/minus buttons with
     * @param incAmount2 (Optional) The amount to increment the second set of plus/minus buttons with
     * @implNote <b> incAmount3 </b> (Optional parameter) - The amount to increment the third set of plus/minus buttons with
     */
    // With 2 increment sets
    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1, int incAmount2);
    /**
     * Creates a plus/minus counter with multiple increment sets starting at 0.
     * Can have 1, 2, or 3 increment sets, based on how many increment values are put in the parameters.
     * @param name The name the data will be exported as. Usually structured as page_data.
     * <br/> Ex. "Teleop_Pieces_Scored"
     *
     * @param maxValue The max value that can be put in
     * @param position The box that this component is in.
     * <ul>
     *      <li>Values.left - The component is in the left box</li>
     *      <li>Values.middle - The component is in the middle box</li>
     *      <li>Values.right - The component is in the right box</li>
     * </ul>
     * @param colorPattern The the pattern that the background color sections will be
     *  <ul>
     *      <li>Values.greyWhiteGray - From top to bottom, alternates grey and white backgrounds</li>
     *      <li>Values.whiteGreyWhite - From top to bottom, alternates white and grey backgrounds</li>
     *      <li>Values.allWhite - Whole background is white</li>
     *      <li>Values.allGrey - Whole background is grey</li>
     * </ul>
     *
     * @param incAmount1 The amount to increment the first set of plus/minus buttons with
     * @param incAmount2 (Optional) The amount to increment the second set of plus/minus buttons with
     * @param incAmount3 (Optional) The amount to increment the third set of plus/minus buttons with
     */
    // With 3 increment sets
    public void createMultiCounter(String name, int maxValue, int position, int colorPattern, int incAmount1, int incAmount2, int incAmount3);

    /**
     * Creates a stopwatch with a start, stop, and reset button.
     * @requires To implement, you MUST have {@link #pauseStopwatch} at the bottom of the Fragment.
     * See {@link #pauseStopwatch} for how to implement.
     *
     * @param name The name the data will be exported as. Usually structured as page_data.
     * <br/> Ex. "Teleop_Pieces_Scored"
     * @param position The box that this component is in.
     * <ul>
     *      <li>Values.left - The component is in the left box</li>
     *      <li>Values.middle - The component is in the middle box</li>
     *      <li>Values.right - The component is in the right box</li>
     * </ul>
     */
    // A stopwatch with on, off, and reset
    public void createStopwatch(String name, int position);

    /**
     * Stops the stopwatch. Used when the parent Fragment is hidden (not the visible Fragment).
     * <br/>At the bottom of your code, after the main {@code public View createView()} method is closed, create this code:
     * <pre>{@code @Override
     * public void onHiddenChanged(boolean hidden) {
     *     row_name.pauseStopwatch(Values.position);
     *     super.onHiddenChanged(hidden);
     * } }</pre>
     * @param position The box that this component is in.
     * <ul>
     *      <li>Values.left - The component is in the left box</li>
     *      <li>Values.middle - The component is in the middle box</li>
     *      <li>Values.right - The component is in the right box</li>
     * </ul>
     */
    public void pauseStopwatch(int position);
}
