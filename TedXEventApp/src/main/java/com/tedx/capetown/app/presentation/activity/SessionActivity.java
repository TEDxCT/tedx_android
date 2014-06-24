package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.SessionModel;
import com.tedx.capetown.app.core.models.SessionsListModel;
import com.tedx.capetown.app.core.models.TalkModel;
import com.tedx.capetown.app.presentation.adapter.SessionListAdapter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class SessionActivity extends ListActivity {
    ListView listView = null;
    Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        _context = this;
        listView = this.getListView();

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        SessionListAdapter sessionListAdapter = new SessionListAdapter(getModel(), this);
        setListAdapter(sessionListAdapter);
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

    public List<SessionsListModel> getModel() {
        EventCollectionModel eventCollectionModel = (EventCollectionModel) EventBus.getDefault().getStickyEvent(EventCollectionModel.class);
        List<SessionModel> sessions = (List<SessionModel>) eventCollectionModel.events.get(0).sessions.sessions;
        List<SessionsListModel> sessionsList = new ArrayList<SessionsListModel>();
        for (SessionModel session : sessions) {
            SessionsListModel tempSession = new SessionsListModel();
            tempSession.sessionModel = session;
            sessionsList.add(tempSession);
            for (TalkModel talk : session.talks.talks) {
                SessionsListModel tempTalk = new SessionsListModel();
                tempTalk.talkModel = talk;
                tempTalk.child = true;
                sessionsList.add(tempTalk);
            }
        }
        return sessionsList;
    }

}
