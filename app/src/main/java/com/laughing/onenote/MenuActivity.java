package com.laughing.onenote;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.List;


public class MenuActivity extends AppCompatActivity {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_WORK = 1;
    public static final int TYPE_LIFE = 2;
    public static final int TYPE_OTHERS = 3;
    public static final String TYPE_CODE = "type_code";
    private static final String SEARCH_RESULT = "result";
    private Button workbutton;
    private Button lifebutton;
    private Button othersbutton;
    private Button allbutton;
    private Intent mIntent;
    Fragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        workbutton = findViewById(R.id.button_work);
        lifebutton = findViewById(R.id.button_life);
        othersbutton = findViewById(R.id.button_others);
        allbutton = findViewById(R.id.button_all);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment1 = fragmentManager.findFragmentById(R.id.list_fragment_container);
        fragment1 = new ListFragment();
        mIntent = getIntent();
        mIntent.putExtra(TYPE_CODE,TYPE_ALL);
        fragmentManager.beginTransaction()
                .add(R.id.list_fragment_container, fragment1)
                .commit();
        allbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra(TYPE_CODE,TYPE_ALL);
                checkfragment(fragment1);
            }
        });
        lifebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra(TYPE_CODE,TYPE_LIFE);
                checkfragment(fragment1);
            }
        });

        workbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra(TYPE_CODE,TYPE_WORK);
                checkfragment(fragment1);
            }
        });

        othersbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra(TYPE_CODE,TYPE_OTHERS);
                checkfragment(fragment1);
            }
        });


    }

    public void checkfragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment1 = fragmentManager.findFragmentById(R.id.list_fragment_container);
        fragment1 = new ListFragment();
        fragmentManager.beginTransaction().hide(fragment).commit();//隐藏本来的fragment
        fragmentManager.beginTransaction().add(R.id.list_fragment_container, fragment1).commit();//显示现在的fragment
    }
    public static Intent newIntent(Context context,List<Note> noteList){
        Intent intent = new Intent(context,MenuActivity.class);
        intent.putExtra(SEARCH_RESULT, (Serializable) noteList);
        return intent;
    }
}
