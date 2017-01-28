package com.stevemerollis.whereabouts.data.repository.datasource.place;

import java.util.List;

import io.reactivex.Observable;

import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;

/**
 * Interface that represents a data store from which data is retrieved.
 */
public interface PlaceDataStore {

    /**
     * Get an {@link Observable} which will emit a List of {@link PlaceEntity}.
     */
    Observable<List<PlaceEntity>> getPlaceEntityList(PlaceRequestParams params);
}
