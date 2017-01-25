package com.stevemerollis.whereabouts.presentation.mapper;

import com.stevemerollis.whereabouts.domain.Place;
import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.model.PlaceModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link Place} (in the domain layer) to {@link PlaceModel} in the
 * presentation layer.
 */
@PerActivity
public class PlaceModelDataMapper {

    @Inject
    public PlaceModelDataMapper(){}

    /**
     * Transform a {@link Place} into an {@link PlaceModel}.
     *
     * @param place Object to be transformed.
     * @return {@link PlaceModel}.
     */
    public PlaceModel transform(Place place) {
        if (place == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final PlaceModel placeModel = new PlaceModel();

        placeModel.setLatitude(place.getLatitude());
        placeModel.setLongitude(place.getLongitude());
        placeModel.setIcon(place.getIcon());
        placeModel.setGoogleId(place.getGoogleId());
        placeModel.setName(place.getName());
        placeModel.setOpenNow(place.isOpenNow());
        placeModel.setGooglePlaceId(place.getGooglePlaceId());
        placeModel.setTypes(place.getTypes());
        placeModel.setVicinity(place.getVicinity());
        placeModel.setWhereaboutsComments(place.getWhereaboutsComments());
        placeModel.setWhereaboutsRating(place.getWhereaboutsRating());
        placeModel.setWhereaboutsHaveVisited(place.getHaveVisited());

        return placeModel;
    }

    /**
     * Transform a Collection of {@link Place} into a Collection of {@link PlaceModel}.
     *
     * @param placeCollection Objects to be transformed.
     * @return List of {@link PlaceModel}.
     */
    public Collection<PlaceModel> transform(Collection<Place> placeCollection) {
        Collection<PlaceModel> placeModelsCollection;

        if (placeCollection != null && !placeCollection.isEmpty()) {
            placeModelsCollection = new ArrayList<>();
            for (Place place : placeCollection) {
                placeModelsCollection.add(transform(place));
            }
        } else {
            placeModelsCollection = Collections.emptyList();
        }

        return placeModelsCollection;
    }
}
