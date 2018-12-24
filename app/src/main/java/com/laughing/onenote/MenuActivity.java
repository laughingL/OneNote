package com.laughing.onenote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import static com.laughing.onenote.ListActivity.result;

public class MenuActivity extends AppCompatActivity {

    private Button workbutton;
    private Button lifebutton;
    private Button othersbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        workbutton = findViewById(R.id.button_work);
        lifebutton = findViewById(R.id.button_life);
        othersbutton = findViewById(R.id.button_others);

        lifebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0; //查询生活类的笔记
                toActivity();

            }
        });

        workbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 1; //查询工作类的笔记
                toActivity();
            }
        });

        othersbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 2; //查询其他的笔记
                toActivity();
            }
        });
    }

    public void toActivity() {
        Intent intent = new Intent(MenuActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
