package com.example.randomteams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.InsetDialogOnTouchListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Manual extends AppCompatActivity implements DialogBox_totalTeams.DialogListener {

    private ArrayList<String> namesArray;
    private ArrayAdapter<String> adapter;
    private ListView namesList;
    private Button addName, generateTeams;
    private EditText names;
    private TextView crossDelNames, nameCounter;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        counter = 0;
        namesArray = new ArrayList<>();             //Arraylist
        addName = findViewById(R.id.addNameBtn);    //Add button
        generateTeams = findViewById(R.id.genBtn);  //Gen Btn
        namesList = findViewById(R.id.nameList);    //ListView
        names = findViewById(R.id.names);           //EditText
        nameCounter = findViewById(R.id.countNames);// TextView for names counting
        crossDelNames = findViewById(R.id.deleteName); //TextView with a cross
        /*
        Note: WHen there are multiple textviews in the layout u providing to arrayadapter
        give the id of view u want to populate.!!! see R.id.displayName
        refer: https://stackoverflow.com/questions/5916459/you-must-supply-a-resource-id-for-a-textview-android-error
         */
        adapter = new ArrayAdapter<>(this, R.layout.name_list_custom, R.id.displayName, namesArray);

        addName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNamesClicked();
            }
        });

        generateTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeTeamsDialogBox();
            }
        });
        /**
         * Error here. See maybe coz its ambiguous as to which cross is clicked. so many in ListView
         * See adding it in activity_manual and somethimg
         * or selecting all view by long press and then delete!!??
         */
//        crossDelNames.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteNames();
////                Log.i("TEST", "onClick: Cross view CLICKED");
//            }
//        });

    }

    private void makeTeamsDialogBox() {
//        RandomizeTeams random = new RandomizeTeams(namesArray); // sends names list to randomize class to perform task
        //Making object of my DialogBox class
        DialogBox_totalTeams dialogBox = new DialogBox_totalTeams();
        // calling show on my dialog to present dialog box on screen
        dialogBox.show(getSupportFragmentManager(), "MakeTeams Dialog");
    }

    private void deleteNames() {
        Toast.makeText(this, "onClick: Cross view CLICKED", Toast.LENGTH_SHORT).show();

//        String nameDeleted = names.getText().toString();
//        int delNameIndex = namesArray.indexOf(nameDeleted);
//        namesArray.remove(delNameIndex);
//        Log.i("Test", "deleteNames: nameDeleted: ***" + nameDeleted + "*** at index= " + delNameIndex+"New element at that index = "+namesArray.get(delNameIndex));
    }

    private void addNamesClicked() {
        //TODO: also add condition when only space is there
        if(!names.getText().toString().isEmpty()) {
//            Log.i("TEST", "addNamesClicked: Button CLicked, Text in box = " + names.getText().toString());
            namesArray.add(names.getText().toString());
            names.setText("");
            //Do see how to make list display from top of screen and not from bottom
            //DOne See xml and note the reason!!!
            namesList.setAdapter(adapter);
            counter++;
            nameCounter.setText("Total Names: " + counter);
        }
    }


    @Override
    public void applyText(String totalTeamsEnteredString) {
        //sending the integer value of teams number to Randomize class to make further operations. Using Constructors.
        // Also see if theres another method by which a value can be sent from one class to another.

        int totalTeamsEntered = 0;
        try {
            totalTeamsEntered = Integer.parseInt(totalTeamsEnteredString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter a Valid Value, Not to be kept empty", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        /**
         * See no use of try catch when i made edittext to enter only numeric values!!!
         */
//        try//see non int??
//        {
        //Also condider case of negative number. It shouldnt be entered
        RandomizeTeams rt;
//
        Log.i("_________CHECK1________", "displayTeams: entered Value in string "+totalTeamsEnteredString); /// WORKING FINE just see some problem with intent or activity
        Log.i("_________CHECK2________", "displayTeams: entered Value "+totalTeamsEntered);


        if (totalTeamsEntered > 0) {
            rt = new RandomizeTeams(totalTeamsEntered, namesArray);


            /**
             * IMP NOTE
             * when i was writing an explicit intent to open another activity I was writing as we write for all other such cases
             * that is
             *  Intent i = new Intent(this, DisplayTeams.class);
             *
             *  Now this here refers to the present context that is, this Fragment(Dialog Box), and Fragment is not a valid CONTEXT according to:
             *   https://stackoverflow.com/questions/46047979/cannot-resolve-constructor-intent
             *
             *  But Activity is the correct context. So used getActivity() to pass the current activity as the context.
             *  NOTE: Use of getActivity too
             *
             *  also Research and learn about what Fragments are.. see that udacity course. it has it
             */
            Intent i = new Intent(this, DisplayTeams.class); //SEE why this error?? --- SORTED

            /**
             * We can also pass data from one activity to another using intents.
             * .putExtra does similar thing using key-value system.
             * For more info, refer:
             * https://stackoverflow.com/questions/6707900/pass-a-string-from-one-activity-to-another-activity-in-android
             */
            i.putExtra("ansList", rt.makeTeams());
//        String namesArrayString[] = new String[namesArray.size()];
//        for (int index = 0; index < namesArray.size(); index++) {
//            namesArrayString[index] = namesArray.get(index);
//        }
//            i.putExtra("ansList", namesArrayString);
            startActivity(i);

        }
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