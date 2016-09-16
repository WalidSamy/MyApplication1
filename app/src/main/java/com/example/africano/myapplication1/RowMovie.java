package com.example.africano.myapplication1;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class RowMovie extends RealmObject {

    @PrimaryKey
    private long id;
    private String poster;
    private String panner;
    private String RelaseDate;
    private String title;
    private String overView;
    private double voteAvg;


    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPanner() {
        return panner;
    }

    public void setPanner(String panner) {
        this.panner = panner;
    }

    public String getRelaseDate() {
        return RelaseDate;
    }

    public void setRelaseDate(String relaseDate) {
        RelaseDate = relaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public double getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(double voteAvg) {
        this.voteAvg = voteAvg;
    }


    public RowMovie(long id, String poster, String title, String overView, String panner, String RelaseDate, double voteAvg){
        this.panner = panner;
        this.id=id;
        this.overView = overView;
        this.RelaseDate = RelaseDate;
        this.setPoster(poster);
        this.voteAvg = voteAvg;
        this.title = title;

    }

    public RowMovie()
    {
    }

    public RowMovie(long id  , String title , String poster)
    {
        this.id=id;
        this.title=title;
        this.poster=poster;
    }

}
