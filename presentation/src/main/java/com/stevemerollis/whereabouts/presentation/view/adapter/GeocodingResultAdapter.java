package com.stevemerollis.whereabouts.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stevemerollis.whereabouts.presentation.model.GeocodingResultModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class GeocodingResultAdapter extends RecyclerView.Adapter<GeocodingResultAdapter.GeocodingResultViewHolder>{

    private List<GeocodingResultModel> geocodingResultModelCollection;
    private final LayoutInflater layoutInflater;

    @Inject
    GeocodingResultAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.geocodingResultModelCollection = Collections.emptyList();
    }

    @Override
    public GeocodingResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GeocodingResultViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class GeocodingResultViewHolder extends RecyclerView.ViewHolder {

        GeocodingResultViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
