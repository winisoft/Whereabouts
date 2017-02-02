package com.stevemerollis.whereabouts.domain;

import java.util.List;

public class PlaceRequestParams {

    public double latitude;
    public double longitude;
    public int proximityRadius;
    public List<String> placeTypes;
    public boolean iHaveBeen;
    public boolean iHaveNotBeen;
    public boolean iWantToTry;
    public int openAtHour;
    public int openAtMinute;
    public int minRatingMe;
    public int minRatingOthers;
    public int maxPrice;

    public PlaceRequestParams(){}
}
