package com.stevemerollis.whereabouts.data.entity.geocoding;

import com.google.gson.annotations.SerializedName;

public class GeocodingResultEntity {

    @SerializedName("formatted_address")
    public String formattedAddress;

    @SerializedName("geometry")
    public Geometry geometry;
}
