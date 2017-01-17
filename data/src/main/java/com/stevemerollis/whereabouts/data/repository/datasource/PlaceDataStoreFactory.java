package com.stevemerollis.whereabouts.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.stevemerollis.whereabouts.data.entity.mapper.PlaceEntityJsonMapper;
import com.stevemerollis.whereabouts.data.net.RestApi;
import com.stevemerollis.whereabouts.data.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link PlaceDataStore}.
 */

@Singleton
public class PlaceDataStoreFactory {

    private final Context context;

    @Inject
    PlaceDataStoreFactory(@NonNull Context context){
        this.context = context.getApplicationContext();
    }

    /**
     * Create {@link PlaceDataStore} to retrieve data from the Cloud.
     */
    public PlaceDataStore createCloudDataStore() {
        final PlaceEntityJsonMapper placeEntityJsonMapper = new PlaceEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(this.context, placeEntityJsonMapper);

        return new CloudPlaceDataStore(restApi);
    }
}
