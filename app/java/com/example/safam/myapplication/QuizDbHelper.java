package com.example.safam.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.safam.myapplication.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by safam on 22/03/2018.
 */

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myquiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Quel est le type d'ordre le plus utilisé ?",
                                    "L'ordre à cours limité", "L'ordre du marché", "L'ordre d'application", 1);
        addQuestion(q1);


        Question q2 = new Question("Quelle est l'unité de poids utilisée pour la cotation de l'or ?",
                                    "Le gallon", "L'once", "Le gramme", 2);
        addQuestion(q2);


        Question q3 = new Question("Quel est le principal indice sectoriel de la Bourse de Tunis ",
                                    "TUNPETROL", "TUNCHIM", "TUNFIN", 3);
        addQuestion(q3);


        Question q4 = new Question("Quel est le seuil maximal de variation par séance des cotations en Tunisie ?",
                                    "± 6%", "± 3%", "Pas de seuil", 1);
        addQuestion(q4);


        Question q5 = new Question("En quelle année a été créé la Bourse de Tunis ?",
                                    "1969", "1995", "1980", 1);
        addQuestion(q5);


        Question q6 = new Question("Jusqu'à quelle heure sont cotées les valeurs les moins liquides da la Bourse de Tunis ?",
                                    "14h05", "13H05", "15h05", 2);
        addQuestion(q6);


        Question q7 = new Question("Comment s'appelle une option de vente ?",
                                    "Un ask", "Un put", "Un call", 3);
        addQuestion(q7);


        Question q8 = new Question("Combien y'a-t-il de marchés à la Bourse de Tunis ?",
                                    "3", "2", "1", 2);
        addQuestion(q8);


        Question q9 = new Question("Que se passe t'il lors d'une division du nominal ?",
                                    "On perd de l'argent", "On gagne de l'agrent", "L'opération est neutre", 3);
        addQuestion(q9);


        Question q10 = new Question("La négociation de 14H05 à 14H10 se déroule au :",
                "cours le plus haut", "cours le plus bas", "cours de clôture", 3);
        addQuestion(q10);


        Question q11 = new Question("La phase de préouverture de la Bourse de Tunis est de :",
                "9H à 10H", "9H30 à 10H", "8H30 à 10H", 1);
        addQuestion(q11);


        Question q12 = new Question("Que permet une distribution d'action gratuite ? ?",
                "De verser un dividende exceptionnel", "D'augmenter le capital de la société ", "D' améliorer les marges de la société", 2);
        addQuestion(q12);


        Question q13 = new Question("Quel est le moyen traditionnel utilisé pour combattre l'inflation ?",
                "Distribuer un dividende", "Relever les taux d'intérêts", "Baisser les taux d'intérêts", 2);
        addQuestion(q13);


        Question q14 = new Question("Combien y'a-t-il de modes de cotation en Tunisie ?",
                "3", "2", "1", 2);
        addQuestion(q14);


        Question q15 = new Question("Qu'est ce que le TUNFIN ?",
                "Un indice sectoriel", "Un indicateur technique", "Une société", 1);
        addQuestion(q15);


        Question q16 = new Question("Jusqu'à quelle heure sont cotées les valeurs les moins liquides da la Bourse de Tunis ?",
                "15H05.", "14H05", "13H05", 3);
        addQuestion(q16);


        Question q17 = new Question("La commission des intermédiaires en bourse varie entre :",
                " 1 à 2 %", "0,4 à 0,8 %", "0,2 à 0,5 %", 2);
        addQuestion(q17);


    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


    public List<Question> getSomeQuestions( int max) {
        int i=0;
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst() ) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
                i++;
            } while (c.moveToNext() && i<max);
        }

        c.close();
        return questionList;
    }

}
