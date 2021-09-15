package com.example.randomteams;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TeamAdapter extends ArrayAdapter<String> {

    private String[] ansList;
    public TeamAdapter(Context context, String[] ansList) {
        super(context, 0, ansList);
        this.ansList = ansList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View teamTile = convertView;
        if (teamTile == null) {
            teamTile = LayoutInflater.from(getContext())
                        .inflate(R.layout.display_list_layout, parent, false);
        }

        TextView teamMem = teamTile.findViewById(R.id.namesRandomList);
        TextView teamNum = teamTile.findViewById(R.id.teamNum);

        teamNum.setText("Team " + (position + 1));
//        Log.i("_______*_______", "getView: Text in teamNum: " + teamNum.getText().toString());
        teamMem.setText(ansList[position]);

        return teamTile;
    }
}
