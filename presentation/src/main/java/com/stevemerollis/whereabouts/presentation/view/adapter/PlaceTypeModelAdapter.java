package com.stevemerollis.whereabouts.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.stevemerollis.whereabouts.presentation.R;
import com.stevemerollis.whereabouts.presentation.model.PlaceTypeModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlaceTypeModelAdapter extends RecyclerView.Adapter<PlaceTypeModelAdapter.PlaceTypeViewHolder> {

    private List<PlaceTypeModel> placeTypeModelCollection;
    private final LayoutInflater layoutInflater;

    @Inject
    PlaceTypeModelAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.placeTypeModelCollection = Collections.emptyList();
    }

    @Override
    public PlaceTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_place_type, parent, false);
        return new PlaceTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceTypeViewHolder holder, int position) {
        final PlaceTypeModel placeTypeModel = this.placeTypeModelCollection.get(position);
        holder.checkBox.setText(placeTypeModel.name);
    }

    @Override
    public int getItemCount() {
        return (this.placeTypeModelCollection != null) ? this.placeTypeModelCollection.size() : 0;
    }

    public void setPlaceTypeModelCollection(Collection<PlaceTypeModel> placeTypeModelCollection) {
        if (placeTypeModelCollection == null){
            throw new IllegalArgumentException("The list cannot be null!");
        }

        this.placeTypeModelCollection = (List<PlaceTypeModel>) placeTypeModelCollection;
        this.notifyDataSetChanged();
    }

    static class PlaceTypeViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cb_place_type) CheckBox checkBox;

        PlaceTypeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
