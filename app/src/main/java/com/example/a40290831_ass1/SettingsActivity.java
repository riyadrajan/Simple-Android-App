package com.example.a40290831_ass1;

import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
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
        enableDisplayMode();
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


        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String counter1 = counter1Edit.getText().toString();
                String counter2 = counter2Edit.getText().toString();
                String counter3 = counter3Edit.getText().toString();
                String maxCountText = maxCountEdit.getText().toString();
                int maxCount = Integer.parseInt(maxCountText);


                SharedPreferenceHelper helper = new SharedPreferenceHelper(SettingsActivity.this);

                helper.setCounter1Name(counter1);
                helper.setCounter2Name(counter2);
                helper.setCounter3Name(counter3);
                helper.setMaxCount(maxCount);
                Toast.makeText(getApplicationContext(), "Names saved", Toast.LENGTH_LONG).show();
                finish(); // return to MainActivity
            }
        });
    }

}
