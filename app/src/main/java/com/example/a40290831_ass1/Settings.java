package com.example.a40290831_ass1;

/**
 * ===== MVC ARCHITECTURE - MODEL =====
 * 
 * Settings data model that holds application configuration.
 * Represents the data structure for user preferences and settings.
 * 
 * Responsibilities:
 * - Store counter names (counter1Name, counter2Name, counter3Name)
 * - Store maximum count limit (maxCount)
 * - Provide validation methods for data integrity
 * - Offer getters/setters for data access
 * 
 * Used by:
 * - Controller: CounterController.java (business logic)
 * - Controller: SharedPreferenceHelper.java (data persistence)
 * - Views: MainActivity.java, SettingsActivity.java, DataActivity.java
 */
public class Settings {
    private String counter1Name;
    private String counter2Name;
    private String counter3Name;
    private int maxCount;

//constructors for different inputs
    public Settings() { }
    
    public Settings(String c1, String c2, String c3) {
        this.counter1Name = c1;
        this.counter2Name = c2;
        this.counter3Name = c3;
        this.maxCount = 0;
    }
    
    public Settings(String c1, String c2, String c3, int maxCount) {
        this.counter1Name = c1;
        this.counter2Name = c2;
        this.counter3Name = c3;
        this.maxCount = maxCount;
    }

    //getters and setters
    public String getCounter1Name() {
        return counter1Name;
    }
    public void setCounter1Name(String counter1Name) {
        this.counter1Name = counter1Name;
    }

    public String getCounter2Name() {
        return counter2Name;
    }
    public void setCounter2Name(String counter2Name) {
        this.counter2Name = counter2Name;
    }

    public String getCounter3Name() {
        return counter3Name;
    }
    public void setCounter3Name(String counter3Name) {
        this.counter3Name = counter3Name;
    }

    public int getMaxCount() {
        return maxCount;
    }
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    // --- Validation Methods ---
    public boolean isValid() {
        return counter1Name != null && !counter1Name.trim().isEmpty() &&
               counter2Name != null && !counter2Name.trim().isEmpty() &&
               counter3Name != null && !counter3Name.trim().isEmpty() &&
               maxCount > 0;
    }

    public boolean areNamesSet() {
        return counter1Name != null && counter2Name != null && counter3Name != null;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "counter1Name='" + counter1Name + '\'' +
                ", counter2Name='" + counter2Name + '\'' +
                ", counter3Name='" + counter3Name + '\'' +
                ", maxCount=" + maxCount +
                '}';
    }
}
