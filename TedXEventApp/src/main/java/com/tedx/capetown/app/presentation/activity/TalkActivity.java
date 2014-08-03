package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.tedx.capetown.app.core.models.SpeakerModel;
import com.tedx.capetown.app.core.models.TalkModel;

import de.greenrobot.event.EventBus;

public class TalkActivity extends Activity {

    private TextView txtTalkName;
    private TextView txtGenre;
    private TextView txtSpeakerName;
    private TextView txtDescription;
    private ImageView imgTalk;
    private int speakerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getActionBar();
        actionBar.hide();
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
        txtGenre.setText(talkModel.speaker.funkyTitle);
        txtDescription.setText(Html.fromHtml(talkModel.descriptionHTML));

        if (speaker.imageURL != null && !speaker.imageURL.isEmpty())
            ImageLoader.getInstance().displayImage(talkModel.imageURL, imgTalk);

        if (speaker != null) {
            speakerId = speaker.id;
            txtSpeakerName.setText(speaker.fullName);

            findViewById(R.id.activity_talk_speaker_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, SpeakerProfileActivity.class);
                    intent.putExtra("speakerId", speakerId);
                    context.startActivity(intent);
                }
            });


            if (speaker.contactDetails != null) {
                LinearLayout contactsLayout = (LinearLayout) findViewById(R.id.lla_contactsList_talk);
                for (ContactModel contact : speaker.contactDetails.contactDetails) {
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
                findViewById(R.id.lla_contactsList_talk).setVisibility(View.GONE);
            }
        }
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