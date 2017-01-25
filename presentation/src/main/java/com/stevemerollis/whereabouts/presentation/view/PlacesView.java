package com.stevemerollis.whereabouts.presentation.view;

import com.stevemerollis.whereabouts.presentation.model.PlaceModel;

import java.util.Collection;

/**
 * Just changing this comment slightly to check I can check in here.
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link PlaceModel}.
 */
public interface PlacesView extends LoadDataView {

    /**
     * Plot a list of places in the UI.
     *
     * @param placeModelCollection The collection of {@link PlaceModel} that will be shown.
     */
    void plotPlaceModels(Collection<PlaceModel> placeModelCollection);
}
