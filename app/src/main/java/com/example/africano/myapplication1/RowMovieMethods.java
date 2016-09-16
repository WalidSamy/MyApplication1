package com.example.africano.myapplication1;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class RowMovieMethods {


    Realm realm;
    RealmResults<RowMovie> result;
    Context context;

    public RowMovieMethods(Context context){
        realm= Realm.getInstance(context);
        this.context = context;
    }

    public void insert(Long id , String title , String poster) {
        realm.beginTransaction();
        RowMovie data = new RowMovie(id, title, poster);
        realm.copyToRealm(data);
        realm.commitTransaction();
    }

    public ArrayList<RowMovie> getFav()
    {
        if(realm==null)
        {
            realm = Realm.getInstance(context);
        }
        ArrayList<RowMovie> mydat = new ArrayList<>();

        result = realm.where(RowMovie.class).findAll();
        for (int i = 0; i<result.size() ; i++){

            mydat.add(result.get(i));
        }
        return mydat;
    }

    public boolean checkIfExist(long id)
    {

        result = realm.where(RowMovie.class).equalTo("id", id).findAll();
        if(result.size() != 0)
        {
            return true;
        }
        else
            return false;
    }


    public void delete (long id)
    {
        RowMovie realmTest = realm.where(RowMovie.class).equalTo("id", id).findAll().get(0);
        realm.beginTransaction();
        realmTest.removeFromRealm();
        realm.commitTransaction();
        realm.close();
    }

}
