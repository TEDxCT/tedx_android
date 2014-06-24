package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
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
    private TextView txtSpeakerName;
    private TextView txtDescription;
    private ImageView imgTalk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        setContentView(R.layout.activity_talk);

        txtTalkName = (TextView) findViewById(R.id.txtTalkName);
        txtGenre = (TextView) findViewById(R.id.txtGenre);
        txtSpeakerName = (TextView) findViewById(R.id.txtSpeakerName);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        imgTalk = (ImageView) findViewById(R.id.imgTalk);

        if (getIntent().getExtras() != null)
            if (getIntent().getExtras().containsKey("talkId"))
                loadTalk(getIntent().getExtras().getInt("talkId"));
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


    public void loadTalk(int talkId) {
        TalkModel talkModel = getTalk(talkId);
        SpeakerModel speaker = getSpeaker(talkModel);

        txtTalkName.setText(talkModel.name);
        txtGenre.setText("Genre");
        txtSpeakerName.setText(speaker.fullName);
        txtDescription.setText(Html.fromHtml(talkModel.descriptionHTML));

        if (speaker.imageURL != null && !speaker.imageURL.isEmpty())
            ImageLoader.getInstance().displayImage(speaker.imageURL, imgTalk);
    }

    public TalkModel getTalk(int talkId) {
        return findTalkById(talkId);
    }

    public SpeakerModel getSpeaker(TalkModel talkModel) {
        return talkModel.speaker;
    }

    public TalkModel findTalkById(int talkId) {
        EventCollectionModel eventCollectionModel = (EventCollectionModel) EventBus.getDefault().getStickyEvent(EventCollectionModel.class);
        for (EventModel eventModel : eventCollectionModel.events) {
            for (SessionModel sessionModel : eventModel.sessions.sessions) {
                for (TalkModel talkModel : sessionModel.talks.talks) {
                    if (talkModel.id == talkId)
                        return talkModel;
                }
            }
        }
        return null;
    }

}