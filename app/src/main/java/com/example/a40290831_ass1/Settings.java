package com.example.a40290831_ass1;

public class Settings {
    private String counter1Name;
    private String counter2Name;
    private String counter3Name;

    // --- Constructors ---
    public Settings() { }  // Empty constructor
    public Settings(String c1, String c2, String c3) {
        this.counter1Name = c1;
        this.counter2Name = c2;
        this.counter3Name = c3;
    }

    // --- Getters and Setters ---
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
}
