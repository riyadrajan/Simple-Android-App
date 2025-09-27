package com.example.a40290831_ass1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * ===== MVC ARCHITECTURE - VIEW =====
 * 
 * Main Activity (View) that handles the primary user interface.
 * Focuses purely on UI interactions and display logic.
 * 
 * Responsibilities:
 * - Display counter buttons with custom names
 * - Show total count
 * - Handle button click events
 * - Navigate to Settings and Data activities
 * - Update UI when data changes
 * 
 * MVC Interactions:
 * - Uses CounterController for all business logic
 * - No direct access to Models or SharedPreferences
 * - Refreshes data via controller when resuming from other activities
 */
public class MainActivity extends AppCompatActivity {

    // UI Components
    protected TextView showCount;
    protected Button settings;
    protected Button data;
    protected Button buttonA;
    protected Button buttonB;
    protected Button buttonC;
    String name1, name2, name3;
    
    // MVC Controller
    private CounterController controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        // Initialize MVC Controller
        controller = new CounterController(this);
        setupUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //used to update/initialize components
    @Override
    protected void onResume() {
        super.onResume();

        // IMPORTANT: Refresh controller data in case it was changed in SettingsActivity
        // This ensures button names and counts update immediately when returning from settings
        controller.refreshData();
        
        // Check if settings are configured via controller
        if (!controller.areNamesSet()) {
            goToSettingsActivity();
        } else {
            updateUI();
            setupButtonListeners();
        }
    }

//Updates the UI with current data from Controller
    private void updateUI() {
        Settings settingsObj = controller.getSettings(); //returns settings obj
        CounterData counterData = controller.getCounterData();
        
        // Update button texts with event names
        buttonA.setText(settingsObj.getCounter1Name());
        buttonB.setText(settingsObj.getCounter2Name());
        buttonC.setText(settingsObj.getCounter3Name());
        
        // Update total count
        showCount.setText(controller.getFormattedTotalCount());
    }

//Settings up button click functionality (increments) using the controller
    private void setupButtonListeners() {
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.incrementButtonA()) {
                    showCount.setText(controller.getFormattedTotalCount());
                }
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.incrementButtonB()) {
                    showCount.setText(controller.getFormattedTotalCount());
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (controller.incrementButtonC()) {
                    showCount.setText(controller.getFormattedTotalCount());
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupUI() {
        settings = findViewById(R.id.settingsButton);
        data = findViewById(R.id.showCountButton);
        buttonA = findViewById(R.id.button1);
        buttonB = findViewById(R.id.button2);
        buttonC = findViewById(R.id.button3);
        showCount = findViewById(R.id.textCount);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSettingsActivity();
            }
        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDataActivity();
            }
        });
    }

    private void goToSettingsActivity() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
    private void goToDataActivity() {
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        // In MVC pattern, DataActivity will get data directly from controller
        // No need to pass data via intent extras anymore
        startActivity(intent);
    }

}
