package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {
    private int id;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        editText = findViewById(R.id.noteEditText);
        id = getIntent().getIntExtra("id", 0);
        String contents = getIntent().getStringExtra("contents");
        editText.setText(contents);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.database.noteDao().save(editText.getText().toString(), id);
    }
}