package com.stevemerollis.whereabouts.presentation.di.components;

import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.di.modules.ActivityModule;
import com.stevemerollis.whereabouts.presentation.di.modules.PlaceModule;
import com.stevemerollis.whereabouts.presentation.view.fragment.PlacesFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects fragments used as part of the places functionality.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PlaceModule.class})
public interface PlaceComponent extends ActivityComponent {

    void inject(PlacesFragment placeMapFragment);
}