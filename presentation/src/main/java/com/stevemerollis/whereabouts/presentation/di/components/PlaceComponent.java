package com.stevemerollis.whereabouts.presentation.di.components;

import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.di.modules.ActivityModule;
import com.stevemerollis.whereabouts.presentation.di.modules.PlaceModule;
import com.stevemerollis.whereabouts.presentation.view.fragment.WhereaboutsMapFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PlaceModule.class})
public interface PlaceComponent extends ActivityComponent {
    void inject(WhereaboutsMapFragment placeMapFragment);
}