package com.stevemerollis.whereabouts.data.repository.datasource.geocodingresult;

import com.stevemerollis.whereabouts.data.entity.geocoding.GeocodingResultEntity;
import com.stevemerollis.whereabouts.data.net.geocodingresult.GeocodingResultRestApi;

import java.util.List;

import io.reactivex.Observable;

public class CloudGeocodingResultDataStore implements GeocodingResultDataStore {

    private final GeocodingResultRestApi geocodingResultRestApi;

    public CloudGeocodingResultDataStore(GeocodingResultRestApi geocodingResultRestApi){
        this.geocodingResultRestApi = geocodingResultRestApi;
    }

    @Override
    public Observable<List<GeocodingResultEntity>> getGeocodingResultEntityList(String userQuery) {
        return this.geocodingResultRestApi.getPlaceEntities(userQuery);
    }
}
