package com.stevemerollis.whereabouts.domain;

public class PlaceRequestParams {

    private double latitude;

    private double longitude;

    private int proximityRadius;

    private String establishmentType;

    public PlaceRequestParams(double lat, double lng, int proximityRadius, String establishmentType){
        this.latitude = lat;
        this.longitude = lng;
        this.proximityRadius = proximityRadius;
        this.establishmentType = establishmentType;
    }

    public double getLatitude(){ return latitude; }

    public double getLongitude(){ return longitude; }

    public int getProximityRadius(){ return proximityRadius; }

    public String getEstablishmentType(){ return establishmentType; }

}
