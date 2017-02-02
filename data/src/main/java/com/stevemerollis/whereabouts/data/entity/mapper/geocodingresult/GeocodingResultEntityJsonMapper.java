package com.stevemerollis.whereabouts.data.entity.mapper.geocodingresult;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.stevemerollis.whereabouts.data.entity.geocoding.GeocodingResponse;
import com.stevemerollis.whereabouts.data.entity.geocoding.GeocodingResultEntity;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class GeocodingResultEntityJsonMapper {

    private final Gson gson;

    @Inject
    public GeocodingResultEntityJsonMapper() {
        this.gson = new Gson();
    }

    public GeocodingResponse transform(String geocodingJsonResponse)
            throws JsonSyntaxException {
        final Type geocodingResponseType = new TypeToken<GeocodingResponse>(){}.getType();
        return this.gson.fromJson(geocodingJsonResponse, geocodingResponseType);
    }
}
