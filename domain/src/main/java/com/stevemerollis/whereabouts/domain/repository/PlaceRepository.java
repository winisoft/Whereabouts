package com.stevemerollis.whereabouts.domain.repository;

import java.util.List;

import io.reactivex.Observable;

import com.stevemerollis.whereabouts.domain.Place;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;

/**
 * Interface that represents a Repository for getting {@link Place} related data.
 */
public interface PlaceRepository {

    /**
     * Get an {@link Observable} which will emit a List of {@link Place}.
     *
     * @param params the parameters of our search
     */
    Observable<List<Place>> places(final PlaceRequestParams params);
}
