package com.example.safam.myapplication;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class Login extends AppCompatActivity {

    Button show;
    private List<User> userList;
    private User currentUser;
    private int n;
    private EditText nameDisplay;
    private EditText passwordDisplay;
    Button bt;
    String save_name;
    String save_password;
    static String user_name="none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt = (Button) findViewById(R.id.login);
        nameDisplay = findViewById(R.id.name);
       passwordDisplay = findViewById(R.id.password);
        show = (Button) findViewById(R.id.show);
        show.setOnClickListener(new showOrHidePassword());
        show.setText("Afficher");

        LoginDbHelper dbHelper = new LoginDbHelper(this);
        userList = dbHelper.getAllUsers();
        n = userList.size();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logExam();
            }
        });

    }


    private void logExam(){
        save_name = nameDisplay.getText().toString();
        save_password = passwordDisplay.getText().toString();
        int verifier=9;
        int i;
        currentUser =userList.get(0);
       for(i=0;i<n;i++){
            currentUser =userList.get(i);
            if ((currentUser.getName().equals(save_name)&& (currentUser.getPassword().equals(save_password))))
            {
                verifier=0;
                user_name=save_name;
            }
        }

        if (verifier==0)
        {
            Intent intent3 = new Intent(Login.this, preExamen.class);
            startActivity(intent3);
        }else if(save_name.equals("")  || save_password.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setPositiveButton("OK",null)
                    .setMessage("Veuillez saisir vos données");
            AlertDialog dialog = builder.create();
            dialog.show();
            }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setPositiveButton("OK",null)
                    .setMessage("Veuillez vérifier vos données");
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        }

        static String getUsername(){
            return user_name;
        }

    class showOrHidePassword implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (show.getText().toString() == "Afficher") {
                passwordDisplay.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                show.setText("Cacher");

            } else {

                passwordDisplay.setTransformationMethod(PasswordTransformationMethod.getInstance());
                show.setText("Afficher");
            }
        }
    }


    }

