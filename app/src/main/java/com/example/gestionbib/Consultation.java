package com.example.gestionbib;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Consultation extends AppCompatActivity {

    private FirebaseFirestore db;
    private ListView lstLivre;
    private Button btnRetour;
    private ArrayAdapter<Livre> adpLivre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        initialiser();

        ecouteurs();
        remplir();
    }

    private void initialiser() {
        db = FirebaseFirestore.getInstance();

        lstLivre = findViewById(R.id.lstLivre);
        btnRetour = findViewById(R.id.btnRetour);

        adpLivre = new ArrayAdapter<Livre>(this, android.R.layout.simple_list_item_1);
        lstLivre.setAdapter(adpLivre);
    }

    private void ecouteurs() {
        btnRetour.setOnClickListener(view -> finish());
    }
    private void remplir() {
        db.collection("livres").whereGreaterThan("nbpage", 150)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getId();
                                String titre = document.getString("titre");
                                int nbpage = document.getDouble("nbpage").intValue();
                                Livre l = new Livre(id, titre, nbpage);
                                adpLivre.add(l);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Erreur lors de la récupération de la liste des livres", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}

