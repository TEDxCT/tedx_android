package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.presentation.adapter.SpeakerListAdapter;

import de.greenrobot.event.EventBus;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle state = this.getIntent().getExtras();
        final Activity context = this;

        if(state!=null)
            if(state.containsKey("key"))
                Toast.makeText(this, state.getString("key"), Toast.LENGTH_LONG).show();

        this.findViewById(R.id.txt_home_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context,SpeakerListActivity.class);
                startActivity(intent);
                context.setResult(context.RESULT_OK, intent);
                context.finish();
            }
        });

        this.findViewById(R.id.btn_speaker_profile_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SpeakerProfileActivity.class);
                intent.putExtra("speakerId",1);
                startActivity(intent);
            }
        });

        this.findViewById(R.id.btn_sponsors_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context,SponsorsActivity.class);
                startActivity(intent);
                context.setResult(context.RESULT_OK, intent);
                context.finish();
            }
        });

        this.findViewById(R.id.btn_agenda_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AgendaActivity.class);
                startActivity(intent);
            }
        });

        this.findViewById(R.id.btn_speaker_profile_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SpeakerProfileActivity.class);
                intent.putExtra("speakerId",0);
                startActivity(intent);
            }
        });

        this.findViewById(R.id.btn_talk_profile_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,TalkActivity.class);
                intent.putExtra("talkId",0);
                startActivity(intent);
            }
        });

        EventBus.getDefault().register(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

    public void onEventMainThread(EventCollectionModel eventCollectionModel){
        Toast.makeText(this,eventCollectionModel.events.size()+"TEST",Toast.LENGTH_LONG).show();
        Toast.makeText(this,eventCollectionModel.events.get(0).id+"TEST",Toast.LENGTH_LONG).show();
    }

}
