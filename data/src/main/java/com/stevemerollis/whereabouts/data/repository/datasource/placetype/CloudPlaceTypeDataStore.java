package com.stevemerollis.whereabouts.data.repository.datasource.placetype;

import com.stevemerollis.whereabouts.data.entity.PlaceTypeEntity;
import com.stevemerollis.whereabouts.data.net.place.PlaceRestApi;
import com.stevemerollis.whereabouts.data.net.placetype.PlaceTypeRestApi;

import java.util.List;

import io.reactivex.Observable;

public class CloudPlaceTypeDataStore implements PlaceTypeDataStore {

    private final PlaceTypeRestApi placeTypeRestApi;

    public CloudPlaceTypeDataStore(PlaceTypeRestApi placeTypeRestApi){
        this.placeTypeRestApi = placeTypeRestApi;
    }

    @Override
    public Observable<List<PlaceTypeEntity>> getPlaceTypeEntityList() {
        return this.placeTypeRestApi.getPlaceTypeEntities();
    }
}
