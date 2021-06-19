package com.example.android.quakereport;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;
    public Earthquake(double Magnitude,String Location,long TimeInMilliseconds,String Url){
        mTimeInMilliseconds=TimeInMilliseconds;
        mLocation=Location;
        mMagnitude=Magnitude;
        mUrl=Url;
    }
    public long getTimeInMilliseconds(){return mTimeInMilliseconds;}
    public double getMagnitude() {
        return mMagnitude;
    }
    public String getLocation(){
        return mLocation;
    }

    public String getUrl() {
        return mUrl;
    }
}
