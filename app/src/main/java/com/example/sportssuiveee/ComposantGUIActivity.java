package com.example.sportssuiveee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ComposantGUIActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnConnect, btnInscrire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);

        initViews();
        setupListeners();
    }
    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnConnect = findViewById(R.id.btnConnect);
        btnInscrire = findViewById(R.id.btnInscrire);
    }

    private void setupListeners() {
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        btnInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvrir l'activité d'inscription
                Intent intent = new Intent(ComposantGUIActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showToast("Veuillez remplir tous les champs");
            return;
        }

        if (!isValidEmail(email)) {
            showToast("Veuillez entrer un email valide");
            return;
        }

        // Simulation de connexion réussie
        if (password.length() >= 6) {
            showToast("Connexion réussie !");

            // Ouvrir l'activité historique après connexion
            Intent intent = new Intent(ComposantGUIActivity.this, ComposantGUIActivity.class);
            intent.putExtra("USER_EMAIL", email);
            startActivity(intent);
            finish(); // Fermer l'activité de connexion
        } else {
            showToast("Mot de passe incorrect");
        }
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        return email.matches(emailPattern);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
