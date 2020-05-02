package com.example.cellphoneshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    Spinner spName;
    Button btnBack;
    ListView lvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        spName = findViewById(R.id.spName);
        btnBack = findViewById(R.id.btnBack);
        lvResult = findViewById(R.id.lvResult);

        List<String> names = new ArrayList<>();
        for(Phone p : PhoneDAL.phones) {
            if(!names.contains(p.getName()))
                names.add(p.getName());
        }
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, names);
        spName.setAdapter(namesAdapter);

        spName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<Phone> resultList = new ArrayList<>();
                for(Phone p : PhoneDAL.phones) {
                    if(p.getName().equals(spName.getSelectedItem().toString()))
                        resultList.add(p);
                }
                lvResult.setAdapter(new PhoneAdapter(getApplicationContext(), resultList));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
            }
        });
    }
    }

