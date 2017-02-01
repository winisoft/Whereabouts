package com.stevemerollis.whereabouts.domain;

import java.util.List;

public class PlaceRequestParams {

    public double latitude;
    public double longitude;
    public int proximityRadius;
    public List<String> placeTypes;
    public boolean iHaveBeen;
    public boolean iHaveNotBeen;
    public boolean iHaveEitherBeenOrNotBeen;
    public int minimumRating;
    public boolean ratedByMe;
    public boolean maximumPrice;

    public PlaceRequestParams(){}

    public double getLatitude(){ return latitude; }

    public double getLongitude(){ return longitude; }

    public int getProximityRadius(){ return proximityRadius; }

    public List<String> getPlaceTypes(){ return placeTypes; }

    /*List<String> selectedPlaceTypes, String distanceText, Enums.VISITED_PARAM visitedParam, int minRating,
    boolean myRate, int maxPrice, boolean usesOpenAtTime*/

}
