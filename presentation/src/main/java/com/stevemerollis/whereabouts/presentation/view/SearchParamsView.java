package com.stevemerollis.whereabouts.presentation.view;

import com.stevemerollis.whereabouts.domain.PlaceRequestParams;
import com.stevemerollis.whereabouts.presentation.model.PlaceTypeModel;

import java.util.Collection;

public interface SearchParamsView extends LoadDataView {

    void setOpenAtTime(String openAtTime);
    void renderPlaceTypesList(Collection<PlaceTypeModel> placeTypeModelsCollection);
    void requestSearch(PlaceRequestParams placeRequestParams);
}
