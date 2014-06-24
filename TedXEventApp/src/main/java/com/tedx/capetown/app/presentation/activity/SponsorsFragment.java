package com.tedx.capetown.app.presentation.activity;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.app.presentation.adapter.SponsorListAdapter;

import de.greenrobot.event.EventBus;

public class SponsorsFragment extends ListFragment {

    SponsorListAdapter _sponsorListAdapter = null;

    public static SponsorsFragment newInstance() {
        return new SponsorsFragment();
    }

    public SponsorsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sponsors, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        SponsorCollectionModel sponsorCollectionModel = (SponsorCollectionModel) EventBus.getDefault().getStickyEvent(SponsorCollectionModel.class);
        if (_sponsorListAdapter == null) {
            _sponsorListAdapter = new SponsorListAdapter(sponsorCollectionModel.sponsors, getActivity());
            this.getListView().setAdapter(_sponsorListAdapter);
        } else {
            _sponsorListAdapter.updateData(sponsorCollectionModel.sponsors);
            _sponsorListAdapter.notifyDataSetChanged();
        }
    }

}