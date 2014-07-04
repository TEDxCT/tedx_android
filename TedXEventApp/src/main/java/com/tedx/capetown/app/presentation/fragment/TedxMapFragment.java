package com.tedx.capetown.app.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tedx.capetown.app.core.models.EventModel;

/**
 * Created by andrewpettey on 2014/07/03.
 */
public class TedxMapFragment extends MapFragment {
    EventModel eventModel;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  super.onCreateView ( inflater, container,  savedInstanceState);

        LatLng eventPosition = new LatLng(eventModel.latitude, eventModel.longitude);
        this.getMap().getUiSettings().setAllGesturesEnabled(false);
        this.getMap().setMyLocationEnabled(false);
        this.getMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(eventPosition, 15));
        this.getMap().addMarker(new MarkerOptions().position(eventPosition).title(eventModel.name));
        return view;
    }
    public void setEventModel(EventModel eventModel)
    {
        this.eventModel = eventModel;
    }
}
