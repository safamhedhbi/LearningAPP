package com.example.safam.myapplication;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class Cours extends AppCompatActivity {

   private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);
       tablayout = (TabLayout) findViewById(R.id.tablayout_id);
        appBarLayout =(AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter =new ViewPagerAdapter((getSupportFragmentManager()));

        adapter.addFragment(new Chap1(),"Chapitre 1");
        adapter.addFragment(new Chap2(),"Chapitre 2");
        adapter.addFragment(new Chap3(),"Plus ...");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);


    }
}
