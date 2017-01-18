package com.stevemerollis.whereabouts.data.entity.mapper;

import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.domain.Place;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class PlaceEntityDataMapper {

    @Inject
    PlaceEntityDataMapper(){}

    public Place transform(PlaceEntity placeEntity){
        Place place = null;

        if (placeEntity != null){
            place = new Place();
            place.setLatitude(placeEntity.latitude);
            place.setLongitude(placeEntity.longitude);
            place.setIcon(placeEntity.icon);
            place.setGoogleId(placeEntity.googleId);
            place.setName(placeEntity.name);
            place.setOpenNow(placeEntity.openNow);
            place.setGooglePlaceId(placeEntity.googlePlaceId);
            place.setTypes(placeEntity.types);
            place.setWhereaboutsComments(placeEntity.whereaboutsComments);
            place.setWhereaboutsRating(placeEntity.whereaboutsRating);
            place.setHaveVisited(placeEntity.whereaboutsHaveVisited);
        }

        return place;
    }

    public List<Place> transform(Collection<PlaceEntity> placeEntityCollection){
        List<Place> placeList = new ArrayList<>();

        for (PlaceEntity placeEntity : placeEntityCollection){
            final Place place = transform(placeEntity);

            if (place != null){
                placeList.add(place);
            }
        }

        return placeList;
    }
}
