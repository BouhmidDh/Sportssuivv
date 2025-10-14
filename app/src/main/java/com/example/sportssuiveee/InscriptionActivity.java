package com.example.sportssuiveee;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InscriptionActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscriptionn);

        initViews();
        setupListeners();
    }

    private void initViews() {
        etName = findViewById(R.id.etNom);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnInscrire);
    }

    private void setupListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegistration();
            }
        });
    }

    private void handleRegistration() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showToast("Veuillez remplir tous les champs");
            return;
        }

        if (!isValidEmail(email)) {
            showToast("Veuillez entrer un email valide");
            return;
        }

        if (password.length() < 6) {
            showToast("Le mot de passe doit contenir au moins 6 caractères");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showToast("Les mots de passe ne correspondent pas");
            return;
        }

        // Simulation d'inscription réussie
        showToast("Inscription réussie pour " + name);

        // Retour à l'activité de connexion
        Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
        //intent.putExtra("NEW_USER_EMAIL", email);
        Bundle bundle = new Bundle();
        bundle.putString("NEW_USER_NAME", name);
        bundle.putString("NEW_USER_PASSWORD", password);
        bundle.putString("NEW_USER_EMAIL", email);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        return email.matches(emailPattern);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onBackPressedDispatcher() {
//        super.getOnBackPressedDispatcher();
//        // Retour à l'écran de connexion
//        Intent intent = new Intent(this, ComposantGUIActivity.class);
//        startActivity(intent);
//        finish();
//    }
}
