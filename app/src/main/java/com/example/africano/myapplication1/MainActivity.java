package com.example.africano.myapplication1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Recycler_adapter.ClickCallback {

    private  boolean x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = findViewById(R.id.movie_detail) != null;

    }

    @Override
    public void onMovieCardClicked(long id) {


        Bundle b = new Bundle();
        b.putLong("id", id);

        if (x)
        {
            DetailFrag detailFrag = new DetailFrag();
            detailFrag.setArguments(b);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail, detailFrag)
                    .commit();
        }

        else {
            Intent i = new Intent(this, DetailOfMovie.class);
            i.putExtra("id", id);
            startActivity(i);
        }
    }

}
