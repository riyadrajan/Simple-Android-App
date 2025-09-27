package com.example.a40290831_ass1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

//need access to totalCount (MainActivity)

public class DataActivity extends AppCompatActivity {

    protected TextView textA;
    protected TextView textB;
    protected TextView textC;
    protected TextView textTotal;
    protected ListView showEvents;

    int aCount, bCount, cCount, total;
    String name1, name2, name3, totalStr;

    boolean toggleMode = true;
    ArrayList<String> eventHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Toolbar setup
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data Activity");
        setupUI();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        // if true show Event names, else show counter 1, counter 2, ...
        if (id == R.id.action_toggle && toggleMode == true) {
            toggleToCounter(toggleMode);
            toggleMode = false;
        } else {
            toggleToCounter(toggleMode);
            toggleMode = true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setCounts(String A, String B, String C, String totalStr) {
        if (aCount == 1){
            A = A + aCount + " event";
        } else{
            A = A + aCount + " events";
        }

        if (bCount == 1){
            B = B + bCount + " event";
        } else {
            B = B + bCount + " events";
        }

        if (cCount == 1){
            C = C + cCount + " event";
        } else {
            C = C + cCount + " events";
        }

        textA.setText(A);
        textB.setText(B);
        textC.setText(C);
        textTotal.setText(totalStr);
    }

    public void toggleToCounter(boolean toggle) {
        if (toggle) {
            setCounts("Counter 1: ", "Counter 2: ", "Counter 3: ", totalStr);
            updateList(false);
        } else {
            setCounts(name1 + ": ", name2 + ":  ", name3+ ": ", totalStr);
            updateList(true);
        }

    }

    public void updateList(boolean showNames) {
        ArrayList<String> displayList = new ArrayList<>();

        for (String event : eventHistory) {
            if (showNames) {
                if (event.equals("1")) displayList.add(name1);
                else if (event.equals("2")) displayList.add(name2);
                else if (event.equals("3")) displayList.add(name3);
            } else {
                displayList.add(String.valueOf(event));
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        showEvents.setAdapter(adapter);
    }


    private void setupUI() {
        textA = findViewById(R.id.ACount);
        textB = findViewById(R.id.BCount);
        textC = findViewById(R.id.CCount);
        textTotal = findViewById(R.id.totalCount);
        showEvents = findViewById(R.id.showEvents);

        SharedPreferenceHelper helper = new SharedPreferenceHelper(this);
        name1 = helper.getCounter1Name();
        name2 = helper.getCounter2Name();
        name3 = helper.getCounter3Name();
        total = helper.getMaxCount();

        //Retrieve intent values
        aCount = getIntent().getIntExtra("buttonA_count", 0);
        bCount = getIntent().getIntExtra("buttonB_count", 0);
        cCount = getIntent().getIntExtra("buttonC_count", 0);
        total = getIntent().getIntExtra("total_count", 0);
        eventHistory = getIntent().getStringArrayListExtra("event_history");
        if (eventHistory == null) {
            eventHistory = new ArrayList<>(); // fallback to empty list
        }
        totalStr = "Total events: " + total;
        // Show counts immediately
        toggleToCounter(false);
    }
}
