package com.laughing.onenote;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    private Button mDateButton;
    private Button mButton;
    private Calendar mCalendar;
    private String mDate;
    private static final String SEARCH_RESULT = "result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mDateButton = findViewById(R.id.search_date);
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
                new DatePickerDialog(SearchActivity.this,date,mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        mButton = findViewById(R.id.search_date_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDate != null) {
                    List<Note> noteList = getResultList(mDate);
                    if (noteList.size() != 0){
                        Intent intent = MenuActivity.newIntent(SearchActivity.this,noteList);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SearchActivity.this,R.string.search_wrong ,Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SearchActivity.this,R.string.search_wrong ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private List<Note> getResultList(String date) {
        //结果List
        List<Note> resultList = new ArrayList<>();
        //获取NoteLab
        NoteLab noteLab = NoteLab.get(SearchActivity.this);
        //获取NoteList
        List<Note> noteList = noteLab.getNotes();
        //遍历noteList获取日期符合的日记
        for (int i=0;i<noteList.size();i++){
            Note note = noteList.get(i);
            if (note.getDate().equals(date)){
                resultList.add(note);
            }
        }
        return resultList;
    }
    private void updateLabel(){
        //设置日期按钮显示内容的更新
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        String date = sdf.format(mCalendar.getTime());
        mDate = date;
        mDateButton.setText(date);
    }
}
