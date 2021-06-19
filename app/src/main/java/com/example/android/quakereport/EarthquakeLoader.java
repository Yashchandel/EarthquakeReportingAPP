package com.example.android.quakereport;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {
    private String murl;
    public EarthquakeLoader(Context content,String url){
        super(content);
        murl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<Earthquake> loadInBackground() {
        if(murl.length()<1  || murl==null){
            return null;
        }
        ArrayList<Earthquake> earthquakes=Utils.fetchEarthquakeData(murl);
        return earthquakes;

    }
}
