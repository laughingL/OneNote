package com.laughing.onenote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mButton;
    private static final String SEARCH_RESULT = "result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mEditText = findViewById(R.id.search_date);
        mButton = findViewById(R.id.search_date_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = mEditText.getText().toString().trim();
                if (!date.equals("")) {
                    List<Note> noteList = getResultList(date);
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
}
