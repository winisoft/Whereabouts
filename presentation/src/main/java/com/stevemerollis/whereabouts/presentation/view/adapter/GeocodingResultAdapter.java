package com.stevemerollis.whereabouts.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.model.GeocodingResultModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GeocodingResultAdapter extends RecyclerView.Adapter<GeocodingResultAdapter.GeocodingResultViewHolder>{

    private List<GeocodingResultModel> geocodingResultModelCollection;
    private final LayoutInflater layoutInflater;
    private int selectedPosition = 0;

    @Inject
    GeocodingResultAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.geocodingResultModelCollection = Collections.emptyList();
    }

    @Override
    public GeocodingResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.row_geocoding_result, parent, false);
        return new GeocodingResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GeocodingResultViewHolder holder, int position) {
        final GeocodingResultModel geocodingResultModel = geocodingResultModelCollection.get(position);
        final int pos = position;

        holder.tv_formatted_address.setText(geocodingResultModel.formattedAddress);
        holder.tv_formatted_address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectedPosition = pos;
                return false;
            }
        });
        holder.tv_formatted_address.setSelected(position == selectedPosition);
    }

    @Override
    public int getItemCount() {
        return (this.geocodingResultModelCollection != null) ? this.geocodingResultModelCollection.size() : 0;
    }

    public void setGeocodingResultModelCollection(Collection<GeocodingResultModel> geocodingResultModels) {
        if (geocodingResultModels == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }

        this.geocodingResultModelCollection = (List<GeocodingResultModel>) geocodingResultModels;
        this.notifyDataSetChanged();
    }

    static class GeocodingResultViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.gcf_tv_formatted_address) TextView tv_formatted_address;

        GeocodingResultViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
