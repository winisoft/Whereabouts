package com.stevemerollis.whereabouts.data.entity.mapper;

import com.google.gson.JsonSyntaxException;
import com.stevemerollis.whereabouts.data.entity.PlaceEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PlaceEntityJsonMapperTest {

    private static final String JSON_RESPONSE = "[{\"latitude\":42.48908369999999,\"longitude\":-83.1440762,\"icon\":\"http://google.com/images/1.jpg\",\"googleId\":\"asdfgh\",\"name\":\"Andiamo\",\"openNow\":true,\"googlePlaceId\":\"lkjhgf\",\"types\":[\"restaurant\",\"bar\",\"establishment\"],\"whereaboutsComments\":\"I like it a lot.  Not because I've actually been there, but because it's convenient for the purposes of this demonstration.\",\"whereaboutsRating\":4.5,\"whereaboutsHaveVisited\":true,\"vicinity\":\"129 South Main Street, Royal Oak\"},{\"latitude\":42.48526939999999,\"longitude\":-83.1467365,\"icon\":\"http://google.com/images/1.jpg\",\"googleId\":\"zxcvbn\",\"name\":\"Pronto!\",\"openNow\":true,\"googlePlaceId\":\"mnbvcc\",\"types\":[\"restaurant\",\"bar\",\"establishment\"],\"whereaboutsComments\":\"I hated it.  My food was cold and the waiter quit his job while I was ordering.\",\"whereaboutsRating\":2,\"whereaboutsHaveVisited\":true,\"vicinity\":\"608 South Washington Avenue, Royal Oak\"}]";
    private PlaceEntityJsonMapper placeEntityJsonMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        placeEntityJsonMapper = new PlaceEntityJsonMapper();
    }

    @Test
    public void testTransformPlaceEntityCollectionHappyCase() {
        Collection<PlaceEntity> placeEntities =
                placeEntityJsonMapper.transformPlaceApiResponse(
                        JSON_RESPONSE);

        assertThat(((PlaceEntity) placeEntities.toArray()[0]).googleId, is("asdfgh"));
        assertThat(((PlaceEntity) placeEntities.toArray()[1]).googleId, is("zxcvbn"));
        assertThat(placeEntities.size(), is(2));
    }

    @Test
    public void testTransformPlaceEntityCollectionNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        placeEntityJsonMapper.transformPlaceApiResponse("Garbage data.");
    }
}
