package com.stevemerollis.whereabouts.data.net.geocodingresult;

import android.content.Context;

import com.stevemerollis.whereabouts.data.R;
import com.stevemerollis.whereabouts.data.entity.geocoding.GeocodingResponse;
import com.stevemerollis.whereabouts.data.entity.geocoding.GeocodingResultEntity;
import com.stevemerollis.whereabouts.data.entity.mapper.geocodingresult.GeocodingResultEntityJsonMapper;
import com.stevemerollis.whereabouts.data.exception.GoogleApiException;
import com.stevemerollis.whereabouts.data.exception.NetworkConnectionException;
import com.stevemerollis.whereabouts.data.net.ApiConnection;
import com.stevemerollis.whereabouts.data.net.BaseApiImpl;

import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.List;

import io.reactivex.Observable;

public class GeocodingResultRestApiImpl extends BaseApiImpl implements GeocodingResultRestApi {

    private final GeocodingResultEntityJsonMapper geocodingResultEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context {@link android.content.Context}.
     * @param geocodingResultEntityJsonMapper {@link GeocodingResultEntityJsonMapper}.
     */
    public GeocodingResultRestApiImpl(Context context, GeocodingResultEntityJsonMapper geocodingResultEntityJsonMapper) {
        super(context);

        if (geocodingResultEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!");
        }

        this.geocodingResultEntityJsonMapper = geocodingResultEntityJsonMapper;
    }

    private String getGeocodingResultURL(String userQuery) {
        String[] queryFragments = userQuery.split(" ");
        String formattedQuery = "";

        for (String fragment : queryFragments) {
            formattedQuery += fragment + '+';
        }

        formattedQuery = formattedQuery.substring(0, formattedQuery.length() -1);

        return MessageFormat.format(getContext().getString(R.string.geocoding_api_url), formattedQuery,
                "1234");
    }

    private String getGeocodingResults(String userQuery) throws MalformedURLException {
        String url = getGeocodingResultURL(userQuery);
        return ApiConnection.createGET(url).requestSyncCall();
    }

    @Override
    public Observable<List<GeocodingResultEntity>> getPlaceEntities(String userQuery) {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String apiResponse = getGeocodingResults(userQuery);
                    if (apiResponse != null) {
                        GeocodingResponse geocodingResponse = geocodingResultEntityJsonMapper.transform(apiResponse);

                        if (!geocodingResponse.status.equals("OK")) {
                            emitter.onError(new GoogleApiException(geocodingResponse.errorMessage));
                        }

                        emitter.onNext(geocodingResponse.resultEntities);
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
