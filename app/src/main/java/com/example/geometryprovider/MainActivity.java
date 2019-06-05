package com.example.geometryprovider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE);
        }

        final Button button = findViewById(R.id.button);
        final EditText editText = findViewById(R.id.edittext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOCUMENTS), "mydreamland/geometry/vertices.dat");
                try {
                    FileWriter fileWriter = new FileWriter(file, false);
                } catch (IOException e) {
                    new AlertDialog.Builder(MainActivity.this).setTitle("Error")
                            .setMessage(e.getMessage())
                            .setPositiveButton(android.R.string.ok, null)
                            .setIcon(android.R.drawable.ic_dialog_alert).show();
                }
            }
        });
    }
}
