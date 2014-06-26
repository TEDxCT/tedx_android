package com.tedx.capetown.app.presentation.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;

import de.greenrobot.event.EventBus;

public class SpeakersFragment extends ListFragment {

    SpeakerListAdapter _listAdapter = null;
    private OnFragmentInteractionListener _listener;

    public static SpeakersFragment newInstance() {
        return new SpeakersFragment();
    }

    public SpeakersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_speakers, container, false);
        return fragmentView;
    }

    public void onButtonPressed(Uri uri) {
        if (_listener != null)
            _listener.onFragmentInteraction(uri);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            _listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SpeakerCollectionModel speakerCollectionModel = (SpeakerCollectionModel) EventBus.getDefault().getStickyEvent(SpeakerCollectionModel.class);
        _listAdapter = new SpeakerListAdapter(speakerCollectionModel.speakers, getActivity());
        this.getListView().setAdapter(_listAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _listener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}