package com.example.sportssuiveee;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.Button;
import android.view.View;

import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView l1;
    private EditText t1;
    private DatePicker date;
    private Button btn;
    private ArrayList<String> activities = new ArrayList<>();
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pagemain);
        initViews();
        Intent myLocalIntent = getIntent();
        Bundle myBundle = myLocalIntent.getExtras();


        setupListeners();
    }

    private void initViews() {
        l1 = findViewById(R.id.listActivities);
        t1 = findViewById(R.id.etNewActivity);
        date = findViewById(R.id.datePicker);
        btn = findViewById(R.id.btnAddActivity);
    }
    private void setupListeners() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterNouvelleActivite();
            }
        });
    }
    private void ajouterNouvelleActivite() {
        String activity = getTextActivite();
        String date = getDateSelectionnee();
        ajouterActiviteALaListe(activity, date);
        rafraichirAffichage();
    }
    private String getTextActivite() {
        return t1.getText().toString();
    }
    private String getDateSelectionnee() {
        int day =date.getDayOfMonth();
        int month = date.getMonth();
        int year = date.getYear();
        String s = day + "/" + month + "/" + year;
        return s;
    }
    private void ajouterActiviteALaListe(String texte , String date){
        activities.add(texte+ "-"+ date);
    }
    private void rafraichirAffichage(){
        adapter.notifyDataSetChanged();
        t1.setText("");
    }
}
