package com.laughing.onenote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String USER_NAME = "root";
    private static final String PWD = "******";
    private EditText mUserName;
    private EditText mPwd;
    private Button mSubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserName = findViewById(R.id.username_edit);
        mPwd = findViewById(R.id.pwd_edit);
        mSubmitButton = findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){
        String username = mUserName.getText().toString().trim();
        String pwd = mPwd.getText().toString().trim();
        if (username.equals(USER_NAME) && pwd.equals(PWD)){
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,R.string.login_wrong,Toast.LENGTH_SHORT).show();
        }
    }
}
