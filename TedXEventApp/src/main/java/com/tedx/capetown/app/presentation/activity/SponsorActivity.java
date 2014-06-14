package com.tedx.capetown.app.presentation.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import com.tedx.capetown.app.R;

public class SponsorActivity extends Activity
{

    public SponsorActivity() {
        ImageView view = (ImageView) findViewById(R.id.imgSponsor);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        setContentView(R.layout.listview_sponsor);
    }

}