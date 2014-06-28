package com.tedx.capetown.app.presentation.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tedx.capetown.app.R;

public class AboutFragment extends Fragment {

    private OnFragmentInteractionListener _listener;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    public AboutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_about, container, false);
//        FrameLayout frameLayout = (FrameLayout) fragmentView.findViewById(R.id.sponsors_frame_layout);
//        FragmentManager fragMan = getFragmentManager();
//        FragmentTransaction fragTransaction = fragMan.beginTransaction();
//        SponsorsFragment sponsorsFragment = new SponsorsFragment();
//
//        fragTransaction.add(frameLayout.getId(), sponsorsFragment, "fragment added");
//        fragTransaction.commit();
        return fragmentView;
    }

    public void onButtonPressed(Uri uri) {
        if (_listener != null)
            _listener.onFragmentInteraction(uri);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try
//        {
//            _listener = (OnFragmentInteractionListener) activity;
//        }
//        catch (ClassCastException e)
//        {
//            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
//        }
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

}