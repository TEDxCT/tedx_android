package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;
import com.tedx.capetown.app.presentation.adapter.TabSectionsPagerAdapter;

import de.greenrobot.event.EventBus;

public class HomeTabbedActivity extends ActionBarActivity implements ActionBar.TabListener, AgendaFragment.OnFragmentInteractionListener, SpeakersFragment.OnFragmentInteractionListener {

    TabSectionsPagerAdapter _SectionsPagerAdapter;
    ViewPager _ViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometabbed);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        _SectionsPagerAdapter = new TabSectionsPagerAdapter(getFragmentManager(), getApplicationContext());
        _ViewPager = (ViewPager) findViewById(R.id.pager);
        _ViewPager.setAdapter(_SectionsPagerAdapter);

        _ViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < _SectionsPagerAdapter.getCount(); i++)
            actionBar.addTab(actionBar.newTab().setText(_SectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
        Log.wtf("HOMETABBED", "_SectionsPagerAdapter.getCount():" + _SectionsPagerAdapter.getCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.tabbed, menu);
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
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        _ViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

}