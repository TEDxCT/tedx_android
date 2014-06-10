package com.tedx.capetown.app.presentation.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.app.core.models.SponsorModel;
import com.tedx.capetown.app.facade.factory.FacadeFactoryImpl;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;
import com.tedx.capetown.app.presentation.adapter.SponsorListAdapter;

import de.greenrobot.event.EventBus;

public class SponsorsActivity extends ListActivity
{

    SponsorListAdapter _sponsorListAdapter = null;
    Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        _context = this;
        SponsorCollectionModel sponsorCollectionModel = (SponsorCollectionModel) EventBus.getDefault().getStickyEvent(SponsorCollectionModel.class);
        if (sponsorCollectionModel != null) 
        {
            onEventMainThread(sponsorCollectionModel);
            FacadeFactoryImpl.createSponsorFacade(this).fetchSponsorList();
        }
        else
            FacadeFactoryImpl.createSponsorFacade(this).fetchSponsorList();
    }

    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.sponsors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void onEventMainThread(SponsorCollectionModel sponsorCollectionModel)
    {
        if(_sponsorListAdapter == null)
        {
            _sponsorListAdapter = new SponsorListAdapter(sponsorCollectionModel.sponsors, this);
            this.getListView().setAdapter(_sponsorListAdapter);
        }
        else
            _sponsorListAdapter.updateData(sponsorCollectionModel.sponsors);
        _sponsorListAdapter.notifyDataSetChanged();
    }

}