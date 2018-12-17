package com.laughing.onenote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AddActivity extends AppCompatActivity {
    private EditText mTitle;
    private EditText mLocation;
    private EditText mText;
    private RadioGroup mRadioGroup;
    private Button mDateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //
        mTitle = findViewById(R.id.note_title);
        mLocation = findViewById(R.id.note_location);
        mText = findViewById(R.id.note_text);
    }
}
