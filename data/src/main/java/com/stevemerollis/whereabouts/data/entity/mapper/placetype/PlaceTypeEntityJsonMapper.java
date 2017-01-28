package com.stevemerollis.whereabouts.data.entity.mapper.placetype;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.stevemerollis.whereabouts.data.entity.PlaceTypeEntity;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class PlaceTypeEntityJsonMapper {

    private final Gson gson;

    @Inject
    public PlaceTypeEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<PlaceTypeEntity> transform(String placeTypeJsonResponse)
            throws JsonSyntaxException {
        final Type placeTypeType = new TypeToken<List<PlaceTypeEntity>>(){}.getType();
        return this.gson.fromJson(placeTypeJsonResponse, placeTypeType);
    }
}
