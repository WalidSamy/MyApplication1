package com.example.africano.myapplication1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DetailFrag extends Fragment {

    RequestQueue requestQueue;
    long ID;
    TextView title1;
    String poster;
    String movieName;
    ImageView posterImage;
    ImageView panner;
    TextView relasedate;
    TextView vote_avg;
    TextView overview;
    Button favorite;
    RowMovieMethods rowMovieMethods;
    LinearLayout linearLayout;
    LinearLayout linearLayout1;
    Bundle b = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getActivity());

        b= getArguments();
        ID = b.getLong("id");
    }

    public DetailFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View V= inflater.inflate(R.layout.fragment_details, container, false);
        //ID = getActivity().getIntent().getExtras().getLong("id");

        posterImage = (ImageView) V.findViewById(R.id.image);
        panner = (ImageView) V.findViewById(R.id.big_poster);
        relasedate = (TextView) V.findViewById(R.id.release_date);
        overview = (TextView) V.findViewById(R.id.overview);
        vote_avg = (TextView) V.findViewById(R.id.vote_average);
        favorite = (Button) V.findViewById(R.id.fav);
        title1 = (TextView) V.findViewById(R.id.title1);

        linearLayout = (LinearLayout) V.findViewById(R.id.trailer_layout);
        linearLayout1 = (LinearLayout) V.findViewById(R.id.review_layout);


        rowMovieMethods = new RowMovieMethods(getActivity());

        final ArrayList<RowMovie> movie;
        movie = rowMovieMethods.getFav();

        for (int i =0 ; i<movie.size() ; i++)
        {
            if (ID == movie.get(i).getId())
            {
                favorite.setText("Favorited");
            }
        }
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean x = rowMovieMethods.checkIfExist(ID);
                if (x==true)
                {
                    rowMovieMethods.delete(ID);
                    favorite.setText("Make As Favorite");
                }
                else {
                    rowMovieMethods.insert(ID, movieName, poster);
                    favorite.setText("Favorited");
                }
            }
        });

        return V;

    }

    public void onResume()
    {
        super.onResume();
        sendRequest("http://api.themoviedb.org/3/movie/" + ID + "?api_key=77363e36dfe4d8d2f793a71be536ddae");
        sendTrailerRequest("http://api.themoviedb.org/3/movie/" + ID + "/videos?api_key=77363e36dfe4d8d2f793a71be536ddae");
        sendReviewRequest("http://api.themoviedb.org/3/movie/" + ID + "/reviews?api_key=77363e36dfe4d8d2f793a71be536ddae");
    }


    public void  sendRequest(String url){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                RowMovie row_movie = response(jsonObject);

                    Picasso.with(getActivity())
                            .load(Uri.parse("http://image.tmdb.org/t/p/w342" + row_movie.getPanner()))
                            .into(panner);
                    Picasso.with(getActivity())
                            .load(Uri.parse("http://image.tmdb.org/t/p/w342" + row_movie.getPoster())).into(posterImage);

                    title1.setText(row_movie.getTitle());
                    relasedate.setText(row_movie.getRelaseDate());
                    overview.setText(row_movie.getOverView());
                    vote_avg.setText(row_movie.getVoteAvg() + "");
                Log.d("moamen" , row_movie.getPanner().toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public RowMovie response (JSONObject jsonObject){

        if (jsonObject == null || jsonObject.length() ==0)
        {
            return null;
        }
        else {

            try {

                    long id = jsonObject.getLong("id");
                    movieName=jsonObject.getString("title");
                    poster= jsonObject.getString("poster_path");
                    String panner =jsonObject.getString("backdrop_path");
                    String relse_date =jsonObject.getString("release_date");
                    String summry =jsonObject.getString("overview");
                    double vote=jsonObject.getDouble("vote_average");
                    RowMovie row_movie = new RowMovie(id,poster,movieName,summry,panner,relse_date,vote);

                return row_movie;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }



    public void sendTrailerRequest(String mUrl)
    {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, mUrl, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject jsonObject) {

                if (jsonObject == null || jsonObject.length() == 0)
                {
                    return ;
                }
                else {
                    try {
                        JSONArray jsonArray = jsonObject.getJSONArray("results");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            final String key = jsonObject1.getString("key");
                            String name = jsonObject1.getString("name");

                            View v = LayoutInflater.from(getActivity()).inflate(R.layout.trailers_layout, null);

                            ImageView play = (ImageView) v.findViewById(R.id.play);
                            TextView ttitle = (TextView) v.findViewById(R.id.trailer_title);

                            ttitle.setText(name);
                            play.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse("https://www.youtube.com/watch?v=" + key));
                                    startActivity(i);
                                }
                            });

                            linearLayout.addView(v);

                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }

        }
        );

        requestQueue.add(request);
    }

    public void sendReviewRequest(String mUrl)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, mUrl, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject jsonObject) {

                if (jsonObject == null || jsonObject.length() == 0)
                {
                    return ;
                }
                else {
                    try {
                        JSONArray jsonArray = jsonObject.getJSONArray("results");
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String x = jsonObject1.getString("author");
                            String y = jsonObject1.getString("content");

                            View v = LayoutInflater.from(getActivity()).inflate(R.layout.reviews_layout, null);

                            TextView author = (TextView) v.findViewById(R.id.author);
                            TextView content = (TextView) v.findViewById(R.id.content);
                            author.setText(x);
                            content.setText(y);

                            linearLayout1.addView(v);

                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }

        }
        );

        requestQueue.add(request);
    }

}
