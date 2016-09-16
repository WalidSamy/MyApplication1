package com.example.africano.myapplication1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragmentMovie extends Fragment {

    RequestQueue requestQueue;
    ArrayList<RowMovie> row_movies;

    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<RowMovie> rowMovie = new ArrayList<>();
    RowMovieMethods rowMovieMethods;

    public FragmentMovie() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getActivity());
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.movie_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rowMovieMethods = new RowMovieMethods(getActivity());

        sharedPreferences = getActivity().getSharedPreferences("data" , Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main_menu , menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.pop)
        {
            editor.putInt("show", 0);
            editor.apply();

            sendRequest("http://api.themoviedb.org/3/discover/movie?api_key=77363e36dfe4d8d2f793a71be536ddae&sort_by=popularity.desc");

        }
        else if(id == R.id.rated)
        {
            editor.putInt("show",1);
            editor.apply();

            sendRequest("http://api.themoviedb.org/3/discover/movie?api_key=77363e36dfe4d8d2f793a71be536ddae&sort_by=vote_average.desc");
        }

        else if(id == R.id.favorit)
        {
            editor.putInt("show",2);
            editor.apply();

            rowMovie = rowMovieMethods.getFav();
            recyclerView.setAdapter(new Recycler_adapter(getActivity() , rowMovie));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {

        super.onResume();

        int value = sharedPreferences.getInt("show",0);
        if (value ==0)
        {
            sendRequest("http://api.themoviedb.org/3/discover/movie?api_key=77363e36dfe4d8d2f793a71be536ddae&sort_by=popularity.desc");
        }
        else if (value == 1)
        {
            sendRequest("http://api.themoviedb.org/3/discover/movie?api_key=77363e36dfe4d8d2f793a71be536ddae&sort_by=vote_average.desc");
        }
        else
        {

            rowMovie = rowMovieMethods.getFav();

            recyclerView.setAdapter(new Recycler_adapter(getActivity() , rowMovie));
        }

    }

    public void sendRequest(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                row_movies = response(jsonObject);
               if (row_movies != null && getActivity() != null) {

                    Recycler_adapter adapter = new Recycler_adapter(getActivity(),row_movies);
                    recyclerView.setAdapter(adapter);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public ArrayList<RowMovie> response(JSONObject jsonObject) {
        row_movies= new ArrayList<>();
        if (jsonObject == null || jsonObject.length() == 0) {
            return null;
        }
        else {

            try {
                JSONArray arr = jsonObject.getJSONArray("results");
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject raw = arr.getJSONObject(i);
                    long id = raw.getLong("id");
                    String name=raw.getString("title");
                    String poster= raw.getString("poster_path");
                    String panner =raw.getString("backdrop_path");
                    String relse_date =raw.getString("release_date");
                    String summry =raw.getString("overview");
                    double vote=raw.getDouble("vote_average");

                    row_movies.add(new RowMovie(id,poster,name,panner,relse_date,summry,vote));
                   // Log.d("abcd", data.get(i).getOverView());


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return row_movies;

        }



    }


}
