package com.example.a40290831_ass1;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferenceHelper extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences("settings_prefs", Context.MODE_PRIVATE);
    }

    // -------- Counter 1 --------
    public void setCounter1Name(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("counter1Name", name);
        editor.apply();
    }

    public String getCounter1Name() {
        return sharedPreferences.getString("counter1Name", null);
    }

    // -------- Counter 2 --------
    public void setCounter2Name(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("counter2Name", name);
        editor.apply();
    }

    public String getCounter2Name() {
        return sharedPreferences.getString("counter2Name", null);
    }

    // -------- Counter 3 --------
    public void setCounter3Name(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("counter3Name", name);
        editor.apply();
    }

    public String getCounter3Name() {
        return sharedPreferences.getString("counter3Name", null);
    }

    // -------- Max Count --------
    public void setMaxCount(int max) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("maxCount", max);
        editor.apply();
    }

    public int getMaxCount() {
        return sharedPreferences.getInt("maxCount", 0);
    }


}
