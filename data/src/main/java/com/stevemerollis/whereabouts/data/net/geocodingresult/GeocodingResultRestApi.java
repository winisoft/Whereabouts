package com.stevemerollis.whereabouts.data.net.geocodingresult;

import com.stevemerollis.whereabouts.data.entity.GeocodingResultEntity;

import java.util.List;

import io.reactivex.Observable;

public interface GeocodingResultRestApi {

    String API_GEOCODING_URL = "https://googleplaceapi...";

    Observable<List<GeocodingResultEntity>> getPlaceEntities(final String userQuery);
}
