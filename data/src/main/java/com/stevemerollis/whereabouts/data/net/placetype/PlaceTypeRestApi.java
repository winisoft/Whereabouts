package com.stevemerollis.whereabouts.data.net.placetype;

import com.stevemerollis.whereabouts.data.entity.PlaceTypeEntity;

import java.util.List;

import io.reactivex.Observable;

public interface PlaceTypeRestApi {

    String API_PLACE_TYPE_URL = "https://stevemerollis.com/api/places";

    Observable<List<PlaceTypeEntity>> getPlaceTypeEntities();
}
