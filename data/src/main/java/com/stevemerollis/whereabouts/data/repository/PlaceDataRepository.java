package com.stevemerollis.whereabouts.data.repository;

import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.data.entity.mapper.PlaceEntityDataMapper;
import com.stevemerollis.whereabouts.data.repository.datasource.PlaceDataStore;
import com.stevemerollis.whereabouts.data.repository.datasource.PlaceDataStoreFactory;
import com.stevemerollis.whereabouts.domain.Place;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.domain.repository.PlaceRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * {@link PlaceRepository} for retrieving place data.
 */
public class PlaceDataRepository implements PlaceRepository {

    private final PlaceDataStoreFactory placeDataStoreFactory;
    private final PlaceEntityDataMapper placeEntityDataMapper;

    @Inject
    PlaceDataRepository(PlaceDataStoreFactory dataStoreFactory,
                        PlaceEntityDataMapper placeEntityDataMapper) {
        this.placeDataStoreFactory = dataStoreFactory;
        this.placeEntityDataMapper = placeEntityDataMapper;
    }

    @Override
    public Observable<List<Place>> places(PlaceRequestParams params) {
        final PlaceDataStore placeDataStore = this.placeDataStoreFactory.createCloudDataStore();
        return placeDataStore.getPlaceEntityList(params).map(this.placeEntityDataMapper::transform);
    }
}
