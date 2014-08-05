package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tedx.capetown.app.R;
import com.tedx.capetown.app.core.models.ContactModel;
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
    private ImageView ivImage;
    private int talkId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_profile);

        Bundle state = this.getIntent().getExtras();
        if (state != null)
            if (state.containsKey("speakerId"))
                loadSpeaker(state.getInt("speakerId"));

        final ActionBar actionBar = getActionBar();
        actionBar.hide();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
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
        ivImage = (ImageView) findViewById(R.id.img_speaker);
        tvTalkName = (TextView) findViewById(R.id.txt_talkName);

        SpeakerModel speaker = getSpeaker(speakerId);
        tvSpeakerName.setText(speaker.fullName);
        tvGenre.setText(speaker.funkyTitle);
        tvDescription.setText(Html.fromHtml(speaker.descriptionHTML));

        TalkModel talk = getTalk(speakerId);
        if (talk != null)
        {
            talkId = talk.id;
            tvTalkName.setText(talk.name);

            tvTalkName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context,TalkActivity.class);
                    intent.putExtra("talkId",talkId);
                    context.startActivity(intent);
                }
            });
        }

        if (speaker.contactDetails != null){
            LinearLayout contactsLayout = (LinearLayout) findViewById(R.id.lla_contactsList_speaker);
            contactsLayout.setVisibility(View.VISIBLE);
            for (ContactModel contact : speaker.contactDetails.contactDetails ) {
                final TextView tvContact = new TextView(this);
                tvContact.setText(contact.value);
                tvContact.setTextColor(Color.parseColor("#0092ff"));
                tvContact.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

                if (contact.name.equals("email")) {
                    tvContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            emailIntent(tvContact.getText().toString());
                        }
                    });
               /* } else if (contact.name.equals("twitter")) {
                    tvContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            twitterIntent(tvContact.getText().toString());
                        }
                    });*/
                } else {
                    tvContact.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            urlIntent(tvContact.getText().toString());
                        }
                    });
                }

                contactsLayout.addView(tvContact);
            }
        }
        else
        {
            if(findViewById(R.id.lla_contactsList_talk)!=null) {
                findViewById(R.id.lla_contactsList_talk).setVisibility(View.GONE);
            }
        }

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

    public void urlIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(Intent.createChooser(intent, "Open Url"));
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