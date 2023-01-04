package com.example.livehelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.livehelper.socket.SocketService;

public class MainActivity extends AppCompatActivity {
    TextView btn;
    ImageView btnimg;




    private int permissionRequestCode = 100;
    private int captureRequestCode = 1;
    private MediaProjectionManager mediaProjectionManager;
    private SocketService socketService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        //跳转
//        btn = (TextView) findViewById(R.id.okewm);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
//                intent.putExtra("info","信息");
//                startActivity(intent);
//            }
//        });


        //跳转设置
        btnimg = (ImageView) findViewById(R.id.btnsetting);
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                intent.putExtra("info","信息");
                startActivity(intent);
            }
        });



    }
    private void init() {
        mediaProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.okewm:
                startCast();
                break;
        }
    }
    private void startCast(){
        PermissionUtil.checkPermission(this,PermissionUtil.storagePermissions,permissionRequestCode);
        Intent intent = mediaProjectionManager.createScreenCaptureIntent();
        startActivityForResult(intent,captureRequestCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK){
            return;
        }
        if(requestCode == this.captureRequestCode){
            startCast(resultCode,data);
        }
    }
    private void startCast(int resultCode,Intent data){
        MediaProjection mediaProjection = mediaProjectionManager.getMediaProjection(resultCode,data);
        if (mediaProjection == null){
            return;
        }
        socketService = new SocketService();
        socketService.start(mediaProjection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (socketService != null){
            socketService.colse();
        }
    }


}