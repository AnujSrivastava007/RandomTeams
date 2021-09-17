package com.example.randomteams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayTeams extends AppCompatActivity {

    private TeamAdapter adapter;
    private String[] finalList;
    private Button copyBtn, randomizeBtn;
    private ListView randomTeamsList;

    private static final String COPY_LIST = "copiedList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_teams);


//        RandomizeTeams randomTeams = new RandomizeTeams();
        randomTeamsList = findViewById(R.id.randomTeamsList);


        /**
         * Below 2 line are used to retrieve the info passed from calling activity to this called activity.
         * for more info, refer
         * https://stackoverflow.com/questions/6707900/pass-a-string-from-one-activity-to-another-activity-in-android
         */
        Intent intent = getIntent();
        finalList = intent.getExtras().getStringArray("ansList");

        /**
         * TODO: Make a custom ArrayAdapter to display Team 1, Team 2 etc as heading, so its a good way to learn it and implement. Grab it.!!
         */
        //ArrayAdapter of type: (context, int, int, T[]) used!!
        /*
        Note this is used when there are more than one views. so giving second id is to tell it, it needs to populate it.
         */


        adapter = new TeamAdapter(this, finalList);
        randomTeamsList.setAdapter(adapter);


        copyBtn = findViewById(R.id.copyBtn);
//        randomizeBtn = findViewById(R.id.randomizeBtn);

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyList();
            }
        });

//        randomizeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                randomizeAgain();
//            }
//        });
    }

//    private void randomizeAgain() {
//        Intent intent = getIntent();
//        ArrayList<String> namesList = new ArrayList<>();
//        namesList = intent.getExtras().getStringArrayList("originalList");
//        int totalTeams = finalList.length;
//
//        RandomizeTeams rt = new RandomizeTeams(totalTeams, namesList);
//
//        adapter = new TeamAdapter(this, rt.makeTeams());
//        randomTeamsList.setAdapter(adapter);
//        Toast.makeText(this, "New Teams:\n" + Arrays.toString(rt.makeTeams()), Toast.LENGTH_LONG).show();
//    }

    private void copyList() {

        StringBuilder copiedListTeams = new StringBuilder();
        for (int i = 0; i < finalList.length; i++) {
            copiedListTeams.append("Team ").append(i+1 + "\n").append(finalList[i]).append("\n");
        }

//        Toast.makeText(this, "Copied List:\n" + copiedListTeams, Toast.LENGTH_LONG).show();

        /**
         * Below Lines are used to make the text copy to clipboard!!!
         * LEARN AND UNDERSTAND MORE ABOUT IT!!!
         */
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(COPY_LIST, copiedListTeams);
        clipboard.setPrimaryClip(clip);
    }


}