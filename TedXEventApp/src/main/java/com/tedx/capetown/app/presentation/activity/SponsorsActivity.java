package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
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

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        setContentView(R.layout.activity_sponsors);
        _context = this;
        SponsorCollectionModel sponsorCollectionModel = (SponsorCollectionModel) EventBus.getDefault().getStickyEvent(SponsorCollectionModel.class);
        if (_sponsorListAdapter == null)
        {
            _sponsorListAdapter = new SponsorListAdapter(sponsorCollectionModel.sponsors, this);
            this.getListView().setAdapter(_sponsorListAdapter);
        }
        else
        {
            _sponsorListAdapter.updateData(sponsorCollectionModel.sponsors);
            _sponsorListAdapter.notifyDataSetChanged();
        }
    }

    public void onPause()
    {
        super.onPause();
    }

    public void onResume()
    {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
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

}