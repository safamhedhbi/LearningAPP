package com.example.safam.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.barteksc.pdfviewer.PDFView;

public class Chap1 extends Fragment {

    View view;
    PDFView pdfView;
    Context context;

    public Chap1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_chap1,container,false);
        pdfView = (PDFView)view.findViewById(R.id.pdfView);
       /* CourDb dbCours = new CourDb(context);
        String path = dbCours.getthePath(1);*/
        pdfView.fromAsset("ch1.pdf").load();
        return view;
    }


}
