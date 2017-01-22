package com.stevemerollis.whereabouts.data.entity.mapper;

import com.stevemerollis.whereabouts.data.entity.PlaceEntity;
import com.stevemerollis.whereabouts.domain.Place;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PlaceEntityDataMapperTest {

    private static final String FAKE_GOOGLE_ID = "fdsjklfds";
    private static final String FAKE_NAME = "nvmcxdsf";

    private PlaceEntityDataMapper placeEntityDataMapper;

    @Before
    public void setUp() throws Exception {
        placeEntityDataMapper = new PlaceEntityDataMapper();
    }

    @Test
    public void testTransformUserEntity() {
        PlaceEntity placeEntity = getPlaceEntityMock();
        Place place = placeEntityDataMapper.transform(placeEntity);

        assertThat(place, is(instanceOf(Place.class)));
        assertThat(place.getGoogleId(), is(FAKE_GOOGLE_ID));
        assertThat(place.getName(), is(FAKE_NAME));
    }

    @Test
    public void testTransformPlaceEntityCollection() {
        PlaceEntity placeEntityOne = mock(PlaceEntity.class);
        PlaceEntity placeEntityTwo = mock(PlaceEntity.class);

        List<PlaceEntity> placeEntityList = new ArrayList<>(5);
        placeEntityList.add(placeEntityOne);
        placeEntityList.add(placeEntityTwo);

        Collection<Place> userCollection = placeEntityDataMapper.transform(placeEntityList);

        assertThat(userCollection.toArray()[0], is(instanceOf(Place.class)));
        assertThat(userCollection.toArray()[1], is(instanceOf(Place.class)));
        assertThat(userCollection.size(), is(2));
    }

    @Test
    public void testTransformNullEntityReturnsNull() {
        PlaceEntity placeEntity = null;
        Place place = placeEntityDataMapper.transform(placeEntity);

        assertThat(place, is(nullValue()));
    }

    private PlaceEntity getPlaceEntityMock() {
        PlaceEntity placeEntity = new PlaceEntity();
        placeEntity.googleId = FAKE_GOOGLE_ID;
        placeEntity.name = FAKE_NAME;

        return placeEntity;
    }
}
