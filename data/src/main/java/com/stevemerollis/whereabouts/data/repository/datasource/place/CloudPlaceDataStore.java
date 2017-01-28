package com.stevemerollis.whereabouts.data.repository.datasource.place;

import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.data.net.place.PlaceRestApi;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;

import java.util.List;

import io.reactivex.Observable;

public class CloudPlaceDataStore implements PlaceDataStore {

    private final PlaceRestApi placeRestApi;

    public CloudPlaceDataStore(PlaceRestApi placeRestApi){
        this.placeRestApi = placeRestApi;
    }

    @Override
    public Observable<List<PlaceEntity>> getPlaceEntityList(PlaceRequestParams params) {
        return this.placeRestApi.getPlaceEntities(params);
    }
}
