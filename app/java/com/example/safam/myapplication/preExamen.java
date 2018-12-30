package com.example.safam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.safam.myapplication.Login.getUsername;

public class preExamen extends AppCompatActivity {

    Button btn;
    TextView conseil;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_examen);

        name="Bienvenue  "+getUsername()+"\n"+"Afin d'éviter l'interruption brusque de votre examen, on vous conseille de :\n" +
                "- Vérifier le niveau de batterie.\n" +
                "- Vérifier que le mode avion est activé.";

        btn = findViewById(R.id.start);
        conseil= findViewById(R.id.view) ;
        conseil.setText(name);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(preExamen.this,Examen.class);
                startActivity(intent);

            }
        });

    }

    public void onBackPressed() {
        Intent intent1= new Intent(preExamen.this,Choix_CQE.class);
        startActivity(intent1);
    }
}
