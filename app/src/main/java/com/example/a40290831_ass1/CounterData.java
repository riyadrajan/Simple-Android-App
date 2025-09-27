package com.example.a40290831_ass1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ===== MVC ARCHITECTURE - MODEL =====
 * 
 * CounterData model that holds counter state and event history.
 * Represents the business data for the counting functionality.
 * 
 * Responsibilities:
 * - Store individual counter values (buttonACount, buttonBCount, buttonCCount)
 * - Store total count across all counters
 * - Maintain event history as ArrayList<String>
 * - Provide business logic methods (increment, reset, validation)
 * - Handle CSV conversion for SharedPreferences storage
 * 
 * Used by:
 * - Controller: CounterController.java (business logic)
 * - Controller: SharedPreferenceHelper.java (data persistence)
 * - Views: MainActivity.java, DataActivity.java (via controller)
 */
public class CounterData {
    private int buttonACount;
    private int buttonBCount;
    private int buttonCCount;
    private int totalCount;
    private ArrayList<String> eventHistory;

    // Constructor
    public CounterData() {
        this.buttonACount = 0;
        this.buttonBCount = 0;
        this.buttonCCount = 0;
        this.totalCount = 0;
        this.eventHistory = new ArrayList<>();
    }

    // Getters
    public int getButtonACount() {
        return buttonACount;
    }

    public int getButtonBCount() {
        return buttonBCount;
    }

    public int getButtonCCount() {
        return buttonCCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    //To keep track of what clicks were made (referring to DataActivity)
    public ArrayList<String> getEventHistory() {
        return eventHistory;
    }

    //My helper methods
    public boolean canIncrement(int maxCount) {
        return totalCount < maxCount;
    }

    //Before implementing MVC, this was all in MainActivity
    //Migrated functions
    public void incrementButtonA() {
        buttonACount++;
        totalCount++;
        eventHistory.add("1");
    }

    public void incrementButtonB() {
        buttonBCount++;
        totalCount++;
        eventHistory.add("2");
    }

    public void incrementButtonC() {
        buttonCCount++;
        totalCount++;
        eventHistory.add("3");
    }

    public void reset() {
        buttonACount = 0;
        buttonBCount = 0; 
        buttonCCount = 0;
        totalCount = 0;
        eventHistory.clear();
    }

    // For SharedPreferences storage - convert event history to CSV string
    public String getEventHistoryAsString() {
        if (eventHistory.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < eventHistory.size(); i++) {
            sb.append(eventHistory.get(i));
            if (i < eventHistory.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    // For SharedPreferences retrieval - convert CSV string to event history
    public void setEventHistoryFromString(String csvString) {
        eventHistory.clear();
        if (csvString != null && !csvString.trim().isEmpty()) {
            String[] events = csvString.split(",");
            eventHistory.addAll(Arrays.asList(events));
        }
    }

    // Setters for loading the saved data
    //Generate with INtellij functions
    public void setButtonACount(int buttonACount) {
        this.buttonACount = buttonACount;
    }

    public void setButtonBCount(int buttonBCount) {
        this.buttonBCount = buttonBCount;
    }

    public void setButtonCCount(int buttonCCount) {
        this.buttonCCount = buttonCCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setEventHistory(ArrayList<String> eventHistory) {
        this.eventHistory = (eventHistory != null) ? eventHistory : new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CounterData{" +
                "buttonACount=" + buttonACount +
                ", buttonBCount=" + buttonBCount +
                ", buttonCCount=" + buttonCCount +
                ", totalCount=" + totalCount +
                ", eventHistory=" + eventHistory +
                '}';
    }
}
