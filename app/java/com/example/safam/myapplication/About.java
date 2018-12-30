package com.example.safam.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class About extends AppCompatActivity {

    Button b1, b2;
    TextView text;
    String about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        b1= findViewById(R.id.bvmt);
        b2=findViewById(R.id.fb);
        text=findViewById(R.id.body);

        about="c’est une application éducative qui aide les étudiants en Génie Logiciel : "
        +"Option informatique financière à APPRENDRE et REVISER avec des COURS, QUIZ et EXAMEN "
        +"afin de leur procurer les meilleures conditions d’apprentissage pour réussir.";
        text.setText(about);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bvmt.com.tn"));
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/groups/346035015876558/"));
                startActivity(intent);
            }
        });


    }
}
