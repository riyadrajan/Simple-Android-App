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

public class MainActivity extends AppCompatActivity {

    protected TextView showCount;
    static int totalCount;
    protected Button settings;
    protected Button data;
    protected Button buttonA;
    protected Button buttonB;
    protected Button buttonC;



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
        setupUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //use this to update/initialize components
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferenceHelper helper = new SharedPreferenceHelper(MainActivity.this);

        String name1 = helper.getCounter1Name();
        String name2 = helper.getCounter2Name();
        String name3 = helper.getCounter3Name();

        if (name1 == null || name2 == null || name3 == null) {
            goToSettingsActivity();
        } else {
            buttonA.setText(name1);
            buttonB.setText(name2);
            buttonC.setText(name3);
        }

        //set functionality to the buttons (increase total count)
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalCount <= helper.getMaxCount()) {
                    totalCount += 1;
                    showCount.setText("Total Count: " + totalCount);
                }
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalCount <= helper.getMaxCount()) {
                    totalCount += 1;
                    showCount.setText("Total Count: " + totalCount);
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalCount <= helper.getMaxCount()) {
                    totalCount += 1;
                    showCount.setText("Total Count: " + totalCount);
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
    }

    private void goToSettingsActivity() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}
