package com.example.safam.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Matieres extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matieres);
        btn = (Button) findViewById(R.id.finance);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(Matieres.this,Choix_CQE.class);
                startActivity(intent2);

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent1= new Intent(this,MainActivity.class);
        startActivity(intent1);
    }
}
