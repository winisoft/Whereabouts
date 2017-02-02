package com.stevemerollis.whereabouts.data.net.geocodingresult;

import com.stevemerollis.whereabouts.data.entity.geocoding.GeocodingResultEntity;

import java.util.List;

import io.reactivex.Observable;

public interface GeocodingResultRestApi {

    //TODO: Get this outta heeere
    String API_GEOCODING_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=Chicago,+IL&key=AIzaSyAgvjOgdLZgMIF5DsOs-oTa5dNEFTx_VXU";

    Observable<List<GeocodingResultEntity>> getPlaceEntities(final String userQuery);
}