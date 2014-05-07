package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tedx.capetown.app.R;

import java.util.ArrayList;
import java.util.List;

public class SponsorsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);

        populateList();
    }

    private void populateList() {
        ListView listView = (ListView) findViewById(R.id.sponsor_listview);
        List<SponsorActivity> listItems = new ArrayList<SponsorActivity>();
        for(int i = 0; i < 20; i++)
            listItems.add(new SponsorActivity());
        ArrayAdapter<SponsorActivity> adapter = new ArrayAdapter<SponsorActivity>(this, R.layout.listview_sponsor, listItems);
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
