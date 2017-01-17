package com.stevemerollis.whereabouts.data.net;

import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;

import java.util.List;

import io.reactivex.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {

    String API_PLACE_URL = "https://stevemerollis.com/api/places";

    Observable<List<PlaceEntity>> getPlaceEntities(PlaceRequestParams params);
}

