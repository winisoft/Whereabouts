package com.stevemerollis.whereabouts.presentation.util;

import android.content.Context;
import android.util.TypedValue;

public class ResourcesUtil {

    public static float getFloat(Context context, int resId){

        TypedValue outValue = new TypedValue();

        context.getResources().getValue(resId, outValue, true);

        return outValue.getFloat();
    }
}
