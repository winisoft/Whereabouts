package com.stevemerollis.whereabouts.data.net.geocodingresult;

import com.stevemerollis.whereabouts.data.entity.geocoding.GeocodingResultEntity;

import java.util.List;

import io.reactivex.Observable;

public interface GeocodingResultRestApi {

    //TODO: Get this outta heeere
    String API_GEOCODING_URL = "https://maps.googleapis.com/maps/api/geocode/json?address={0}&key={1}";

    Observable<List<GeocodingResultEntity>> getPlaceEntities(final String userQuery);
}
