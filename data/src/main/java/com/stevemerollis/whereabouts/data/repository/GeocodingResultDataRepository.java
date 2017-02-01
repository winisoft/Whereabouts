package com.stevemerollis.whereabouts.data.repository;

import com.stevemerollis.whereabouts.data.entity.mapper.geocodingresult.GeocodingResultEntityDataMapper;
import com.stevemerollis.whereabouts.data.repository.datasource.DataStoreFactory;
import com.stevemerollis.whereabouts.data.repository.datasource.geocodingresult.GeocodingResultDataStore;
import com.stevemerollis.whereabouts.domain.GeocodingResult;
import com.stevemerollis.whereabouts.domain.repository.GeocodingResultRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GeocodingResultDataRepository implements GeocodingResultRepository {

    private final DataStoreFactory dataStoreFactory;
    private final GeocodingResultEntityDataMapper geocodingResultEntityDataMapper;

    @Inject
    GeocodingResultDataRepository(DataStoreFactory dataStoreFactory,
                            GeocodingResultEntityDataMapper geocodingResultEntityDataMapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.geocodingResultEntityDataMapper = geocodingResultEntityDataMapper;
    }

    @Override
    public Observable<List<GeocodingResult>> geocodingResults(String userQuery) {
        final GeocodingResultDataStore geocodingResultDataStore = this.dataStoreFactory.createCloudGeocodingResultDataStore();
        return geocodingResultDataStore.getGeocodingResultEntityList(userQuery).map(geocodingResultEntityDataMapper::transform);
    }
}
