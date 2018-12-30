package com.example.safam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choix_CQE extends AppCompatActivity {

    Button btc,btq,bte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix__cqe);


        btc = (Button) findViewById(R.id.cours);
        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3= new Intent(Choix_CQE.this,Cours.class);
                startActivity(intent3);

            }
        });

        btq = (Button) findViewById(R.id.quiz);
        btq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3= new Intent(Choix_CQE.this,Quiz.class);
                startActivity(intent3);

            }
        });

        bte = (Button) findViewById(R.id.exam);
        bte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3= new Intent(Choix_CQE.this,Login.class);
                startActivity(intent3);

            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent1= new Intent(Choix_CQE.this,Matieres.class);
        startActivity(intent1);
    }
}
