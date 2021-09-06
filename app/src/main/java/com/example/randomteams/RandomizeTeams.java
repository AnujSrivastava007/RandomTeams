package com.example.randomteams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomizeTeams {
    // TODO: doubt see is constructor the only way to transfer data from one class to another??
    int totalTeams;
    ArrayList<String> names = new ArrayList<>();

    public RandomizeTeams() {
    }

    //Constructor to get totalTeams from DialogBox_totalTeams
    public RandomizeTeams(int totalTeams) {
        this.totalTeams = totalTeams;
    }

    //Constructor to get ArrayList of names from Manual
    public RandomizeTeams(ArrayList<String> namesArray) {
        names = namesArray;
    }

    public String[] makeTeams() {
        Random random=new Random();

        String[] teams = new String[totalTeams];
        /**
         * Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.isEmpty()" because "teams[i]" is null
         *
         * See if i dont fill array with "" Then teams[i].isEmpty shows above error. See why??
         */
        Arrays.fill(teams, "");

        int memPerTeam = names.size()/totalTeams;
        int extraMemInTeam = names.size() - memPerTeam * totalTeams;

        
        //try optimising it!!
        for (int i = 0; i < totalTeams; i++) {
            int rn=random.nextInt(names.size());

            if (i < extraMemInTeam)
                for (int j = 1; j <= memPerTeam + 1; j++) {
                    if(!names.get(rn).isEmpty())
                    {
                        if (teams[i].isEmpty())
                            teams[i]=(j)+". "+names.get(rn)+"\n";
                        else
                            teams[i]+=(j)+". "+names.get(rn)+"\n";

                        //replaces the element at 'rn' index with ""
                        names.set(rn, "");
                    }
                    else
                    {
                        --j;
                        rn = random.nextInt(names.size());
                    }
                }

            else
                for (int j = 1; j <= memPerTeam; j++) {
                    if(!names.get(rn).isEmpty())
                    {
                        if (teams[i].isEmpty())
                            teams[i]=(j)+". "+names.get(rn)+"\n";
                        else
                            teams[i]+=(j)+". "+names.get(rn)+"\n";

                        //replaces the element at 'rn' index with ""
                        names.set(rn, "");
                    }
                    else
                    {
                        --j;
                        rn = random.nextInt(names.size());
                    }
                }
        }
        
        return teams;
    }
}
