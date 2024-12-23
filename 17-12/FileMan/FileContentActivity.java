package com.example.learn2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_content);

        TextView textView = findViewById(R.id.fileContentTextView);
        String filePath = getIntent().getStringExtra("filePath");

        if (filePath != null) {
            File file = new File(filePath);
            if (file.exists() && file.canRead()) {
                try {
                    String content = new String(Files.readAllBytes(file.toPath()));
                    textView.setText(content);
                } catch (IOException e) {
                    textView.setText("Unable to read file.");
                }
            } else {
                textView.setText("Unable to read file.");
            }
        }
    }
}
