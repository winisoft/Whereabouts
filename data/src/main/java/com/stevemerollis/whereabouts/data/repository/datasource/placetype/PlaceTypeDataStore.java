package com.stevemerollis.whereabouts.data.repository.datasource.placetype;

import com.stevemerollis.whereabouts.data.entity.PlaceTypeEntity;

import java.util.List;

import io.reactivex.Observable;

public interface PlaceTypeDataStore {

    /**
     * Get an {@link Observable} which will emit a List of {@link PlaceTypeEntity}.
     */
    Observable<List<PlaceTypeEntity>> getPlaceTypeEntityList();
}
