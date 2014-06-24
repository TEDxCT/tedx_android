package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
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
import com.tedx.capetown.app.facade.factory.FacadeFactoryImpl;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;

import de.greenrobot.event.EventBus;

public class SpeakerListActivity extends ListActivity {

    SpeakerListAdapter _speakerListAdapter = null;
    Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_list);
        _context = this;

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        SpeakerCollectionModel speakerCollectionModel = (SpeakerCollectionModel) EventBus.getDefault().getStickyEvent(SpeakerCollectionModel.class);
        if (speakerCollectionModel != null) {
            onEventMainThread(speakerCollectionModel);
            FacadeFactoryImpl.createSpeakerFacade(this).fetchSpeakerList();
        } else
            FacadeFactoryImpl.createSpeakerFacade(this).fetchSpeakerList();
    }

    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void onEventMainThread(SpeakerCollectionModel speakerCollectionModel) {
        if (_speakerListAdapter == null) {
            _speakerListAdapter = new SpeakerListAdapter(speakerCollectionModel.speakers, this);
            this.getListView().setAdapter(_speakerListAdapter);
        } else
            _speakerListAdapter.updateData(speakerCollectionModel.speakers);

        _speakerListAdapter.notifyDataSetChanged();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(_context, "Clicked", Toast.LENGTH_LONG).show();
                SpeakerModel speakerModel = (SpeakerModel) adapterView.getItemAtPosition(pos);
                Intent intent = new Intent(_context, SpeakerProfileActivity.class);
                intent.putExtra("speakerId", speakerModel.id);
                startActivity(intent);
            }
        });
    }

}