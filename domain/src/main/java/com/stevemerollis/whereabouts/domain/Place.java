package com.stevemerollis.whereabouts.domain;

import java.util.List;

/**
 * Class that represents a Place in the domain layer.
 */
public class Place {

    private double latitude;
    private double longitude;
    private String icon;
    private String googleId;
    private String name;
    private boolean openNow;
    private String googlePlaceId;
    private List<String> types;
    private String whereaboutsComments;
    private double whereaboutsRating;
    private boolean whereaboutsHaveVisited;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getWhereaboutsComments() {
        return whereaboutsComments;
    }

    public void setWhereaboutsComments(String whereaboutsComments) {
        this.whereaboutsComments = whereaboutsComments;
    }

    public double getWhereaboutsRating() {
        return whereaboutsRating;
    }

    public void setWhereaboutsRating(double whereaboutsRating) {
        this.whereaboutsRating = whereaboutsRating;
    }

    public boolean getHaveVisited(){ return whereaboutsHaveVisited; }

    public void setHaveVisited(boolean haveVisited){
        this.whereaboutsHaveVisited = haveVisited;
    }
}
