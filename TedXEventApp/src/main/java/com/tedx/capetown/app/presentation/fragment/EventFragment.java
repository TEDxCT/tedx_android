package com.tedx.capetown.app.presentation.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.type.MapType;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tedx.capetown.app.DefaultApplication;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.EventModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.greenrobot.event.EventBus;

public class EventFragment extends Fragment {
    TedxMapFragment mapFragment;
    private OnFragmentInteractionListener _listener;

    public static EventFragment newInstance() {
        return new EventFragment();
    }
    EventModel eventModel;
    public EventFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView ;
        if(container.findViewWithTag("EventFragement")==null)
            fragmentView = inflater.inflate(R.layout.fragment_event, container, false);
        else
            fragmentView = container.findViewWithTag("EventFragement");

        setupUI(eventModel,fragmentView);
        fragmentView.setTag("EventFragment");
        return fragmentView;
    }

    public void onButtonPressed(Uri uri) {
        if (_listener != null)
            _listener.onFragmentInteraction(uri);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        eventModel = getModel();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _listener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    public EventModel getModel()
    {
        EventCollectionModel eventCollectionModel = (EventCollectionModel) EventBus.getDefault().getStickyEvent(EventCollectionModel.class);
        if(eventCollectionModel.events.get(0)!=null)
        {
            return eventCollectionModel.events.get(0);
        }
        return null;
    }
    public void setupUI(EventModel model, View view)
    {
        // Server sends time in seconds - can change on server or leave with seconds
        Date startDate = new Date(Long.parseLong(model.startDate)*1000);
        TextView eventName = (TextView) view.findViewById(R.id.event_name);
        TextView eventDate = (TextView) view.findViewById(R.id.event_date);
        TextView eventTime = (TextView) view.findViewById(R.id.event_time);
        TextView eventAddress = (TextView) view.findViewById(R.id.event_address1);
        ImageView eventPicture = (ImageView) view.findViewById(R.id.event_picture);
        FragmentManager mFragementManager = getActivity().getFragmentManager();
        LatLng eventPosition = new LatLng(eventModel.latitude, eventModel.longitude);

        mapFragment = new TedxMapFragment();
        mapFragment.setEventModel(eventModel);
        FragmentTransaction transaction =
                mFragementManager.beginTransaction();
        transaction.add(R.id.event_map, mapFragment, "MapFragment").commit();
        eventName.setText(model.name);
        eventDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(startDate));
        eventTime.setText(new SimpleDateFormat("kk:mm").format(startDate));
        ImageLoader.getInstance().displayImage(model.imageURL,eventPicture);
        Geocoder geo;
        geo = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geo.getFromLocation(eventModel.latitude, eventModel.longitude, 1);

            if (addresses.isEmpty()) {
                eventAddress.setText("Waiting for Location");
            }
            else {
                if (addresses.size() > 0) {
                    eventAddress.setText(addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality() +", " + addresses.get(0).getAdminArea() + ", " + addresses.get(0).getCountryName());
                }
            }
        } catch (IOException e) {
        }
    }
}