package com.stevemerollis.whereabouts.data.entity.geocoding;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("lat")
    public float lat;

    @SerializedName("lng")
    public float lng;
}
