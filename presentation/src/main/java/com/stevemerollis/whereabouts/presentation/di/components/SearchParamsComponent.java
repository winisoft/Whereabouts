package com.stevemerollis.whereabouts.presentation.di.components;


import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.di.modules.ActivityModule;
import com.stevemerollis.whereabouts.presentation.di.modules.GeocodingResultsModule;
import com.stevemerollis.whereabouts.presentation.di.modules.PlaceTypesModule;
import com.stevemerollis.whereabouts.presentation.view.fragment.GeocodingFragment;
import com.stevemerollis.whereabouts.presentation.view.fragment.SearchParamsFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects fragments used as part of the places functionality.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PlaceTypesModule.class, GeocodingResultsModule.class})
public interface SearchParamsComponent extends ActivityComponent {

    void inject(SearchParamsFragment searchParamsFragment);
    void inject(GeocodingFragment geocodingFragment);
}