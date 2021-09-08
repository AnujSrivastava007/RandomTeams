package com.example.randomteams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DisplayTeams extends AppCompatActivity implements DialogBox_totalTeams.DialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_teams);


        RandomizeTeams randomTeams = new RandomizeTeams();
        ListView randomTeamsList = findViewById(R.id.randomTeamsList);

        //ArrayAdapter of type: (context, int, int, T[]) used!!
        /*
        Note this is used when there are more than one views. so giving second id is to tell it, it needs to populate it.
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.display_list_layout, R.id.nameList, randomTeams.makeTeams());
        randomTeamsList.setAdapter(adapter);

    }

    @Override
    public void getTotalTeamsInput(int totalTeamsEntered) {
        //sending the integer value of teams number to Randomize class to make further operations. Using Constructors.
        // Also see if theres another method by which a value can be sent from one class to another.


        /**
         * See no use of try catch when i made edittext to enter only numeric values!!!
         */
//        try//see non int??
//        {
            //Also condider case of negative number. It shouldnt be entered
            RandomizeTeams rt;

            Log.i("_________CHECK1________", "displayTeams: entered Value "+totalTeamsEntered);
            if (totalTeamsEntered > 0)
                rt = new RandomizeTeams(totalTeamsEntered);
            else
                Toast.makeText(this, "Number of teams cant be less than or equal to 0", Toast.LENGTH_SHORT).show();
//        }
//        catch (Exception e)
//        {
//            Log.i("_________CHECK2________", "displayTeams: entered Value "+totalTeams.getText().toString());
//            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
//        }
    }
}