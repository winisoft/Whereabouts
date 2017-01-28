package com.stevemerollis.whereabouts.data.net.place;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.data.entity.PlaceTypeEntity;
import com.stevemerollis.whereabouts.data.entity.mapper.place.PlaceEntityJsonMapper;
import com.stevemerollis.whereabouts.data.entity.mapper.placetype.PlaceTypeEntityJsonMapper;
import com.stevemerollis.whereabouts.data.exception.NetworkConnectionException;
import com.stevemerollis.whereabouts.data.mock.MockResponse;
import com.stevemerollis.whereabouts.data.net.BaseApiImpl;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link PlaceRestApi} implementation for retrieving data from the network.
 */
public class PlaceRestApiImpl extends BaseApiImpl implements PlaceRestApi {


    private final PlaceEntityJsonMapper placeEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context {@link android.content.Context}.
     * @param placeEntityJsonMapper {@link PlaceEntityJsonMapper}.
     */
    public PlaceRestApiImpl(Context context, PlaceEntityJsonMapper placeEntityJsonMapper) {
        super(context);

        if (placeEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!");
        }

        this.placeEntityJsonMapper = placeEntityJsonMapper;
    }

    @Override
    public Observable<List<PlaceEntity>> getPlaceEntities(PlaceRequestParams params) {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String apiResponse = MockResponse.getPlaces().replace('"', '\"');
                    if (apiResponse != null) {
                        List<PlaceEntity> placeEntities = placeEntityJsonMapper.transform(apiResponse);
                        emitter.onNext(placeEntities);
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(e);
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }
}

