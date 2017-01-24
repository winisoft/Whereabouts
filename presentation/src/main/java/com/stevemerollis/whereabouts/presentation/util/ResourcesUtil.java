package com.stevemerollis.whereabouts.presentation.util;

import android.content.Context;
import android.util.TypedValue;

public class ResourcesUtil {

    /**
     * Get a float value from resources -- Android implements this slightly differently
     * @param context The context from which we're using this util
     * @param resId The resource ID of the dimen we wish to use
     * @return A float representing a resource value
     */
    public static float getFloat(Context context, int resId){

        TypedValue outValue = new TypedValue();

        context.getResources().getValue(resId, outValue, true);

        return outValue.getFloat();
    }
}
