package com.example.randomteams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DisplayTeams extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_teams);


//        RandomizeTeams randomTeams = new RandomizeTeams();
        ListView randomTeamsList = findViewById(R.id.randomTeamsList);


        /**
         * Below 2 line are used to retrieve the info passed from calling activity to this called activity.
         * for more info, refer
         * https://stackoverflow.com/questions/6707900/pass-a-string-from-one-activity-to-another-activity-in-android
         */
        Intent intent = getIntent();
        String[] finalList = intent.getExtras().getStringArray("ansList");
        //ArrayAdapter of type: (context, int, int, T[]) used!!
        /*
        Note this is used when there are more than one views. so giving second id is to tell it, it needs to populate it.
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.display_list_layout, R.id.namesRandomList, finalList);
        randomTeamsList.setAdapter(adapter);

    }


}