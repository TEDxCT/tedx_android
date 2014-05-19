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
import com.tedx.capetown.app.core.models.SponsorModel;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;
import com.tedx.capetown.app.presentation.adapter.SponsorListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SponsorsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        populateList();
    }

    private void populateList() {
        ListView listView = this.getListView();
        //(ListView) findViewById(R.id.sponsor_listview);
        List<SponsorModel> models = new ArrayList<SponsorModel>();
        for(int i = 0; i<20;i++)
            models.add(new SponsorModel());
        SponsorListAdapter adapter = new SponsorListAdapter(models, this.getApplicationContext());
        listView.setAdapter(adapter);
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

}
