package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SponsorsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        ListView lv = (ListView)findViewById(R.id.sponsor_listview);
        List<SpeakerModel> list = new ArrayList<SpeakerModel>();
        for(int i = 0;i<100;i++) {
            list.add(new SpeakerModel(i));
        }
        SpeakerListAdapter sla = new SpeakerListAdapter(list);
        lv.setAdapter(sla);
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
