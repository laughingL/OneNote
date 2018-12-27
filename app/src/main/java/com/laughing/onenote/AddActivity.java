package com.laughing.onenote;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class AddActivity extends AppCompatActivity {
    private static final String NOTE_ID = "note_id";
    private Note mNote;
    private EditText mTitleEditText;
    private EditText mLocationEditText;
    private EditText mTextEditText;
    private RadioGroup mRadioGroup;
    private RadioButton[] mRadioButtons;
    private Button mDateButton;
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //根据传过来的UUID获取笔记
        UUID noteId = (UUID) getIntent().getSerializableExtra(NOTE_ID);
        mNote = NoteLab.get(this).getNote(noteId);
        //笔记标题控件
        mTitleEditText = findViewById(R.id.note_title);
        mTitleEditText.setText(mNote.getTitle());
        mTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //笔记地点控件
        mLocationEditText = findViewById(R.id.note_location);
        mLocationEditText.setText(mNote.getLocation());
        mLocationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setLocation(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //笔记内容控件
        mTextEditText = findViewById(R.id.note_text);
        mTextEditText.setText(mNote.getText());
        mTextEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNote.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //笔记类型控件
        mRadioButtons = new RadioButton[3];
        mRadioButtons[0] = findViewById(R.id.type_work);
        mRadioButtons[1] = findViewById(R.id.type_life);
        mRadioButtons[2] = findViewById(R.id.type_others);
        updateType();
        mRadioGroup = findViewById(R.id.type_rg);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //获取日记类型
                RadioButton radioButton = findViewById(checkedId);
                mNote.setType(radioButton.getText().toString().trim());
            }
        });
        //日期按钮
        mDateButton = findViewById(R.id.date_button);
        updateDate();
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
    }

    private void updateType() {
        //设置类型的显示
        for (RadioButton r : mRadioButtons){
            if (r.getText().toString().trim().equals(mNote.getType())){
                r.setChecked(true);
            }
        }
    }

    //日期按钮初始显示值
    private void updateDate() {
        if (mNote.getDate() != null){
            mDateButton.setText(mNote.getDate());
        }else {
            mDateButton.setText(R.string.date_view);
        }
    }

    private void updateLabel(){
        //设置日期按钮显示内容的更新
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        String date = sdf.format(mCalendar.getTime());
        mNote.setDate(date);
        mDateButton.setText(date);
    }

    @Override
    //更新数据
    protected void onPause() {
        super.onPause();
        NoteLab.get(this).updateNote(mNote);
    }

    //启动本Activity调用的方法
    public static Intent newIntent(Context context, UUID noteId){
        Intent intent = new Intent(context,AddActivity.class);
        intent.putExtra(NOTE_ID,noteId);
        return intent;
    }
}
