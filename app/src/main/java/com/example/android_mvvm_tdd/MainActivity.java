package com.example.android_mvvm_tdd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android_mvvm_tdd.register.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RegisterActivity.start(this);
    }

}
