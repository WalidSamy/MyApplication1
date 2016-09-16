package com.example.africano.myapplication1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class DetailOfMovie extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
//
//        DetailFrag dm =new DetailFrag();
//        getSupportFragmentManager().beginTransaction().add(R.id.details, dm).commit();

        Bundle b = new Bundle();
        b.putLong("id" , getIntent().getExtras().getLong("id"));
        DetailFrag detailFrag = new DetailFrag();
        detailFrag.setArguments(b);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movie_detail, detailFrag)
                .commit();

    }

}
