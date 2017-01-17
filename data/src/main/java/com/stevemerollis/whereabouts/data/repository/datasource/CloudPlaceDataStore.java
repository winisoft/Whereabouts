package com.stevemerollis.whereabouts.data.repository.datasource;

import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.data.net.RestApi;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;

import java.util.List;

import io.reactivex.Observable;

public class CloudPlaceDataStore implements PlaceDataStore {

    private final RestApi restApi;

    CloudPlaceDataStore(RestApi restApi){
        this.restApi = restApi;
    }

    @Override
    public Observable<List<PlaceEntity>> getPlaceEntityList(PlaceRequestParams params) {
        return this.restApi.getPlaceEntities(params);
    }
}
