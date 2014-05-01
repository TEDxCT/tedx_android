package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.Speaker;

import java.util.List;

public class SpeakerProfileActivity extends Activity {

    private TextView tvSpeakerName;
    private TextView tvGenre;
    private TextView tvTalkName ;
    private TextView tvDescription;
    private TextView tvTwitterHandle;
    private TextView tvEmailAddress;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_profile);

        tvSpeakerName = (TextView)findViewById(R.id.txt_speakerName);
        tvGenre = (TextView)findViewById(R.id.txt_genre);
        tvTalkName = (TextView)findViewById(R.id.txt_talkName);
        tvDescription = (TextView)findViewById(R.id.txt_description);
        tvTwitterHandle = (TextView)findViewById(R.id.txt_twitterHandle);
        tvEmailAddress = (TextView)findViewById(R.id.txt_emailAddress);
        ivImage = (ImageView)findViewById(R.id.img_speaker);

        Bundle state = this.getIntent().getExtras();
        if(state!=null)
            if(state.containsKey("speakerId"))
                loadSpeaker(state.getInt("speakerId"));

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
        getMenuInflater().inflate(R.menu.speaker_profile, menu);
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

    public void loadSpeaker(int speakerId) {
        Speaker speaker = getSpeaker(speakerId);

        tvSpeakerName.setText(speaker.getSpeakerName());
        tvGenre.setText(speaker.getGenre());
        tvTalkName.setText(speaker.getTalkName());
        tvDescription.setText(speaker.getDescription());
        tvTwitterHandle.setText(speaker.getTwitterHandle());
        tvEmailAddress.setText(speaker.getEmailAddress());

        Drawable myDrawable = getResources().getDrawable(R.drawable.img_karendudley);
        ivImage.setImageDrawable(myDrawable);
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
        for(ResolveInfo ri: lract)
        {
            if(ri.activityInfo.name.endsWith(".SendTweet"))
            {
                tweetIntent.setClassName(ri.activityInfo.packageName,ri.activityInfo.name);
                resolved = true;
                break;
            }
        }
        startActivity(resolved ? tweetIntent : Intent.createChooser(tweetIntent, "Choose one"));
    }

    //Dummy Class
    public Speaker getSpeaker(int speakerId) {
        Speaker spkr = new Speaker("Karen Dudley","Food","the Kitchen","Karen uses food and sharing thereof as a metaphor for embracing our diversity and creating spaces for people to find comfort and nourishment.","@twitter","karen@test.com","img_karendudley.jpg");
        return spkr;
    }

}
