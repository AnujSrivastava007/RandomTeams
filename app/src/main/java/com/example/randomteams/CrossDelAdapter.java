package com.example.randomteams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CrossDelAdapter extends ArrayAdapter<String> {

    private ArrayList<String> namesList;
    private TextView counterTextView;
    private Button genTeamsBtn;

    public CrossDelAdapter(Context context, ArrayList<String> namesList, TextView counterTextView, Button genTeamsBtn) {
        super(context, 0, namesList);
        this.namesList = namesList;
        this.counterTextView = counterTextView;
        this.genTeamsBtn = genTeamsBtn;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View namesTile = convertView;
        if (namesTile == null) {
            namesTile = LayoutInflater.from(getContext())
                        .inflate(R.layout.name_list_custom, parent, false);
        }

        TextView names = namesTile.findViewById(R.id.displayName);
        TextView cross = namesTile.findViewById(R.id.deleteName);

        names.setText(namesList.get(position));
        
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNames(namesList, position);
            }
        });
        return namesTile;
    }

    private void deleteNames(ArrayList<String> namesList, int position) {
        namesList.remove(position);
        notifyDataSetChanged();

        // To set Button Disabled when list empty
        if (namesList.isEmpty())
            genTeamsBtn.setEnabled(false);
//        int counter = Integer.parseInt(counterTextView.getText().toString().substring(13));
        /**
         * https://stackoverflow.com/a/5497639
         * link to explanation of above code
         */
        int counter = namesList.size();
        counterTextView.setText("Total Names: " + counter);
    }
}
