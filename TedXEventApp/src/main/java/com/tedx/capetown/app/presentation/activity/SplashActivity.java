package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tedx.capetown.app.DefaultApplication;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.app.core.service.error.EventErrorResponse;
import com.tedx.capetown.app.core.service.error.SpeakerErrorResponse;
import com.tedx.capetown.app.core.service.error.SponsorErrorResponse;
import com.tedx.capetown.app.core.service.storage.StorageKey;
import com.tedx.capetown.app.facade.factory.FacadeFactoryImpl;
import com.tedx.capetown.app.presentation.fragment.error.ErrorFragment;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

public class SplashActivity extends FragmentActivity {
    boolean dataEvent = false;
    boolean dataSponsor = false;
    boolean dataSpeaker = false;
    int waitPeriod = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.hide();
    }

    private void SetupSplashActivity() {
        if(!hasActiveInternetConnection())
        {
            ErrorFragment.DisplayFatalError(this, "Please note the application does require an internet connection");
        }
        EventBus.getDefault().register(this);

        FacadeFactoryImpl.createSponsorFacade(DefaultApplication.getAppContext()).fetchSponsorList();
        FacadeFactoryImpl.createEventFacade(DefaultApplication.getAppContext()).fetchEventList();
        FacadeFactoryImpl.createSpeakerFacade(DefaultApplication.getAppContext()).fetchSpeakerList();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String eventData = (String) FacadeFactoryImpl.createStorageFacade().read(StorageKey.EventModel);
            String speakerData = (String) FacadeFactoryImpl.createStorageFacade().read(StorageKey.SpeakersModel);
            String sponsorData = (String)FacadeFactoryImpl.createStorageFacade().read(StorageKey.SponsorsModel);
            if(!TextUtils.isEmpty(eventData))
            {
                EventBus.getDefault().postSticky(mapper.readValue(eventData, EventCollectionModel.class));
            }
            if(!TextUtils.isEmpty(speakerData))
            {
                EventBus.getDefault().postSticky(mapper.readValue(speakerData, SpeakerCollectionModel.class));
            }
            if(!TextUtils.isEmpty(sponsorData))
            {
                EventBus.getDefault().postSticky(mapper.readValue(sponsorData, SponsorCollectionModel.class));
            }
        } catch (Exception e) {
            FacadeFactoryImpl.createStorageFacade().remove(StorageKey.SponsorsModel);
            FacadeFactoryImpl.createStorageFacade().remove(StorageKey.SpeakersModel);
            FacadeFactoryImpl.createStorageFacade().remove(StorageKey.EventModel);
            ErrorFragment.DisplayFatalError(this, "An error occurred - please restart the app.");
        }
    }
    @Override
    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                SetupSplashActivity();
            }
        }, (waitPeriod));
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


    public void initiateHomeActivity()
    {
        Intent intent = new Intent();
        intent.setClass(this.getApplicationContext(), HomeTabbedActivity.class);
        this.startActivity(intent);
        this.finish();
    }
    public boolean hasActiveInternetConnection() {
        return isNetworkAvailable();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null)
            return activeNetworkInfo.isAvailable();
        else
            return false;
    }

    public void onEventMainThread(SpeakerErrorResponse speakerErrorResponse)
    {
        ErrorFragment.DisplayFatalError(this,"Can not connect to servers - please try again later");
    }

    public void onEventMainThread(SponsorErrorResponse sponsorErrorResponse)
    {
        ErrorFragment.DisplayFatalError(this,"Can not connect to servers - please try again later");
    }

    public void onEventMainThread(EventErrorResponse eventErrorResponse)
    {
        ErrorFragment.DisplayFatalError(this,"Can not connect to servers - please try again later");
    }

    public void onEventMainThread(EventCollectionModel eventCollectionModel) {

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(eventCollectionModel);
            FacadeFactoryImpl.createStorageFacade().save(StorageKey.EventModel,json);
        } catch (Exception e) {
            ErrorFragment.DisplayFatalError(this,"Error collecting data, please try again later");
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
        } catch (Exception e) {
            ErrorFragment.DisplayFatalError(this,"Error collecting data, please try again later");
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
        } catch (Exception e) {
            ErrorFragment.DisplayFatalError(this,"Error collecting data, please try again later");
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
}