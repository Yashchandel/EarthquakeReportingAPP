package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Earthquake>>{
    EarthquakeAdapter madapter;
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        madapter=new EarthquakeAdapter(this,new ArrayList<Earthquake>());
        earthquakeListView.setAdapter(madapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currEarthquake=madapter.getItem(position);
                Uri earthquakeUri=Uri.parse(currEarthquake.getUrl());
                Intent website= new Intent(Intent.ACTION_VIEW,earthquakeUri);
                startActivity(website);
            }
        });
        getSupportLoaderManager().initLoader(0,null,this).forceLoad();
    }

    @NonNull
    @Override
    public Loader<ArrayList<Earthquake>> onCreateLoader(int id, @Nullable Bundle args) {
        return new EarthquakeLoader(EarthquakeActivity.this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> data) {
        if(data==null){
            return ;
        }
        madapter.addAll(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Earthquake>> loader) {
        madapter.clear();
    }
}
