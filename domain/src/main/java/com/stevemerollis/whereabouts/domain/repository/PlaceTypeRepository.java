package com.stevemerollis.whereabouts.domain.repository;

import java.util.List;

import io.reactivex.Observable;

import com.stevemerollis.whereabouts.domain.PlaceType;

/**
 * Interface that represents a Repository for getting {@link PlaceType} related data.
 */
public interface PlaceTypeRepository {

    /**
     * Get an {@link Observable} which will emit a List of {@link PlaceType}.
     */
    Observable<List<PlaceType>> placeTypes();
}
