package com.stevemerollis.whereabouts.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.stevemerollis.whereabouts.data.R;
import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.data.entity.mapper.PlaceEntityJsonMapper;
import com.stevemerollis.whereabouts.data.exception.NetworkConnectionException;
import com.stevemerollis.whereabouts.data.mock.MockResponse;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

    private final Context context;
    private final PlaceEntityJsonMapper placeEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context {@link android.content.Context}.
     * @param placeEntityJsonMapper {@link PlaceEntityJsonMapper}.
     */
    public RestApiImpl(Context context, PlaceEntityJsonMapper placeEntityJsonMapper) {
        if (context == null || placeEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!");
        }
        this.context = context.getApplicationContext();
        this.placeEntityJsonMapper = placeEntityJsonMapper;
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    private String getFromApi(PlaceRequestParams params) throws MalformedURLException {
        StringBuilder apiUrl = new StringBuilder(API_PLACE_URL);
        apiUrl.append("location=").append(params.getLatitude()).append(",").append(params.getLongitude());
        apiUrl.append("&radius=").append(params.getProximityRadius());
        apiUrl.append("&type=").append(params.getEstablishmentType());
        apiUrl.append("&sensor=true");
        apiUrl.append("&key=").append(context.getString(R.string.google_place_api_key));
        return ApiConnection.createGET(apiUrl.toString()).requestSyncCall();
    }

    @Override
    public Observable<List<PlaceEntity>> getPlaceEntities(PlaceRequestParams params) {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    //String apiResponse = getFromApi(params);
                    String apiResponse = MockResponse.get().replace('"', '\"');
                    if (apiResponse != null) {
                        List<PlaceEntity> placeEntities = placeEntityJsonMapper.transformPlaceApiResponse(apiResponse);
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

