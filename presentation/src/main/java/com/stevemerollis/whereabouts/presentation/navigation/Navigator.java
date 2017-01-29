package com.stevemerollis.whereabouts.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.presentation.view.activity.MapsActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    /**
     * Goes to the place map screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToMapsActivity(Context context, PlaceRequestParams placeRequestParams) {
        if (context != null) {
            Intent intentToLaunch = MapsActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}