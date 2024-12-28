package com.example.gestionbib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener */{
    private Button btnAjout;
    private Button btnSuppression;
    private Button btnModification;
    private Button btnConsultation;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        initialiser();
        ecouteurs();
    }
    private void initialiser() {
        btnAjout = findViewById(R.id.btnAjout);
        btnSuppression = findViewById(R.id.btnSuppression);
        btnModification = findViewById(R.id.btnModification);
        btnConsultation = findViewById(R.id.btnConsultation);
    }

    private void ecouteurs() {

        btnAjout.setOnClickListener(view -> ajouter());
        btnConsultation.setOnClickListener(view -> consulter());
        btnModification.setOnClickListener(view -> modifier());
        btnSuppression.setOnClickListener(view -> supprimer());
    }



    /*@Override
    public void onClick(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.btnAjout:
                i = new Intent(MainActivity.this, Ajout.class);
                break;
            case R.id.btnConsultation:
                i = new Intent(MainActivity.this, Consultation.class);
                break;
            case R.id.btnModification:
                i = new Intent(MainActivity.this, Modification.class);
                break;
            case R.id.btnSuppression:
                i = new Intent(MainActivity.this, Suppression.class);
                break;
        }
        startActivity(i);

    }*/

    ActivityResultLauncher<Intent> ActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                }
            });

    private void ajouter() {
        Intent i=new Intent(this, Ajout.class);
        ActivityLauncher.launch(i);
    }
    private void consulter() {
        Intent i=new Intent(this, Consultation.class);
        ActivityLauncher.launch(i);
    }
    private void modifier() {
        Intent i=new Intent(this, Modification.class);
        ActivityLauncher.launch(i);
    }
    private void supprimer() {
        Intent i=new Intent(this, Suppression.class);
        ActivityLauncher.launch(i);
    }
}