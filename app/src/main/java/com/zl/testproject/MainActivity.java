package com.zl.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zl.testproject.R;

public class MainActivity extends AppCompatActivity     {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onHellow(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}