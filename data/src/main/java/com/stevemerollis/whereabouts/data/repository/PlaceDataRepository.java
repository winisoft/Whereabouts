package com.stevemerollis.whereabouts.data.repository;

import com.stevemerollis.whereabouts.data.entity.mapper.place.PlaceEntityDataMapper;
import com.stevemerollis.whereabouts.data.repository.datasource.place.PlaceDataStore;
import com.stevemerollis.whereabouts.data.repository.datasource.DataStoreFactory;
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

    private final DataStoreFactory dataStoreFactory;
    private final PlaceEntityDataMapper placeEntityDataMapper;

    @Inject
    PlaceDataRepository(DataStoreFactory dataStoreFactory,
                        PlaceEntityDataMapper placeEntityDataMapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.placeEntityDataMapper = placeEntityDataMapper;
    }

    @Override
    public Observable<List<Place>> places(PlaceRequestParams params) {
        final PlaceDataStore placeDataStore = this.dataStoreFactory.createCloudPlaceDataStore();
        return placeDataStore.getPlaceEntityList(params).map(this.placeEntityDataMapper::transform);
    }
}
