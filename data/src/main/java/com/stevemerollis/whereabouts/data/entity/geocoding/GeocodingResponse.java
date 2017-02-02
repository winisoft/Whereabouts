package com.stevemerollis.whereabouts.data.entity.geocoding;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeocodingResponse {

    @SerializedName("results")
    public List<GeocodingResultEntity> resultEntities;

    @SerializedName("status")
    public String status;

    @SerializedName("error_message")
    public String errorMessage;
}
