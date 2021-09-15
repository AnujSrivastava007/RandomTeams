package com.example.randomteams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class crossDelAdapter extends ArrayAdapter<String> {

    private ArrayList<String> namesList;
    public crossDelAdapter(Context context, ArrayList<String> namesList) {
        super(context, 0, namesList);
        this.namesList = namesList;
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
    }
}
