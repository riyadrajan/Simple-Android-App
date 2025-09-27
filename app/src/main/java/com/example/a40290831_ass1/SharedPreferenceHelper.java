package com.example.a40290831_ass1;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * ===== MVC ARCHITECTURE - CONTROLLER (Data Persistence) =====
 * 
 * Data persistence controller that handles SharedPreferences operations.
 * Follows MVC pattern by separating data storage logic from business logic.
 * 
 * Responsibilities:
 * - Save/load Settings objects to/from SharedPreferences
 * - Save/load CounterData objects to/from SharedPreferences
 * - Handle CSV conversion for event history storage
 * - Provide clean interface for model object persistence
 * 
 * Works with Models:
 * - Settings.java (configuration data)
 * - CounterData.java (counter state and event history)
 * 
 * Used by Controller:
 * - CounterController.java (main business logic controller)
 */
public class SharedPreferenceHelper {

    private SharedPreferences sharedPreferences;

    //Constructor
    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE);
    }

    //--------------Counter 1
    public void setCounter1Name(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("counter1Name", name);
        editor.apply();
    }

    public String getCounter1Name() {
        return sharedPreferences.getString("counter1Name", null);
    }

    //-----------------Counter 2
    public void setCounter2Name(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("counter2Name", name);
        editor.apply();
    }

    public String getCounter2Name() {
        return sharedPreferences.getString("counter2Name", null);
    }

    //---------------Counter 3
    public void setCounter3Name(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("counter3Name", name);
        editor.apply();
    }

    public String getCounter3Name() {
        return sharedPreferences.getString("counter3Name", null);
    }

    //---------------Max Count
    public void setMaxCount(int max) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("maxCount", max);
        editor.apply();
    }

    public int getMaxCount() {
        return sharedPreferences.getInt("maxCount", 0);
    }

    // Work with settings objects like A1 instructions
    // Set the counter/event names from input
    public void saveSettings(Settings settings) {
        //Using what was in the A1 instructions
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("counter1Name", settings.getCounter1Name());
        editor.putString("counter2Name", settings.getCounter2Name());
        editor.putString("counter3Name", settings.getCounter3Name());
        editor.putInt("maxCount", settings.getMaxCount());
        editor.apply();
    }

//load the settings object
    public Settings loadSettings() {
        Settings settings = new Settings();
        settings.setCounter1Name(sharedPreferences.getString("counter1Name", null));
        settings.setCounter2Name(sharedPreferences.getString("counter2Name", null));
        settings.setCounter3Name(sharedPreferences.getString("counter3Name", null));
        settings.setMaxCount(sharedPreferences.getInt("maxCount", 0));
        return settings;
    }

    // --------------Counter Data Persistence
    
    /**
     * Save counter data including event history as CSV
     * @param counterData CounterData object containing all counter information
     */
    public void saveCounterData(CounterData counterData) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("buttonACount", counterData.getButtonACount());
        editor.putInt("buttonBCount", counterData.getButtonBCount());
        editor.putInt("buttonCCount", counterData.getButtonCCount());
        editor.putInt("totalCount", counterData.getTotalCount());
        editor.putString("eventHistory", counterData.getEventHistoryAsString());
        editor.apply();
    }

    /**
     * Load counter data including event history from CSV
     * @return CounterData object with all counter information
     */
    public CounterData loadCounterData() {
        CounterData counterData = new CounterData();
        counterData.setButtonACount(sharedPreferences.getInt("buttonACount", 0));
        counterData.setButtonBCount(sharedPreferences.getInt("buttonBCount", 0));
        counterData.setButtonCCount(sharedPreferences.getInt("buttonCCount", 0));
        counterData.setTotalCount(sharedPreferences.getInt("totalCount", 0));
        counterData.setEventHistoryFromString(sharedPreferences.getString("eventHistory", ""));
        return counterData;
    }

    /**
     * Clear all counter data (reset counters)
     */
    public void clearCounterData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("buttonACount");
        editor.remove("buttonBCount");
        editor.remove("buttonCCount");
        editor.remove("totalCount");
        editor.remove("eventHistory");
        editor.apply();
    }

}
