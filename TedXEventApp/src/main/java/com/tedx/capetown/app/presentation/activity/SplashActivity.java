package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tedx.capetown.app.DefaultApplication;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.app.core.service.storage.StorageKey;
import com.tedx.capetown.app.facade.FacadeFactory;
import com.tedx.capetown.app.facade.factory.FacadeFactoryImpl;

import java.io.IOException;

import de.greenrobot.event.EventBus;

public class SplashActivity extends Activity {
    boolean dataEvent = false;
    boolean dataSponsor = false;
    boolean dataSpeaker = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        setContentView(R.layout.activity_splash);
        EventBus.getDefault().register(this);
        FacadeFactoryImpl.createSponsorFacade(DefaultApplication.getAppContext()).fetchSponsorList();
        FacadeFactoryImpl.createEventFacade(DefaultApplication.getAppContext()).fetchEventList();
        FacadeFactoryImpl.createSpeakerFacade(DefaultApplication.getAppContext()).fetchSpeakerList();
        ObjectMapper mapper = new ObjectMapper();
        try {
            if(FacadeFactoryImpl.createStorageFacade().read(StorageKey.EventModel)!=null)
            {
                EventBus.getDefault().postSticky(mapper.readValue((String) FacadeFactoryImpl.createStorageFacade().read(StorageKey.EventModel), EventCollectionModel.class));
            }
            if(FacadeFactoryImpl.createStorageFacade().read(StorageKey.SpeakersModel)!=null)
            {
                EventBus.getDefault().postSticky(mapper.readValue((String)FacadeFactoryImpl.createStorageFacade().read(StorageKey.SpeakersModel), SpeakerCollectionModel.class));
            }
            if(FacadeFactoryImpl.createStorageFacade().read(StorageKey.SponsorsModel)!=null)
            {
                EventBus.getDefault().postSticky(mapper.readValue((String)FacadeFactoryImpl.createStorageFacade().read(StorageKey.SponsorsModel), SponsorCollectionModel.class));
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
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

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
    }

    @Override
    public void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }

    public void onEventMainThread(EventCollectionModel eventCollectionModel) {

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(eventCollectionModel);
            FacadeFactoryImpl.createStorageFacade().save(StorageKey.EventModel,json);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        if(dataEvent!=true && dataSpeaker==true && dataSponsor==true)
        {
            dataEvent=true;
            initiateHomeActivity();
        }
        else
        {
            dataEvent=true;
        }
    }

    public void onEventMainThread(SponsorCollectionModel sponsorCollectionModel) {

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(sponsorCollectionModel);
            FacadeFactoryImpl.createStorageFacade().save(StorageKey.SponsorsModel,json);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        if(dataEvent==true && dataSpeaker==true && dataSponsor!=true)
        {
            dataSponsor=true;
            initiateHomeActivity();
        }
        else
        {
            dataSponsor=true;
        }
    }
    public void onEventMainThread(SpeakerCollectionModel speakerCollectionModel) {

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(speakerCollectionModel);
            FacadeFactoryImpl.createStorageFacade().save(StorageKey.SpeakersModel,json);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        if(dataEvent==true && dataSpeaker!=true && dataSponsor==true)
        {
            dataSpeaker=true;
            initiateHomeActivity();
        }
        else
        {
            dataSpeaker=true;
        }
    }
    public void initiateHomeActivity()
    {
        Intent intent = new Intent();
        intent.setClass(this.getApplicationContext(), HomeTabbedActivity.class);
        this.startActivity(intent);
        this.finish();
    }

}