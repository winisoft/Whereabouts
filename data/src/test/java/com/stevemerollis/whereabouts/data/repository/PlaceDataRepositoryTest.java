package com.stevemerollis.whereabouts.data.repository;

import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.data.entity.mapper.PlaceEntityDataMapper;
import com.stevemerollis.whereabouts.data.repository.datasource.PlaceDataStore;
import com.stevemerollis.whereabouts.data.repository.datasource.PlaceDataStoreFactory;
import com.stevemerollis.whereabouts.domain.Place;
import com.stevemerollis.whereabouts.domain.PlaceRequestParams;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PlaceDataRepositoryTest {

    private static final int FAKE_USER_ID = 123;

    private PlaceDataRepository placeDataRepository;

    @Mock private PlaceDataStoreFactory mockPlaceDataStoreFactory;
    @Mock private PlaceEntityDataMapper mockPlaceEntityDataMapper;
    @Mock private PlaceDataStore mockPlaceDataStore;
    @Mock private PlaceEntity mockPlaceEntity;
    @Mock private Place mockPlace;
    @Mock private PlaceRequestParams mockPlaceRequestParams;

    @Before
    public void setUp() {
        placeDataRepository = new PlaceDataRepository(mockPlaceDataStoreFactory, mockPlaceEntityDataMapper);
        given(mockPlaceDataStoreFactory.createCloudDataStore()).willReturn(mockPlaceDataStore);
    }

    @Test
    public void testGetPlaces() {
        List<PlaceEntity> placeList = new ArrayList<>();
        placeList.add(new PlaceEntity());
        given(mockPlaceDataStore.getPlaceEntityList(mockPlaceRequestParams)).willReturn(Observable.just(placeList));

        placeDataRepository.places(mockPlaceRequestParams);

        verify(mockPlaceDataStoreFactory).createCloudDataStore();
        verify(mockPlaceDataStore).getPlaceEntityList(mockPlaceRequestParams);
    }
}
