package com.example.safam.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class test extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;
    private Button q1,q2,q3,q4,q5,q6,q7,q8,q9;


    private ColorStateList textColorDefaultRb;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score, rep=0;
    private int tab[]={0,0,0,0,0,0,0,0,0};

    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.next);
        q1= findViewById(R.id.q1);
        q2= findViewById(R.id.q2);
        q3= findViewById(R.id.q3);
        q4= findViewById(R.id.q4);
        q5= findViewById(R.id.q5);
        q6= findViewById(R.id.q6);
        q7= findViewById(R.id.q7);
        q8= findViewById(R.id.q8);
        q9= findViewById(R.id.q9);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();

        //Permuter de façon aléatoire les éléments de la liste.
        Collections.shuffle(questionList);

        showNextQuestion();
        startCountDown();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                    tab[questionCounter]=checkRadio();
                    buttonColor();
                    checkScore();
                }
                showNextQuestion();
            }
        });



    }




    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            buttonConfirmNext.setText("Suivante");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            if (questionCounter == questionCountTotal)buttonConfirmNext.setText("Valider");

        } else if((buttonConfirmNext.getText().equals("Valider"))&&(rep<questionCountTotal)){
            AlertDialog.Builder builder = new AlertDialog.Builder(test.this);
            builder.setTitle("Alert")
                    .setMessage("Vous n'avez pas répondu à toutes les questions.\n" + "Voulez vous quand même valider?")
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            showResult();
                            countDownTimer.cancel();
                        }
                    })
                    .setNegativeButton("Non", null)
                    .setIcon(R.drawable.alert);
            AlertDialog dialog = builder.create();
            dialog.show();

        }else {
            showResult();
            countDownTimer.cancel();}
    }



    private void checkScore(){
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        rep++;
        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
        }
    }

    private int checkRadio(){
        if (rb1.isChecked()) {return 1;}
        if (rb2.isChecked()) {return 2;}
        if (rb3.isChecked()) {return 3;}
        else return 0;

    }



    private void buttonColor(){

        switch (questionCounter)
        {
            case 1:
                q1.setBackgroundColor(Color.rgb(163, 231, 249));
                break;

            case 2:
                q2.setBackgroundColor(Color.rgb(163, 231, 249));
                break;

            case 3:
                q3.setBackgroundColor(Color.rgb(163, 231, 249));
                break;

            case 4:
                q4.setBackgroundColor(Color.rgb(163, 231, 249));
                break;

            case 5:
                q5.setBackgroundColor(Color.rgb(163, 231, 249));
                break;

            case 6:
                q6.setBackgroundColor(Color.rgb(163, 231, 249));
                break;

            case 7:
                q7.setBackgroundColor(Color.rgb(163, 231, 249));
                break;

            case 8:
                q8.setBackgroundColor(Color.rgb(163, 231, 249));
                break;

            case 9:
                q9.setBackgroundColor(Color.rgb(163, 231, 249));
                break;

            default:
                break;
        }

    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                AlertDialog.Builder builder = new AlertDialog.Builder(test.this);
                builder.setTitle("Alert")
                        .setMessage("Le temps est écoulé")
                        .setPositiveButton("Résultat!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showResult();
                            }
                        })
                        .setIcon(R.drawable.alert);
                AlertDialog dialog = builder.create();
                dialog.show();
                updateCountDownText();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }




    private void finishQuiz() {
        finish();
    }



    public void showResult(){


        String good =String.valueOf(score);

        String bad =String.valueOf(questionCountTotal-score);
        String note="";
        if (score> (questionCountTotal-score)) {
            note = "Bravo!!";
        } else note = "Vous pouvez faire mieux!";

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.custom_dialog, null);

        TextView tv1 = customView.findViewById(R.id.good);
        tv1.setText("Réponses correctes : "+good+"\n");
        TextView tv2 = customView.findViewById(R.id.bad);
        tv2.setText("Réponses incorrectes : "+bad+ "\n");
        TextView tv3 = customView.findViewById(R.id.note);
        tv3.setText(note+ "\n");

        alert.setView(customView);

        alert.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishQuiz();
            }
        });
        alert.create().show();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
