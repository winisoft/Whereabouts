package com.stevemerollis.whereabouts.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.stevemerollis.whereabouts.data.entity.mapper.place.PlaceEntityJsonMapper;
import com.stevemerollis.whereabouts.data.entity.mapper.placetype.PlaceTypeEntityJsonMapper;
import com.stevemerollis.whereabouts.data.net.place.PlaceRestApi;
import com.stevemerollis.whereabouts.data.net.place.PlaceRestApiImpl;
import com.stevemerollis.whereabouts.data.net.placetype.PlaceTypeRestApi;
import com.stevemerollis.whereabouts.data.net.placetype.PlaceTypeRestApiImpl;
import com.stevemerollis.whereabouts.data.repository.datasource.geocodingresult.GeocodingResultDataStore;
import com.stevemerollis.whereabouts.data.repository.datasource.place.CloudPlaceDataStore;
import com.stevemerollis.whereabouts.data.repository.datasource.place.PlaceDataStore;
import com.stevemerollis.whereabouts.data.repository.datasource.placetype.CloudPlaceTypeDataStore;
import com.stevemerollis.whereabouts.data.repository.datasource.placetype.PlaceTypeDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link PlaceDataStore}.
 */

@Singleton
public class DataStoreFactory {

    private final Context context;

    @Inject
    DataStoreFactory(@NonNull Context context){
        this.context = context.getApplicationContext();
    }

    /**
     * Create {@link PlaceDataStore} to retrieve data from the Cloud.
     */
    public PlaceDataStore createCloudPlaceDataStore() {
        final PlaceEntityJsonMapper placeEntityJsonMapper = new PlaceEntityJsonMapper();
        final PlaceRestApi placeRestApi = new PlaceRestApiImpl(this.context, placeEntityJsonMapper);

        return new CloudPlaceDataStore(placeRestApi);
    }

    /**
     * Create {@link PlaceDataStore} to retrieve data from the Cloud.
     */
    public PlaceTypeDataStore createCloudPlaceTypeDataStore() {
        final PlaceTypeEntityJsonMapper placeTypeEntityJsonMapper = new PlaceTypeEntityJsonMapper();
        final PlaceTypeRestApi placeTypeRestApi = new PlaceTypeRestApiImpl(this.context, placeTypeEntityJsonMapper);

        return new CloudPlaceTypeDataStore(placeTypeRestApi);
    }

    public GeocodingResultDataStore createCloudGeocodingResultDataStore() {

        return null;
        //return new GeocodingResultDataStore();
    }
}
