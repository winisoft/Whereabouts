package com.stevemerollis.whereabouts.data.repository.datasource.geocodingresult;

import com.stevemerollis.whereabouts.data.entity.GeocodingResultEntity;

import java.util.List;

import io.reactivex.Observable;

public interface GeocodingResultDataStore {

    Observable<List<GeocodingResultEntity>> getGeocodingResultEntityList(String userQuery);
}
