package com.example.sportssuiveee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

public class HistoriqueActivity extends AppCompatActivity {

    private ListView listActivities;
    private EditText etNewActivity;
    private Button btnAddActivity;
    private DatePicker datePicker;
    private TextView tvTitleHistory;

    private ArrayList<String> activitiesList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        // Initialiser les vues
        listActivities = findViewById(R.id.listActivities);
        etNewActivity = findViewById(R.id.etNewActivity);
        btnAddActivity = findViewById(R.id.btnAddActivity);
        datePicker = findViewById(R.id.datePicker);
        tvTitleHistory = findViewById(R.id.tvTitleHistory);

        // Initialiser la liste
        activitiesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activitiesList);
        listActivities.setAdapter(adapter);

        // Ajouter quelques activités d'exemple
        activitiesList.add("Course à pied - 30min");
        activitiesList.add("Natation - 45min");
        adapter.notifyDataSetChanged();

        // Bouton pour ajouter une activité
        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterActivite();
            }
        });

        // Clic long pour supprimer
        listActivities.setOnItemLongClickListener((parent, view, position, id) -> {
            String activity = activitiesList.get(position);
            activitiesList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(HistoriqueActivity.this, "Activité supprimée: " + activity, Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void ajouterActivite() {
        String nouvelleActivite = etNewActivity.getText().toString().trim();

        if (nouvelleActivite.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une activité", Toast.LENGTH_SHORT).show();
            return;
        }

        // Récupérer la date
        int jour = datePicker.getDayOfMonth();
        int mois = datePicker.getMonth() + 1; // Les mois commencent à 0
        int annee = datePicker.getYear();

        String date = jour + "/" + mois + "/" + annee;
        String activiteAvecDate = nouvelleActivite + " - " + date;

        // Ajouter à la liste
        activitiesList.add(activiteAvecDate);
        adapter.notifyDataSetChanged();

        // Vider le champ
        etNewActivity.setText("");
        Toast.makeText(this, "Activité ajoutée !", Toast.LENGTH_SHORT).show();
    }


}