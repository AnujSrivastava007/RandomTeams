package com.example.randomteams;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportFile extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;

    private LinearLayout importFileLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_file);

        importFileLinearLayout = findViewById(R.id.importFileLinearLayout);

        /**
         * Requesting permission for the attachment of file
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }

        importFileLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachFile();
            }
        });
    }

    /**
     * NEED To understand all of this!!
     * Done from:
     * https://youtu.be/J6azVvt-9KE
     */

    private void attachFile() {
//        /**
//         * Requesting permission for the attachment of file
//         */
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
//        }

        performFileSearch();
    }




    //read content of file
    private ArrayList<String> readText(String input) {
        File file = new File(input);
//        StringBuilder text = new StringBuilder();
        ArrayList<String> text = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            /*
            See he initialized it inside the while paranthesis, And look at the warning
            Must resolve this, maybe thats what doing it wrong!!
             */

            /**
             * See consider a situation where a line null comes and then there are all the names, SO
             * this below condition would skip it, See how to counter.
             *
             * or is it that it is considered new line and not null
             */
            while((line=br.readLine()) != null) {
//                Log.i("_______TEST1_______", "readText: File lines: " + line);
//                text.append(line);
//                text.append("\n");

                if(!line.equals(" ") && !line.equals("\n"))
                    text.add(line);

            }
//            Log.i("_______TEST2_______", "readText: File lines: " + line);
            br.close();
        }
        catch (IOException e) {
//            Log.i("_______TEST3_______", "readText: Entered catch");
            e.printStackTrace();
        }
        return text;
    }





    //select file from storage
    private void performFileSearch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
//

        //See implement this fuction
//        regisforactire
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring(path.indexOf(":") + 1);


                /**
                 * See i had to add this extra text!!
                 * This could work only in mine, so see how to make it general
                 */
                path = "/storage/emulated/0/" + path;
//                Toast.makeText(this, readText(path).toString(), Toast.LENGTH_SHORT).show();
//                display.setText("The data read is:\n" + readText(path));

                Intent intent = new Intent(this, Manual.class);
                intent.putExtra("Names", readText(path));
                intent.putExtra("intentID", "from ImportFile");
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "___Permission Granted___", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "___Permission DENIED___", Toast.LENGTH_SHORT).show();
                finish(); // see what it does???
            }
        }
    }

}