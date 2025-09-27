package com.example.a40290831_ass1;

import android.content.Context;

/**
 * ===== MVC ARCHITECTURE - CONTROLLER =====
 * 
 * Main Controller class that coordinates between Model and View components.
 * Handles all business logic for the counter application.
 * 
 * Responsibilities:
 * - Manages Settings and CounterData models
 * - Coordinates with SharedPreferenceHelper for data persistence
 * - Provides methods for Views to interact with data
 * - Handles validation and business rules
 * 
 * Works in coordination with:
 * - Models: Settings.java, CounterData.java
 * - Views: MainActivity.java, SettingsActivity.java, DataActivity.java
 * - Data Controller: SharedPreferenceHelper.java
 */
public class CounterController {
    
    private SharedPreferenceHelper sharedPreferenceHelper;
    private Settings settings;
    private CounterData counterData;
    public CounterController(Context context) {
        sharedPreferenceHelper = new SharedPreferenceHelper(context);
        loadData();
    }
    
//load data from shared preferences, using methods in Settings class
    public void loadData() {
        settings = sharedPreferenceHelper.loadSettings();
        counterData = sharedPreferenceHelper.loadCounterData();
    }
    
    /**
     * Refresh data from SharedPreferences (call when returning from other activities)
     * This reloads both settings and counter data to ensure UI shows current values
     */
    public void refreshData() {
        loadData(); // Reload both settings and counter data from SharedPreferences
    }
    
//Save data to shared preferences, using methods in Settings class
    public void saveData() {
        sharedPreferenceHelper.saveSettings(settings);
        sharedPreferenceHelper.saveCounterData(counterData);
    }
    
    // Settings management using methods from the Settings class
    
    public Settings getSettings() {
        return settings;
    }

    //Creating a comprehensive setter method
    public void updateSettings(String counter1Name, String counter2Name, String counter3Name, int maxCount) {
        // Check if max count is changing
        boolean maxCountChanged = (settings.getMaxCount() != maxCount);
        
        settings.setCounter1Name(counter1Name);
        settings.setCounter2Name(counter2Name);
        settings.setCounter3Name(counter3Name);
        settings.setMaxCount(maxCount);
        sharedPreferenceHelper.saveSettings(settings);
        
        // Reset counters if max count changed
        if (maxCountChanged) {
            resetCounters();
        }
    }
    
    /*
     * Update settings with option to reset counters
     * counter1Name Name for counter 1
     * counter2Name Name for counter 2
     * counter3Name Name for counter 3
     * maxCount Maximum count limit
     * resetCounters - to reset all counters to 0
     */
    public void updateSettings(String counter1Name, String counter2Name, String counter3Name, int maxCount, boolean resetCounters) {
        settings.setCounter1Name(counter1Name);
        settings.setCounter2Name(counter2Name);
        settings.setCounter3Name(counter3Name);
        settings.setMaxCount(maxCount);
        sharedPreferenceHelper.saveSettings(settings);
        
        if (resetCounters) {
            resetCounters();
        }
    }

    public boolean areSettingsValid() {
        return settings.isValid();
    }
    
    public boolean areNamesSet() {
        return settings.areNamesSet();
    }
    
    // Counter Management
    
    public CounterData getCounterData() {
        return counterData;
    }
    
    public boolean canIncrement() {
        return counterData.canIncrement(settings.getMaxCount());
    }
    
    public boolean incrementButtonA() {
        if (canIncrement()) {
            counterData.incrementButtonA();
            sharedPreferenceHelper.saveCounterData(counterData);
            return true;
        }
        return false;
    }
    
    public boolean incrementButtonB() {
        if (canIncrement()) {
            counterData.incrementButtonB();
            sharedPreferenceHelper.saveCounterData(counterData);
            return true;
        }
        return false;
    }
    
    public boolean incrementButtonC() {
        if (canIncrement()) {
            counterData.incrementButtonC();
            sharedPreferenceHelper.saveCounterData(counterData);
            return true;
        }
        return false;
    }
    
    public void resetCounters() {
        counterData.reset();
        sharedPreferenceHelper.clearCounterData();
    }
    
    // Data Display Methods
    
    public String getFormattedTotalCount() {
        // Ensures we're getting the current total count from the loaded counterData
        return "Total Count: " + counterData.getTotalCount();
    }

    //If showNames true, return chosen event names
    //else show counter 1, counter 2, ...
    public String getFormattedCounterText(int counterNumber, boolean showNames) {
        int count;
        String name;
        
        switch (counterNumber) {
            case 1:
                count = counterData.getButtonACount();
                //Remember showNames is a bool
                name = showNames ? settings.getCounter1Name() : "Counter 1";
                break;
            case 2:
                count = counterData.getButtonBCount();
                name = showNames ? settings.getCounter2Name() : "Counter 2";
                break;
            case 3:
                count = counterData.getButtonCCount();
                name = showNames ? settings.getCounter3Name() : "Counter 3";
                break;
            default:
                return "";
        }
        
        String eventText = count == 1 ? " event" : " events";
        return name + ": " + count + eventText;
    }
    
    /**
     * Get display list for event history
     * @param showNames true to show custom names, false to show counter numbers
     * @return Array of strings for display
     */
    public String[] getEventHistoryDisplay(boolean showNames) {
        String[] displayArray = new String[counterData.getEventHistory().size()];
        
        for (int i = 0; i < counterData.getEventHistory().size(); i++) {
            String event = counterData.getEventHistory().get(i);
            if (showNames) {
                switch (event) {
                    case "1":
                        displayArray[i] = settings.getCounter1Name();
                        break;
                    case "2":
                        displayArray[i] = settings.getCounter2Name();
                        break;
                    case "3":
                        displayArray[i] = settings.getCounter3Name();
                        break;
                    default:
                        displayArray[i] = event;
                        break;
                }
            } else {
                displayArray[i] = event;
            }
        }
        
        return displayArray;
    }
    
    // Input validation like the assignment example
    
    public static boolean isValidCounterName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Z]+");
    }
    
    public static boolean isValidMaxCount(String maxCountText) {
        try {
            int maxCount = Integer.parseInt(maxCountText);
            return maxCount > 0 && maxCount <= 999999; // Max 6 digits
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Debug method to check current counter values
     */
    public String getDebugInfo() {
        return "A:" + counterData.getButtonACount() + 
               " B:" + counterData.getButtonBCount() + 
               " C:" + counterData.getButtonCCount() + 
               " Total:" + counterData.getTotalCount();
    }
}
