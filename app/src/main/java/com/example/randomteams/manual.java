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

import java.util.ArrayList;

public class manual extends AppCompatActivity {

    private ArrayList<String> namesArray;
    private ArrayAdapter<String> adapter;
    private ListView namesList;
    private Button addName;
    private EditText names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        namesArray = new ArrayList<>();             //Arraylist
        addName = findViewById(R.id.addNameBtn);    //Add button
        namesList = findViewById(R.id.nameList);    //ListView
        names = findViewById(R.id.names);           //EditText
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

    }

    private void addNamesClicked() {
        //TODO: also add condition when only space is there
        if(!names.getText().toString().isEmpty()) {
            Log.i("TEST", "addNamesClicked: Button CLicked, Text in box = " + names.getText().toString());
            namesArray.add(names.getText().toString());
            names.setText("");
            //TODO: Do see how to make list display from top of screen and not from bottom
            //DOne See xml and note the reason!!!
            namesList.setAdapter(adapter);
        }
    }
}