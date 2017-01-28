package com.stevemerollis.whereabouts.data.entity.mapper.placetype;

import com.stevemerollis.whereabouts.data.entity.PlaceTypeEntity;
import com.stevemerollis.whereabouts.domain.PlaceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link PlaceTypeEntity} in the data layer to {@link PlaceType} in the
 * domain layer.
 */
public class PlaceTypeEntityDataMapper {

    @Inject
    public PlaceTypeEntityDataMapper(){}

    /**
     * Transform a {@link PlaceTypeEntity} into an {@link PlaceType}.
     *
     * @param placeTypeEntity Object to be transformed.
     * @return {@link PlaceType}, provided a valid {@link PlaceTypeEntity}, otherwise null.
     */
    public PlaceType transform(PlaceTypeEntity placeTypeEntity){
        PlaceType placeType = null;

        if (placeTypeEntity != null){
            placeType = new PlaceType();
            placeType.id = placeTypeEntity.id;
            placeType.name = placeTypeEntity.name;
            placeType.type = placeTypeEntity.type;
        }

        return placeType;
    }

    public List<PlaceType> transform(Collection<PlaceTypeEntity> placeTypeEntityCollection){
        List<PlaceType> placeTypeList = new ArrayList<>();

        for (PlaceTypeEntity placeTypeEntity : placeTypeEntityCollection){
            final PlaceType placeType = transform(placeTypeEntity);

            if (placeType != null){
                placeTypeList.add(placeType);
            }
        }

        return placeTypeList;
    }
}
