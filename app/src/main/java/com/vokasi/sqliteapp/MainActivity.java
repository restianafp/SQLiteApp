package com.vokasi.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ImageButton buttonAdd;
    Databaseserver databaseserver;
    ArrayList<Map<String,String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        buttonAdd = findViewById(R.id.buttonAdd);
        databaseserver = new Databaseserver(this);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int id = Integer.parseInt(arrayList.get(i).get("id"));
                return true;
            }
        });
    }
    private void showConfirm(final int id){
        new AlertDialog.Builder(this)
                .setTitle("hapus data")
                .setMessage("Apakah anda yakin ingin menghapus data ini?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        deleteData();
                    }
                })
                .setNegativeButton("Tidak",null)
                .show();

    }
    
    private void deleteData(int id){
        databaseserver.delete(id);
        arrayList.clear();
        loadData();
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        arrayList= databaseserver.getAllStudents();
        SimpleAdapter simpleAdapter = new SimpleAdapter(this.arrayList,
                android.R.layout.simple_list_item_2, new String[]{"nama","alamat"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }
}