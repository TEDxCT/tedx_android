package com.tedx.capetown.app.presentation.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.EventModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.greenrobot.event.EventBus;

public class EventFragment extends Fragment {

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
        View fragmentView = inflater.inflate(R.layout.fragment_event, container, false);
        setupUI(eventModel,fragmentView);
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
        Date startDate = new Date(Long.parseLong(model.endDate)*1000);
        TextView eventName = (TextView) view.findViewById(R.id.event_name);
        TextView eventDate = (TextView) view.findViewById(R.id.event_date);
        TextView eventTime = (TextView) view.findViewById(R.id.event_time);
        ImageView eventPicture = (ImageView) view.findViewById(R.id.event_picture);
        eventName.setText(model.name);
        eventDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(startDate));
        eventTime.setText(new SimpleDateFormat("kk:mm").format(startDate));
        ImageLoader.getInstance().displayImage(model.imageURL,eventPicture);
    }



}