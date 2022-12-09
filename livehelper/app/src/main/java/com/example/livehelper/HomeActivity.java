package com.example.livehelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    LinearLayout btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_home);



        //跳转图片列表
        btn = (LinearLayout) findViewById(R.id.btnimg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,LmglistActivity.class);
                intent.putExtra("info","信息");
                startActivity(intent);
            }
        });


        //跳转视频列表
        btn = (LinearLayout) findViewById(R.id.bntvideo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,VideolistAcitivity.class);
                intent.putExtra("info","信息");
                startActivity(intent);
            }
        });


        //跳转文档列表
        btn = (LinearLayout) findViewById(R.id.bntdocument);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,DocumentActivity.class);
                intent.putExtra("info","信息");
                startActivity(intent);
            }
        });


        //跳转遥控器列表
        btn = (LinearLayout) findViewById(R.id.bnttelecontrol);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,TelecontrolActivity.class);
                intent.putExtra("info","信息");
                startActivity(intent);
            }
        });

    }
}
