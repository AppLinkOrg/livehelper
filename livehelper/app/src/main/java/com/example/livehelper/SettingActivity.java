package com.example.livehelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {
    LinearLayout btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //跳转设置名称
        btn = (LinearLayout) findViewById(R.id.btnname);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,SetnameActivity.class);
                intent.putExtra("info","信息");
                startActivity(intent);
            }
        });

        //跳转标签
        btn = (LinearLayout) findViewById(R.id.btnlabel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,LabelActivity.class);
                intent.putExtra("info","信息");
                startActivity(intent);
            }
        });


        //跳转关于
        btn = (LinearLayout) findViewById(R.id.btnabout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,AboutActivty.class);
                intent.putExtra("info","信息");
                startActivity(intent);
            }
        });

    }
}