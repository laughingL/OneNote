package com.laughing.onenote;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    public static int result = 0;

    private Button workbutton;
    private Button lifebutton;
    private Button othersbutton;

    Fragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        workbutton = findViewById(R.id.button_work);
        lifebutton = findViewById(R.id.button_life);
        othersbutton = findViewById(R.id.button_others);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment1 = fragmentManager.findFragmentById(R.id.list_fragment_container);
        fragment1 = new ListFragment();
        fragmentManager.beginTransaction()
                .add(R.id.list_fragment_container, fragment1)
                .commit();

        lifebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 0; //查询生活类的笔记
                checkfragment(fragment1);

            }
        });

        workbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 1; //查询工作类的笔记
                checkfragment(fragment1);
            }
        });

        othersbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = 2; //查询其他的笔记
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


}
