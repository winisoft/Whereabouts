package com.stevemerollis.whereabouts.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class BaseApiImpl {

    private final Context context;

    public BaseApiImpl(Context context) {
        if (context == null){
            throw new IllegalArgumentException("The constructor parameters cannot be null!");
        }
        this.context = context;
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    protected boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }

    public Context getContext() {
        return this.context;
    }
}
