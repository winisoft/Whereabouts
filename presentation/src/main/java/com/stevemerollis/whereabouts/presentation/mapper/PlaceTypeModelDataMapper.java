package com.stevemerollis.whereabouts.presentation.mapper;

import com.stevemerollis.whereabouts.domain.PlaceType;
import com.stevemerollis.whereabouts.presentation.di.PerActivity;
import com.stevemerollis.whereabouts.presentation.model.PlaceTypeModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link PlaceType} (in the domain layer) to {@link PlaceTypeModel} in the
 * presentation layer.
 */
@PerActivity
public class PlaceTypeModelDataMapper {

    @Inject
    public PlaceTypeModelDataMapper(){}

    /**
     * Transform a {@link PlaceType} into an {@link PlaceTypeModel}.
     *
     * @param placeType Object to be transformed.
     * @return {@link PlaceTypeModel}.
     */
    public PlaceTypeModel transform(PlaceType placeType) {
        if (placeType == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final PlaceTypeModel placeTypeModel = new PlaceTypeModel();

        placeTypeModel.id = placeType.id;
        placeTypeModel.name = placeType.name;
        placeTypeModel.type = placeType.type;

        return placeTypeModel;
    }

    /**
     * Transform a Collection of {@link PlaceType} into a Collection of {@link PlaceTypeModel}.
     *
     * @param placeTypeCollection Objects to be transformed.
     * @return List of {@link PlaceTypeModel}.
     */
    public Collection<PlaceTypeModel> transform(Collection<PlaceType> placeTypeCollection) {
        Collection<PlaceTypeModel> placeTypeModelsCollection;

        if (placeTypeCollection != null && !placeTypeCollection.isEmpty()) {
            placeTypeModelsCollection = new ArrayList<>();
            for (PlaceType placeType : placeTypeCollection) {
                placeTypeModelsCollection.add(transform(placeType));
            }
        } else {
            placeTypeModelsCollection = Collections.emptyList();
        }

        return placeTypeModelsCollection;
    }
}