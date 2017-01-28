package com.stevemerollis.whereabouts.data.entity.mapper.place;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.stevemerollis.whereabouts.data.entity.PlaceEntity;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class PlaceEntityJsonMapper {

    private final Gson gson;

    @Inject
    public PlaceEntityJsonMapper() {
        this.gson = new Gson();
    }

    public List<PlaceEntity> transform(String placeJsonResponse)
            throws JsonSyntaxException {
        final Type placeType = new TypeToken<List<PlaceEntity>>(){}.getType();
        return this.gson.fromJson(placeJsonResponse, placeType);
    }
}
