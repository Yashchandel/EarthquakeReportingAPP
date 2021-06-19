package com.example.android.quakereport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATER=" of ";
    public EarthquakeAdapter(@NonNull Context context,ArrayList<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Earthquake currEarthquake=getItem(position);
        View listItemView=convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }
        TextView magnitude=(TextView) listItemView.findViewById(R.id.magnitude);

        TextView date=(TextView) listItemView.findViewById(R.id.date);

        GradientDrawable magnitudeCircle=(GradientDrawable) magnitude.getBackground();
        int magnitudeColor=getMagnitudeColor(currEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String magnitudeFormate=formatMagnitude(currEarthquake.getMagnitude());
        magnitude.setText(magnitudeFormate);

        String locationOffset;
        String primaryLocation;
        String originalLocation=currEarthquake.getLocation();
        if(originalLocation.contains(LOCATION_SEPARATER)){
            String[] parts=originalLocation.split(LOCATION_SEPARATER);
            locationOffset=parts[0]+LOCATION_SEPARATER;
            primaryLocation=parts[1];
        }
        else{
            locationOffset=getContext().getString(R.string.near_the);
            primaryLocation=originalLocation;
        }
        TextView offsetView=(TextView) listItemView.findViewById(R.id.location_offset);
        offsetView.setText(locationOffset);

        TextView primaryView=(TextView) listItemView.findViewById(R.id.primary_location);
        primaryView.setText(primaryLocation);

        Date dateObject=new Date(currEarthquake.getTimeInMilliseconds());
        String formattedDate=formatDate(dateObject);
        date.setText(formattedDate);
        TextView time=(TextView) listItemView.findViewById(R.id.time);
        String formattedTime=formatTime(dateObject);
        time.setText(formattedTime);
        return listItemView;
    }
    private String formatDate(Date dateObject) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormate=new DecimalFormat("0.0");
        return magnitudeFormate.format(magnitude);
    }
    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int mag=(int) Math.floor(magnitude);
        switch (mag){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }
}
