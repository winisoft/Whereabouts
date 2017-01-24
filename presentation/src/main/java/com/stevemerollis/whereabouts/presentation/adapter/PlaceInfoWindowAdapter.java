package com.stevemerollis.whereabouts.presentation.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.model.PlaceModel;

public class PlaceInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

    private Context context;

    public PlaceInfoWindowAdapter(Context context){
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    // Defines the contents of the InfoWindow
    @Override
    public View getInfoContents(Marker arg0) {

        PlaceModel place = (PlaceModel)arg0.getTag();
        View v = ((Activity)context).getLayoutInflater().inflate(R.layout.fragment_place_detail, null);

        TextView tvName = (TextView) v.findViewById(R.id.tv_place_name);
        TextView tvVicinity = (TextView) v.findViewById(R.id.tv_place_vicinity);
        RatingBar rbWhereaboutsRating = (RatingBar) v.findViewById(R.id.rb_whereabouts_rating);
        TextView tvComments = (TextView) v.findViewById(R.id.tv_place_comments);

        tvName.setText(place.getName());
        tvVicinity.setText(place.getVicinity());
        rbWhereaboutsRating.setRating((float)place.getWhereaboutsRating());
        tvComments.setText(place.getWhereaboutsComments());

        return v;

    }
}
