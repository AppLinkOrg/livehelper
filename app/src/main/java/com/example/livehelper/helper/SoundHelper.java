package com.zrkaxt.testmachine.helper;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;


public class SoundHelper {
    private static SoundHelper Instance;

    private SoundHelper() {

    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public Context ctx;

    public static SoundHelper GetInstance() {
        if (SoundHelper.Instance == null) {
            SoundHelper.Instance = new SoundHelper();
        }
        return SoundHelper.Instance;
    }

    public void playVoice(int rawid){
        //MediaPlayer wm=MediaPlayer.create(ctx, rawid);
        //wm.start();
    }
}
