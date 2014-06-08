package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
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
import com.tedx.capetown.app.core.models.SessionsListModel;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.facade.factory.FacadeFactoryImpl;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;
import com.tedx.capetown.lib.sdk.SDKClient;

import de.greenrobot.event.EventBus;

public class SpeakerListActivity extends ListActivity {
    SpeakerListAdapter speakerListAdapter = null;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_list);
        context = this;
        SpeakerCollectionModel speakerCollectionModel = (SpeakerCollectionModel) EventBus.getDefault().getStickyEvent(SpeakerCollectionModel.class);
        if (speakerCollectionModel != null) {
            onEventMainThread(speakerCollectionModel);
            FacadeFactoryImpl.createSpeakerFacade(this).fetchSpeakerList();
        } else
        {
            FacadeFactoryImpl.createSpeakerFacade(this).fetchSpeakerList();
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
        getMenuInflater().inflate(R.menu.speaker_list, menu);
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

    public void onEventMainThread(SpeakerCollectionModel speakerCollectionModel){
        if(speakerListAdapter==null) {
            speakerListAdapter = new SpeakerListAdapter(speakerCollectionModel.speakers, this);
            this.getListView().setAdapter(speakerListAdapter);
        }
        else {
            speakerListAdapter.updateData(speakerCollectionModel.speakers);
        }
        speakerListAdapter.notifyDataSetChanged();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
                SpeakerModel speakerModel = (SpeakerModel)adapterView.getItemAtPosition(pos);
                    Intent intent = new Intent(context,SpeakerProfileActivity.class);
                    intent.putExtra("speakerId",speakerModel.id);
                    startActivity(intent);
            }
        });
    }

}
