package com.example.a40290831_ass1;

import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {
    protected EditText counter1Edit;
    protected EditText counter2Edit;
    protected EditText counter3Edit;
    protected EditText maxCountEdit;
    protected Button saveButton;
    
    // MVC Controller
    private CounterController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setttings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        getSupportActionBar().setTitle("Settings");
        
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
    protected void onResume() {
        super.onResume();
        loadCurrentSettings();
        enableDisplayMode();
    }

//Load Current Settings into fields
    private void loadCurrentSettings() {
        Settings settings = controller.getSettings();
        
        // Set current values if they exist
        if (settings.getCounter1Name() != null) {
            counter1Edit.setText(settings.getCounter1Name());
        }
        if (settings.getCounter2Name() != null) {
            counter2Edit.setText(settings.getCounter2Name());
        }
        if (settings.getCounter3Name() != null) {
            counter3Edit.setText(settings.getCounter3Name());
        }
        if (settings.getMaxCount() > 0) {
            maxCountEdit.setText(String.valueOf(settings.getMaxCount()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            // Switch to edit mode
            enableEditMode();
            return true;
        } else if (id == R.id.action_display) {
            // Switch to display mode
            enableDisplayMode();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void enableEditMode(){
        counter1Edit.setEnabled(true);
        counter2Edit.setEnabled(true);
        counter3Edit.setEnabled(true);
        maxCountEdit.setEnabled(true);
        saveButton.setEnabled(true);
    }

    private void enableDisplayMode(){
        counter1Edit.setEnabled(false);
        counter2Edit.setEnabled(false);
        counter3Edit.setEnabled(false);
        maxCountEdit.setEnabled(false);
    }

    private void setupUI() {
        counter1Edit = findViewById(R.id.editTextText);
        counter2Edit = findViewById(R.id.editTextText2);
        counter3Edit = findViewById(R.id.editTextText3);
        maxCountEdit = findViewById(R.id.editTextText4);
        
        // Apply input filters for validation
        setupInputFilters();

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
            }
        });
    }

    /**
     * Setup input filters based on the assignment hints
     */
    private void setupInputFilters() {
        // For counter names: only alphabetical characters (and no spaces)
        String alphabetsOnly = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        InputFilter alphabetFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                     android.text.Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (alphabetsOnly.indexOf(source.charAt(i)) == -1) {
                        return "";
                    }
                }
                return null;
            }
        };
        
        // Apply alphabet filter and max length to counter name fields
        int maxNameLength = 15;
        InputFilter[] nameFilters = {alphabetFilter, new InputFilter.LengthFilter(maxNameLength)};
        counter1Edit.setFilters(nameFilters);
        counter2Edit.setFilters(nameFilters);
        counter3Edit.setFilters(nameFilters);
        
        // For max count: numerical input with max length 3
        InputFilter[] countFilters = {new InputFilter.LengthFilter(3)};
        maxCountEdit.setFilters(countFilters);
    }

    /**
     * Validate and save settings using controller
     */
    private void saveSettings() {
        String counter1 = counter1Edit.getText().toString().trim();
        String counter2 = counter2Edit.getText().toString().trim();
        String counter3 = counter3Edit.getText().toString().trim();
        String maxCountText = maxCountEdit.getText().toString().trim();

        // Validate inputs
        if (!CounterController.isValidCounterName(counter1)) {
            Toast.makeText(this, "Counter 1 name must contain only letters", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!CounterController.isValidCounterName(counter2)) {
            Toast.makeText(this, "Counter 2 name must contain only letters", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!CounterController.isValidCounterName(counter3)) {
            Toast.makeText(this, "Counter 3 name must contain only letters", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!CounterController.isValidMaxCount(maxCountText)) {
            Toast.makeText(this, "Max count must be a positive number", Toast.LENGTH_SHORT).show();
            return;
        }

        // All validation passed, save using controller
        try {
            int maxCount = Integer.parseInt(maxCountText);
            controller.updateSettings(counter1, counter2, counter3, maxCount);
            Toast.makeText(getApplicationContext(), "Settings saved successfully", Toast.LENGTH_LONG).show();
            finish(); // return to MainActivity
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid max count format", Toast.LENGTH_SHORT).show();
        }
    }

}
