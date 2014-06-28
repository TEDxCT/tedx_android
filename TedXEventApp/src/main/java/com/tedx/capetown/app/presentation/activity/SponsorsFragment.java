package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;
import com.tedx.capetown.app.presentation.adapter.SponsorListAdapter;
import de.greenrobot.event.EventBus;

public class SponsorsFragment extends ListFragment
{

    SponsorListAdapter _sponsorListAdapter = null;
    private OnFragmentInteractionListener _listener;

    public static SponsorsFragment newInstance() {
        return new SponsorsFragment();
    }

    public SponsorsFragment()  {  }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_sponsors, container, false);
  //      FrameLayout frameLayout = (FrameLayout) fragmentView.findViewById(R.id.sponsors_frame_layout);
  //      FragmentManager fragMan = getFragmentManager();
  //      FragmentTransaction fragTransaction = fragMan.beginTransaction();
       // SponsorsFragment sponsorsFragment = new SponsorsFragment();

 //       fragTransaction.add(frameLayout.getId(), this, "fragment added");  //sponsorsFragment
 //       fragTransaction.commit();
        return fragmentView;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        SponsorCollectionModel sponsorCollectionModel = (SponsorCollectionModel) EventBus.getDefault().getStickyEvent(SponsorCollectionModel.class);
        _sponsorListAdapter = new SponsorListAdapter(sponsorCollectionModel.sponsors, getActivity());
        this.getListView().setAdapter(_sponsorListAdapter);

//        SponsorCollectionModel sponsorCollectionModel = (SponsorCollectionModel) EventBus.getDefault().getStickyEvent(SponsorCollectionModel.class);
//        if (_sponsorListAdapter == null)
//        {
//            _sponsorListAdapter = new SponsorListAdapter(sponsorCollectionModel.sponsors, getActivity());
//            this.getListView().setAdapter(_sponsorListAdapter);
//        }
//        else
//        {
//            _sponsorListAdapter.updateData(sponsorCollectionModel.sponsors);
//            _sponsorListAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void onAttach(Activity activity)
    {
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