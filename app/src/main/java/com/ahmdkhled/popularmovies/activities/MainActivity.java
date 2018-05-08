package com.ahmdkhled.popularmovies.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.adapters.MovPagerAdapter;

public class MainActivity extends AppCompatActivity  {



    ViewPager viewPager;
    TabLayout tabLayout;

    LinearLayout main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager=findViewById(R.id.pager);
        tabLayout=findViewById(R.id.tab_layout);
        main=findViewById(R.id.main_activity);

        getSupportActionBar().setElevation(0);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new MovPagerAdapter(getSupportFragmentManager()));

    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
