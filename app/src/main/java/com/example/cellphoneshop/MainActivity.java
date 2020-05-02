package com.example.cellphoneshop;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnUpdate, btnDelete,
            btnReset, btnToSearch;
    ListView lvPhones;
    EditText editCode, editName, editPrice;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnReset = findViewById(R.id.btnReset);
        btnToSearch = findViewById(R.id.btnToSearch);

        lvPhones = findViewById(R.id.lvPhones);

        editCode = findViewById(R.id.editCode);
        editName = findViewById(R.id.editName);
        editPrice = findViewById(R.id.editPrice);

        setFormStatus(true);
        PhoneDAL.readFromFile(getApplicationContext());
        final PhoneAdapter phoneAdapter = new PhoneAdapter(getApplicationContext(), PhoneDAL.phones);
        lvPhones.setAdapter(phoneAdapter);
        lvPhones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setFormStatus(false);
                editCode.setText(PhoneDAL.phones.get(i).getCode());
                editName.setText(PhoneDAL.phones.get(i).getName());
                editPrice.setText(PhoneDAL .phones.get(i).getPrice()+"");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editCode.getText().toString().trim();
                for(Phone p : PhoneDAL.phones) {
                    if(p.getCode().equals(code)) {
                        Toast.makeText(getApplicationContext(), "Code ["+code+"] existed.",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                if(!validateForm())
                    return;

                String name = editName.getText().toString().trim();
                int price = Integer.valueOf(editPrice.getText().toString().trim());
                PhoneDAL.phones.add(new Phone(code, name, price));
                PhoneDAL .saveToFile(getApplicationContext());
                phoneAdapter.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editCode.getText().toString().trim();
                PhoneDAL.phones.remove(PhoneDAL.find(code));
                PhoneDAL.saveToFile(getApplicationContext());
                phoneAdapter.notifyDataSetChanged();

                resetForm();
                setFormStatus(true);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateForm())
                    return;

                String code = editCode.getText().toString().trim();
                String name = editName.getText().toString().trim();
                int price = Integer.valueOf(editPrice.getText().toString().trim());

                Phone selected = PhoneDAL.find(code);
                selected.setName(name);
                selected.setPrice(price);

                PhoneDAL.saveToFile(getApplicationContext());
                phoneAdapter.notifyDataSetChanged();

                resetForm();
                setFormStatus(true);
            }
        });

        btnToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
    }

    private void resetForm() {
        editCode.setText("");
        editName.setText("");
        editPrice.setText("");
    }

    private void setFormStatus(boolean status) {
        editCode.setEnabled(status);
        btnAdd.setEnabled(status);
        btnDelete.setEnabled(!status);
        btnUpdate.setEnabled(!status);
    }

    private boolean validateForm() {
        String code = editCode.getText().toString().trim();
        if(code.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Code can not be blank.", Toast.LENGTH_LONG).show();
            return false;
        }

        String name = editName.getText().toString().trim();
        if(name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Name can not be blank.", Toast.LENGTH_LONG).show();
            return false;
        }

        String price = editPrice.getText().toString().trim();
        if(price.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Price can not be blank.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
    }

