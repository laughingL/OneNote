package com.laughing.onenote;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    private EditText mTitleEditText;
    private EditText mLocationEditText;
    private EditText mTextEditText;
    private RadioGroup mRadioGroup;
    private Button mDateButton;
    private Calendar mCalendar;
    private Button mSubmitButton;

    private String mTitle;
    private String mText;
    private String mLocation;
    private String mType;
    private String mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //笔记标题控件
        mTitleEditText = findViewById(R.id.note_title);
        //笔记地点控件
        mLocationEditText = findViewById(R.id.note_location);
        //笔记内容控件
        mTextEditText = findViewById(R.id.note_text);
        //笔记类型控件
        mRadioGroup = findViewById(R.id.type_rg);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //获取日记类型
                RadioButton radioButton = findViewById(checkedId);
                mType = radioButton.getText().toString().trim();
            }
        });
        //日期按钮
        mDateButton = findViewById(R.id.date_button);
        mCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR,year);
                mCalendar.set(Calendar.MONTH,month);
                mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this,date,mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //提交按钮
        mSubmitButton = findViewById(R.id.add_note_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mTitle = mTitleEditText.getText().toString();
                    mLocation = mLocationEditText.getText().toString().trim();
                    mText = mTextEditText.getText().toString();
                    mDate = mDateButton.getText().toString().trim();
                    if (mTitle != null && mLocation != null && mText!= null && mDate != null && mType != null){
                        //封装成Note类
                        Note note = new Note();
                        note.setTitle(mTitle);
                        note.setLocation(mLocation);
                        note.setText(mText);
                        note.setType(mType);
                        note.setDate(mDate);
                        //装入NoteLab写入数据库
                    }else {
                        //输出错误信息
                        Toast.makeText(AddActivity.this, R.string.add_wrong, Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    private void updateLabel(){
        //设置日期按钮显示内容的更新
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        mDateButton.setText(sdf.format(mCalendar.getTime()));
    }
}
