package com.jsdev.ruime;

import java.io.File;
import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
 

public class VideoPlayer extends Activity implements SurfaceHolder.Callback {

    String path;
    private MediaPlayer mp;
    private SurfaceView mPreview;
    private SurfaceHolder holder;
    boolean pausing = false;
    public static String filepath="";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        mPreview = (SurfaceView)findViewById(R.id.my_video_view);
        holder = mPreview.getHolder();
        holder.setFixedSize(800, 480);
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mp = new MediaPlayer();
//        filepath = "android.resource://" + getPackageName().toString() + "/" +R.raw.lesson_1_brush_teeth;
//        try{
//            Intent intent = getIntent();
//
//            Uri fileuri = intent.getData();
//            filepath=fileuri.getPath();
//        }catch(Exception e){}


    }
    protected void onPause(){
        super.onPause();
        mp.release();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        mp.setDisplay(holder);
        play();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity( intent );
    	
    }
    void play(){
        try {
        	filepath = "android.resource://" + getPackageName().toString() + "/" +R.raw.lesson_1_brush_teeth;
        	Uri uri = Uri.parse("android.resource://" + getPackageName().toString() + "/" +R.raw.lesson_1_brush_teeth);

//        	File pathz = android.os.Environment.getExternalStorageDirectory();
//        	mp.setDataSource(pathz + "/Download/lesson_3_wash_face.mp4");
            mp.setDataSource(VideoPlayer.this, uri);

            mp.prepare(); 

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }
}
