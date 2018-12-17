package com.laughing.onenote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AddActivity extends AppCompatActivity {
    private EditText mTitleEditText;
    private EditText mLocationEditText;
    private EditText mTextEditText;
    private RadioGroup mRadioGroup;
    private Button mDateButton;
    private Button mSubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //笔记标题
        mTitleEditText = findViewById(R.id.note_title);
        //笔记地点
        mLocationEditText = findViewById(R.id.note_location);
        //笔记内容
        mTextEditText = findViewById(R.id.note_text);
        //笔记类型
        mRadioGroup = findViewById(R.id.type_rg);
        //日期按钮
        mDateButton = findViewById(R.id.date_button);
        //提交按钮
        mSubmitButton = findViewById(R.id.submit_button);
    }
}
