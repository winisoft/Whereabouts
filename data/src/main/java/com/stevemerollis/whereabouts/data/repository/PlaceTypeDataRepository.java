package com.stevemerollis.whereabouts.data.repository;

import com.stevemerollis.whereabouts.data.entity.mapper.placetype.PlaceTypeEntityDataMapper;
import com.stevemerollis.whereabouts.data.repository.datasource.placetype.PlaceTypeDataStore;
import com.stevemerollis.whereabouts.data.repository.datasource.DataStoreFactory;
import com.stevemerollis.whereabouts.domain.GeocodingResult;
import com.stevemerollis.whereabouts.domain.PlaceType;
import com.stevemerollis.whereabouts.domain.repository.PlaceTypeRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * {@link PlaceTypeRepository} for retrieving placeTypes data.
 */
public class PlaceTypeDataRepository implements PlaceTypeRepository {

    private final DataStoreFactory dataStoreFactory;
    private final PlaceTypeEntityDataMapper placeTypeEntityDataMapper;

    @Inject
    PlaceTypeDataRepository(DataStoreFactory dataStoreFactory,
                            PlaceTypeEntityDataMapper placeTypeEntityDataMapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.placeTypeEntityDataMapper = placeTypeEntityDataMapper;
    }

    @Override
    public Observable<List<PlaceType>> placeTypes() {
        final PlaceTypeDataStore placeTypeDataStore = this.dataStoreFactory.createCloudPlaceTypeDataStore();
        return placeTypeDataStore.getPlaceTypeEntityList().map(this.placeTypeEntityDataMapper::transform);
    }

    @Override
    public Observable<List<GeocodingResult>> geocodingResults() {
        return null;
    }
}