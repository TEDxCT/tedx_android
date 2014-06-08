package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.SessionCollectionModel;
import com.tedx.capetown.app.core.models.SessionModel;
import com.tedx.capetown.app.core.models.SessionsListModel;
import com.tedx.capetown.app.core.models.TalkModel;
import com.tedx.capetown.app.presentation.adapter.SessionListAdapter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class SessionActivity extends ListActivity {
    ListView listView = null;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        context = this;
        listView = this.getListView();
        SessionListAdapter sessionListAdapter = new SessionListAdapter(getModel(),this);
        setListAdapter(sessionListAdapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show();
                SessionsListModel session = (SessionsListModel)adapterView.getItemAtPosition(pos);
                if(session.isSession())
                {
                    // Do nothing right now
                }
                else
                {
                    Intent intent = new Intent(context,TalkActivity.class);
                    intent.putExtra("talkId",session.talkModel.id);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.session, menu);
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

    public List<SessionsListModel> getModel() {
        EventCollectionModel eventCollectionModel = (EventCollectionModel) EventBus.getDefault().getStickyEvent(EventCollectionModel.class);
        List<SessionModel> sessions = (List<SessionModel>) eventCollectionModel.events.get(0).sessions.sessions;
        List<SessionsListModel> sessionsList = new ArrayList<SessionsListModel>();
        for(SessionModel session : sessions)
        {
            SessionsListModel tempSession = new SessionsListModel();
            tempSession.sessionModel = session;
            sessionsList.add(tempSession);
            for(TalkModel talk : session.talks.talks)
            {
                SessionsListModel tempTalk = new SessionsListModel();
                tempTalk.talkModel = talk;
                tempTalk.child = true;
                sessionsList.add(tempTalk);
            }
        }
        // apply logic to get a model
        return sessionsList;
    }
}
