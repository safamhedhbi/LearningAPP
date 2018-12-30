package com.example.safam.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;


public class Chap3 extends Fragment {

    View view;
    Button btn;

    public Chap3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_chap3,container,false);
        btn=view.findViewById(R.id.buttonvid);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/channel/UCkSew_rtXDQAiTEWjpPYhcw/videos"));
                startActivity(intent);
            }
        });
        return view;
    }
}
