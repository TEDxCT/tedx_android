package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.EventCollectionModel;
import com.tedx.capetown.app.core.models.EventModel;
import com.tedx.capetown.app.core.models.SessionModel;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.core.models.TalkModel;

import de.greenrobot.event.EventBus;

public class TalkActivity extends Activity {

    private TextView txtTalkName;
    private TextView txtGenre;
    private TextView txtSpeakerName ;
    private TextView txtDescription;
    private ImageView imgTalk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        txtTalkName = (TextView) findViewById(R.id.txtTalkName);
        txtGenre = (TextView) findViewById(R.id.txtGenre);
        txtSpeakerName = (TextView) findViewById(R.id.txtSpeakerName);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        imgTalk = (ImageView) findViewById(R.id.imgTalk);

        if (savedInstanceState != null)
            if (savedInstanceState.containsKey("talkId"))
                loadTalk(savedInstanceState.getInt("talkId"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.talk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void loadTalk(int talkId) {
        TalkModel talk = getTalk(talkId);
        SpeakerModel speaker = getSpeaker(talk.speakerId);

        txtTalkName.setText(talk.name);
        txtGenre.setText("Genre");
        txtSpeakerName.setText(speaker.fullName);
        txtDescription.setText(Html.fromHtml(talk.descriptionHTML));

        if(speaker.imageURL != null && !speaker.imageURL.isEmpty())
            ImageLoader.getInstance().displayImage(speaker.imageURL, imgTalk);
    }

    public TalkModel getTalk(int talkId) {
        EventCollectionModel eventCollectionModel1 = (EventCollectionModel) EventBus.getDefault().getStickyEvent(EventCollectionModel.class);
        TalkModel talk = eventCollectionModel1.events.get(0).sessions.sessions.get(0).talks.talks.get(0);
        return talk;
    }

    public SpeakerModel getSpeaker(String speakerId) {
        EventCollectionModel eventCollectionModel1 = (EventCollectionModel) EventBus.getDefault().getStickyEvent(EventCollectionModel.class);
        SpeakerModel speaker = eventCollectionModel1.events.get(0).sessions.sessions.get(0).talks.talks.get(0).speaker;
        return speaker;
    }
    public TalkModel findTalkById(int talkId)
    {
        EventCollectionModel eventCollectionModel = (EventCollectionModel) EventBus.getDefault().getStickyEvent(EventCollectionModel.class);
        for(EventModel eventModel : eventCollectionModel.events)
        {
            for(SessionModel sessionModel : eventModel.sessions.sessions)
            {
                for(TalkModel talkModel : sessionModel.talks.talks)
                {
                    if(talkModel.id == talkId)
                    {
                        return talkModel;
                    }
                }
            }
        }
        return null;
    }

}
