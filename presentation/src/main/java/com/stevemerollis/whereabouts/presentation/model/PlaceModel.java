package com.stevemerollis.whereabouts.presentation.model;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.List;

/**
 * Class that represents a place in the presentation layer.
 */
public class PlaceModel {

    private double latitude;
    private double longitude;
    private String icon;
    private String googleId;
    private String name;
    private boolean openNow;
    private String googlePlaceId;
    private List<String> types;
    private String vicinity;
    private String whereaboutsComments;
    private double whereaboutsRating;
    private boolean whereaboutsHaveVisited;

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public void setVicinity(String vicinity){ this.vicinity = vicinity; }

    public void setWhereaboutsComments(String whereaboutsComments) {
        this.whereaboutsComments = whereaboutsComments;
    }

    public void setWhereaboutsRating(double whereaboutsRating) {
        this.whereaboutsRating = whereaboutsRating;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setWhereaboutsHaveVisited(boolean haveVisited){
        this.whereaboutsHaveVisited = haveVisited;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getIcon() {
        return icon;
    }

    public String getGoogleId() {
        return googleId;
    }

    public String getName() {
        return name;
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public List<String> getTypes() {
        return types;
    }

    public String getWhereaboutsComments() {
        return whereaboutsComments;
    }

    public double getWhereaboutsRating() {
        return whereaboutsRating;
    }

    public boolean getHaveVisited(){ return whereaboutsHaveVisited; }

    public String getVicinity(){ return vicinity; }

    public float getMarkerColor(){
        if (!whereaboutsHaveVisited) return BitmapDescriptorFactory.HUE_CYAN;
        if (whereaboutsRating < 2.0) return BitmapDescriptorFactory.HUE_YELLOW;
        if (whereaboutsRating < 3.0) return BitmapDescriptorFactory.HUE_ORANGE;
        if (whereaboutsRating < 4.0) return BitmapDescriptorFactory.HUE_RED;
        return BitmapDescriptorFactory.HUE_VIOLET;
    }
}
