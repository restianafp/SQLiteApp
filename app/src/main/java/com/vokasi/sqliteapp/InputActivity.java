package com.vokasi.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {
    EditText editNama;
    EditText editAlamat;
    Button buttonSubmit;
    Databaseserver databaseserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        editAlamat = findViewById(R.id.edit_alamat);
        editNama = findViewById(R.id.edit_nama);
        buttonSubmit= findViewById(R.id.tombolSubmit);
        databaseserver= new Databaseserver(this);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
    }

    private void submitData(){
        if (editNama.getText().toString().length()>0 &&
        editAlamat.getText().toString().length()>0){
            databaseserver.addStudent(editNama.getText().toString(),
                    editAlamat.getText().toString());
            onBackPressed();
        }
    }
}