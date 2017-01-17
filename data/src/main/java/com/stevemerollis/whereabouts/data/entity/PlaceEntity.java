package com.stevemerollis.whereabouts.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceEntity {

    @SerializedName("latitude")
    public double latitude;

    @SerializedName("longitude")
    public double longitude;

    @SerializedName("icon")
    public String icon;

    @SerializedName("googleId")
    public String googleId;

    @SerializedName("name")
    public String name;

    @SerializedName("openNow")
    public boolean openNow;

    @SerializedName("googlePlaceId")
    public String googlePlaceId;

    @SerializedName("types")
    public List<String> types;

    @SerializedName("whereaboutsComments")
    public String whereaboutsComments;

    @SerializedName("whereaboutsRating")
    public double whereaboutsRating;


}
