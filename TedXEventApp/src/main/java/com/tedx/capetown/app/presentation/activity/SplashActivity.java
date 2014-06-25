package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.models.SponsorCollectionModel;
import com.tedx.capetown.app.facade.factory.FacadeFactoryImpl;

import de.greenrobot.event.EventBus;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        setContentView(R.layout.activity_splash);
        EventBus.getDefault().register(this);
        FacadeFactoryImpl.createSponsorFacade(this).fetchSponsorList();
        FacadeFactoryImpl.createEventFacade(this).fetchEventList();
        FacadeFactoryImpl.createSpeakerFacade(this).fetchSpeakerList();
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
        if(EventBus.getDefault().getStickyEvent(SponsorCollectionModel.class)!=null)
        {
            initiateHomeActivity();
        }
    }

    public void onEventMainThread(SponsorCollectionModel sponsorCollectionModel) {
        if(EventBus.getDefault().getStickyEvent(EventCollectionModel.class)!=null)
        {
            initiateHomeActivity();
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