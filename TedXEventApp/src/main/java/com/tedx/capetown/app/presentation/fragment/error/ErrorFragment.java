package com.tedx.capetown.app.presentation.fragment.error;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.google.android.gms.common.ErrorDialogFragment;
import com.tedx.capetown.app.DefaultApplication;

/**
 * Created by andrewpettey on 2014/08/07.
 */
public class ErrorFragment{
    public static boolean isErrorDisplayed = false;
    public static void DisplayFatalError(Activity activity,String message)
    {
        if(!isErrorDisplayed) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage(message)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            System.exit(0);
                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            isErrorDisplayed = true;
        }
    }
}
