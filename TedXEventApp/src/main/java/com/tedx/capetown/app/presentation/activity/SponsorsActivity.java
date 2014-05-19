package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.app.core.models.SponsorModel;
import com.tedx.capetown.app.facade.factory.FacadeFactoryImpl;
import com.tedx.capetown.app.presentation.adapter.SponsorListAdapter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class SponsorsActivity extends ListActivity {

    SponsorListAdapter sponsorListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        SponsorCollectionModel sponsorCollectionModel = (SponsorCollectionModel) EventBus.getDefault().getStickyEvent(SponsorCollectionModel.class);
        if (sponsorCollectionModel != null) 
        {
            onEventMainThread(sponsorCollectionModel);
            FacadeFactoryImpl.createSponsorFacade(this).fetchSponsorList();
        }
        else
        {
            FacadeFactoryImpl.createSponsorFacade(this).fetchSponsorList();
        }
    }

    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onResume(){
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sponsors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEventMainThread(SponsorCollectionModel sponsorCollectionModel)
    {
        if(sponsorListAdapter == null)
        {
            sponsorListAdapter = new SponsorListAdapter(sponsorCollectionModel.sponsors, this);
            this.getListView().setAdapter(sponsorListAdapter);
        }
        else
        {
            sponsorListAdapter.updateData(sponsorCollectionModel.sponsors);
        }
        sponsorListAdapter.notifyDataSetChanged();
    }

}