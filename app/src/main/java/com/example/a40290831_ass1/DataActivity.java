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

/**
 * ===== MVC ARCHITECTURE - VIEW =====
 * 
 * Data Activity (View) that handles data visualization and statistics display.
 * Focuses on displaying counter data, statistics, and event history.
 * 
 * Responsibilities:
 * - Display individual counter statistics
 * - Show total event count
 * - Display event history in ListView
 * - Handle toggle between custom names and counter numbers
 * - Provide action bar menu for display options
 * 
 * MVC Interactions:
 * - Uses CounterController for all data retrieval
 * - Gets formatted display strings from controller
 * - Uses controller's event history display methods
 * - No direct access to Models or SharedPreferences
 */
public class DataActivity extends AppCompatActivity {

    protected TextView textA;
    protected TextView textB;
    protected TextView textC;
    protected TextView textTotal;
    protected ListView showEvents;

    // MVC Controller
    private CounterController controller;
    
    // Toggle mode for display
    private boolean showNames = true;

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
        
        // Initialize MVC Controller
        controller = new CounterController(this);
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
        // Toggle between showing custom names and counter numbers
        if (id == R.id.action_toggle) {
            showNames = !showNames;
            updateDisplay();
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Update all display elements using controller data
     */
    private void updateDisplay() {
        // Update counter text displays
        textA.setText(controller.getFormattedCounterText(1, showNames));
        textB.setText(controller.getFormattedCounterText(2, showNames));
        textC.setText(controller.getFormattedCounterText(3, showNames));
        textTotal.setText("Total events: " + controller.getCounterData().getTotalCount());
        
        // Update event history list
        updateEventList();
    }

    /**
     * Update the ListView with event history
     */
    private void updateEventList() {
        String[] displayArray = controller.getEventHistoryDisplay(showNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayArray);
        showEvents.setAdapter(adapter);
    }


    private void setupUI() {
        textA = findViewById(R.id.ACount);
        textB = findViewById(R.id.BCount);
        textC = findViewById(R.id.CCount);
        textTotal = findViewById(R.id.totalCount);
        showEvents = findViewById(R.id.showEvents);

        // In MVC pattern, get data directly from controller instead of intent extras
        updateDisplay();
    }
}
