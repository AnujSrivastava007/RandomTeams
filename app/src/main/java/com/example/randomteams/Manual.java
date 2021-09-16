package com.example.randomteams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Manual extends AppCompatActivity implements DialogBox_totalTeams.DialogListener {

    private ArrayList<String> namesArray;//, namesArray1;
    private CrossDelAdapter adapter;
    private ListView namesList;
    private Button addName, generateTeams;
    private EditText names;
    private TextView crossDelNames, nameCounter;
    private int counter;
    private RetainList retainList;


//    /**
//     * Below are the 3 constants, Used in saving instances of app/ activity
//     * They are basically used as Keys, and mapped to there respective data below.(?)
//     */
//    public static final String SHARED_PREFS = "sharedPrefs";
//    public static final String TOTAL_NAMES = "totalNames";
//    public static final String LIST = "namesList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

//        Toast.makeText(this, "OnCreate Invoked", Toast.LENGTH_LONG).show();

        counter = 0;
//        retainList = new RetainList();
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

        //Below is the custom Adapter

//        retainList.setNamesArrayList(namesArray);
//        namesArray1 = retainList.getNamesArrayList();

        adapter = new CrossDelAdapter(this, namesArray, nameCounter);
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
         *
         * FOUND SOLUTION:
         * This is not to be done here. It is done in the custom adapter class!
         *
         * //        crossDelNames.setOnClickListener(new View.OnClickListener() {
         * //            @Override
         * //            public void onClick(View v) {
         * //                deleteNames();
         * ////                Log.i("TEST", "onClick: Cross view CLICKED");
         * //            }
         * //        });
         */


//        /**
//         * Below CODE is for savind the instance of this Manual.java Activity
//         * So that we can reload all of it at the time of restart of app/Activity/Change in Config.
//         *
//         * We also need 3 constants for this, and some other Variables which will be declared
//         * before OnCreate method above!!
//         *
//         * saveData() :
//         * The code in this method is used to save the instance of app/activity
//         */
//
//        saveData();

//        Toast.makeText(this, "End of OnCreate", Toast.LENGTH_LONG).show();
    }

//    /**
//     * SharedPreferences is the interface used here.(LEARN MORE abt it)
//     * SHARED_PREFS : Constant we created
//     * MODE_PRIVATE : This means no other app can change our shared Preferences
//     *
//     * Refer:
//     * https://youtu.be/fJEFZ6EOM9o
//     */
//    public void saveData() {
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        //Now with our editor we will save our variables
////        editor.put
//    }

    private void makeTeamsDialogBox() {
//        RandomizeTeams random = new RandomizeTeams(namesArray); // sends names list to randomize class to perform task
        //Making object of my DialogBox class
        DialogBox_totalTeams dialogBox = new DialogBox_totalTeams();
        // calling show on my dialog to present dialog box on screen
        dialogBox.show(getSupportFragmentManager(), "MakeTeams Dialog");
    }

//    private void deleteNames() {
//        Toast.makeText(this, "onClick: Cross view CLICKED", Toast.LENGTH_SHORT).show();
//
////        String nameDeleted = names.getText().toString();
////        int delNameIndex = namesArray.indexOf(nameDeleted);
////        namesArray.remove(delNameIndex);
////        Log.i("Test", "deleteNames: nameDeleted: ***" + nameDeleted + "*** at index= " + delNameIndex+"New element at that index = "+namesArray.get(delNameIndex));
//    }

    private void addNamesClicked() {
        //TODO: also add condition when only space is there
        if(!names.getText().toString().isEmpty()) {
//            Log.i("TEST", "addNamesClicked: Button CLicked, Text in box = " + names.getText().toString());
            namesArray.add(names.getText().toString());
//            retainList.addNamesToArrayList(names.getText().toString());
            names.setText("");
            //Do see how to make list display from top of screen and not from bottom
            //DOne See xml and note the reason!!!
            namesList.setAdapter(adapter);
            increaseCounter();
//            Toast.makeText(this, "List:\n" + namesArray, Toast.LENGTH_LONG).show();
        }
    }

    public void increaseCounter() {
        counter = namesArray.size();
        nameCounter.setText("Total Names: " + counter);
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
//        Log.i("_________CHECK1________", "displayTeams: entered Value in string "+totalTeamsEnteredString); /// WORKING FINE just see some problem with intent or activity
//        Log.i("_________CHECK2________", "displayTeams: entered Value "+totalTeamsEntered);


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
            finish(); // TODO: Note this removes this activity from Activity stack and when we press back we land up on the Launch Activity here

//            Toast.makeText(this, "Closing", Toast.LENGTH_LONG).show();
//            Toast.makeText(DisplayTeams.class, "Opened DisplayTeams", Toast.LENGTH_LONG).show();

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