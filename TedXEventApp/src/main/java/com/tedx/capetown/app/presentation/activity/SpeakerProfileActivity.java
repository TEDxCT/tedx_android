package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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
import com.tedx.capetown.app.core.models.SpeakerCollectionModel;
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.core.models.TalkModel;

import java.util.List;

import de.greenrobot.event.EventBus;

public class SpeakerProfileActivity extends Activity {

    private TextView tvSpeakerName;
    private TextView tvGenre;
    private TextView tvTalkName;
    private TextView tvDescription;
    private TextView tvTwitterHandle;
    private TextView tvEmailAddress;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_profile);

        Bundle state = this.getIntent().getExtras();
        if (state != null)
            if (state.containsKey("speakerId"))
                loadSpeaker(state.getInt("speakerId"));

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        tvEmailAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailIntent(tvEmailAddress.getText().toString());
            }
        });

        tvTwitterHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twitterIntent(tvTwitterHandle.getText().toString());
            }
        });
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

    public void loadSpeaker(int speakerId) {
        tvSpeakerName = (TextView) findViewById(R.id.txt_speakerName);
        tvGenre = (TextView) findViewById(R.id.txt_genre);
        tvDescription = (TextView) findViewById(R.id.txt_description);
        tvTwitterHandle = (TextView) findViewById(R.id.txt_twitterHandle);
        tvEmailAddress = (TextView) findViewById(R.id.txt_emailAddress);
        ivImage = (ImageView) findViewById(R.id.img_speaker);
        tvTalkName = (TextView) findViewById(R.id.txt_talkName);

        SpeakerModel speaker = getSpeaker(speakerId);
        TalkModel talk = getTalk(speakerId);

        tvSpeakerName.setText(speaker.fullName);
        tvGenre.setText(speaker.funkyTitle);
        tvDescription.setText(Html.fromHtml(speaker.descriptionHTML));
        tvTalkName.setText(talk.name);

        if (false) //ToDO: get twitter handle
            tvTwitterHandle.setText("TODO");
        else
            tvTwitterHandle.setVisibility(View.GONE);

        if (false) //ToDO: get email address
            tvEmailAddress.setText("TODO");
        else
            tvEmailAddress.setVisibility(View.GONE);


        if (speaker.imageURL != null && !speaker.imageURL.isEmpty())
            ImageLoader.getInstance().displayImage(speaker.imageURL, ivImage);
    }

    public void emailIntent(String address) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, "TEDxCT Talk");
        intent.putExtra(Intent.EXTRA_TEXT, "Great talk!");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    public void twitterIntent(String address) {
        Intent tweetIntent = new Intent(Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_TEXT, address);
        tweetIntent.setType("application/twitter");
        PackageManager pm = getPackageManager();
        List<ResolveInfo> lract = pm.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for (ResolveInfo ri : lract) {
            if (!ri.activityInfo.name.endsWith(".SendTweet"))
                continue;
            tweetIntent.setClassName(ri.activityInfo.packageName, ri.activityInfo.name);
            resolved = true;
            break;
        }
        startActivity(resolved ? tweetIntent : Intent.createChooser(tweetIntent, "Choose one"));
    }

    public SpeakerModel getSpeaker(int speakerId) {
        return findSpeakerById(speakerId);
    }
    public TalkModel getTalk(int speakerId) {
        return findTalkBySpeakerId(speakerId);
    }

    public SpeakerModel findSpeakerById(int speakerId) {
        SpeakerCollectionModel speakerCollectionModel = (SpeakerCollectionModel) EventBus.getDefault().getStickyEvent(SpeakerCollectionModel.class);
        for (SpeakerModel speakerModel : speakerCollectionModel.speakers) {
            if (speakerModel.id == speakerId)
                return speakerModel;
        }
        return null;
    }

    public TalkModel findTalkBySpeakerId(int speakerId) {
        EventCollectionModel eventCollectionModel = (EventCollectionModel) EventBus.getDefault().getStickyEvent(EventCollectionModel.class);
        for (EventModel eventModel : eventCollectionModel.events) {
            for (SessionModel sessionModel : eventModel.sessions.sessions) {
                for (TalkModel talkModel : sessionModel.talks.talks) {
                    if (talkModel.speakerId == speakerId)
                        return talkModel;
                }
            }
        }
        return null;
    }

}