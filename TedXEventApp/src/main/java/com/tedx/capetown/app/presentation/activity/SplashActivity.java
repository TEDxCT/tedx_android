package com.tedx.capetown.app.presentation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tedx.capetown.app.R;
import com.tedx.capetown.app.facade.factory.FacadeFactoryImpl;

public class SplashActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FacadeFactoryImpl.createEventFacade(this).fetchEventList();

        Intent intent = new Intent();
        intent.setClass(this.getApplicationContext(), HomeActivity.class);
        intent.putExtra("key", "Hat");
//        this.startActivity(intent);
        this.startActivityForResult(intent, 100);
//        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle state)
    {
        state.putString("Test", "TEST");
        super.onSaveInstanceState(state);
    }

    @Override
    public void onRestoreInstanceState(Bundle state)
    {
        super.onRestoreInstanceState(state);
        if(state!=null)
            if(state.containsKey("Test"))
        Toast.makeText(this,state.getString("Test"),Toast.LENGTH_LONG).show();
    }

    public void onActivityResult( int requestCode, int resultCode,Intent intent)
    {
        super.onActivityResult(requestCode,resultCode,intent);
    }
}
