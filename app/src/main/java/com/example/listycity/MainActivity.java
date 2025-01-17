package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Declare UI components
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;
    Button addButton, deleteButton;
    int selectedPosition = -1; // Track selected city for deletion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);

        // Predefined list of cities
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        // Adapter setup
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // Handle selecting a city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            Toast.makeText(MainActivity.this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
        });

        // Add city button logic
        addButton.setOnClickListener(v -> {
            String newCity = cityInput.getText().toString().trim();
            if (!newCity.isEmpty() && !dataList.contains(newCity)) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged(); // Update ListView
                cityInput.setText("");  // Clear input field
                cityList.smoothScrollToPosition(dataList.size() - 1); // Scroll to new item
            } else {
                Toast.makeText(MainActivity.this, "Invalid or duplicate city", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete city button logic
        deleteButton.setOnClickListener(v -> {
            if (selectedPosition != -1) { // Ensure a city is selected
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                selectedPosition = -1; // Reset selection
                Toast.makeText(MainActivity.this, "City deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "No city selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
