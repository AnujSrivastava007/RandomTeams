package com.example.randomteams;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.fragment.app.DialogFragment;

import com.example.randomteams.DisplayTeams;
import com.example.randomteams.R;

/**
 * Note:
 * THIS is a CUSTOM layout Dialog box!!The custom layout includes edittext
 * note this down in the notes. How to make dialog... Basics and related things. Note more to learn on this
 * More see here:
 * 1. https://developer.android.com/guide/topics/ui/dialogs#CustomLayout
 * 2. https://www.youtube.com/watch?v=ARezg1D9Zd0
 */


//VV IMP note this:

/**
 * Here my class is extending DialogFormat. THere were 2 such classes in the options
 * one is marked "android.app"
 * and other is "androidx.fragment.app"
 *
 * SO first i was using 1. .show(...) showed error in Manual class
 * but 2nd one works fine; Note this, and find why is it so??
 */
public class DialogBox_totalTeams extends DialogFragment {
//    private Intent i;

    //TODO: Show How many members name has been entered in dialog box


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //getting LayoutInflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        /**
         * Method 1:
         * View view = inflater.inflate(R.layout.dialog_custom_layout, null);
         * builder.setView(view) and rest is same.
         *
         * Method 2:
         * below:
         */
        builder.setView(inflater.inflate(R.layout.dialog_custom_layout, null))
                //Adds title for the dialog boc
                .setTitle("Enter total Teams:")
                //Adds action button.
                // 3 TYpes of buttons, Negative Positive Neutral
                // refer https://developer.android.com/guide/topics/ui/dialogs#AddingButtons
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //no need to do anything here. Negative button itself means taking u back!
                    }
                })
                //Also note we need to add OnclickListener also!
                .setPositiveButton("Randomize", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        displayTeams();
                    }
                });
                //Adds message in dialog box
//                .setMessage("Total names: "  );  // Add this later. See it says param constructor cant be used in fragments activity??
        return builder.create();
    }

    private void displayTeams() {
//        i = new Intent(this, DisplayTeams.class); //SEE why this error??
    }
}
