package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tedx.capetown.app.R;

public class AboutFragment extends Fragment
{

    private OnFragmentInteractionListener _listener;

    public static AboutFragment newInstance()
    {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    public AboutFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_about, container, false);
        return fragmentView;
    }

    public void onButtonPressed(Uri uri)
    {
        if (_listener != null)
            _listener.onFragmentInteraction(uri);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            _listener = (OnFragmentInteractionListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        _listener = null;
    }

    public interface OnFragmentInteractionListener
    {
        public void onFragmentInteraction(Uri uri);
    }

}