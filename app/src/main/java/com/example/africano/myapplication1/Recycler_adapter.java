package com.example.africano.myapplication1;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Recycler_adapter extends RecyclerView.Adapter<Recycler_adapter.viewHolder> {

    ClickCallback callback;
    public LayoutInflater layoutInflater;
    ArrayList<RowMovie>arrayList;
    Context context;


    public Recycler_adapter(Context context, ArrayList<RowMovie> arrayList){
        callback = (ClickCallback) context;
        this.context=context;
        this.arrayList=arrayList;
        layoutInflater= LayoutInflater.from(context);

    }

    @Override
    public Recycler_adapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new viewHolder(layoutInflater.inflate(R.layout.movie_item,parent,false));
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        Picasso.with(context).load(Uri.parse("http://image.tmdb.org/t/p/w342"+ arrayList.get(position).getPoster())).
                into(holder.image);
      //  Toast.makeText(context, "http://image.tmdb.org/t/p/w342" + arrayList.get(position).getPoster(), Toast.LENGTH_SHORT).show();
       // Log.i("img url ", "http://image.tmdb.org/t/p/w342"+ arrayList.get(position).getPoster());
        holder.txt.setText(arrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class viewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView txt;
        public viewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.movie_poster);
            txt = (TextView) itemView.findViewById(R.id.movie_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    callback.onMovieCardClicked(arrayList.get(getPosition()).getId());

//                    Intent i =new Intent(context,DetailOfMovie.class);
//                    i.putExtra("id",arrayList.get(getPosition()).getId());
//                    context.startActivity(i);

                }
            });
        }

    }
    public interface ClickCallback {

        void onMovieCardClicked(long id);


    }
}
