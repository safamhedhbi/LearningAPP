package com.example.safam.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.barteksc.pdfviewer.PDFView;


public class Chap2 extends Fragment {

    View view;
    PDFView pdfView;

    public Chap2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_chap2,container,false);
        pdfView = (PDFView)view.findViewById(R.id.pdfView);
        pdfView.fromAsset("ch2.pdf").load();
        return view;
    }
}
