package com.stevemerollis.whereabouts.data.entity;

import com.google.gson.annotations.SerializedName;

public class PlaceTypeEntity {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;
}
