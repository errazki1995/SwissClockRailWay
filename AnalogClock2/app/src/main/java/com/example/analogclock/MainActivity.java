package com.example.analogclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
MyClockView clockView;
ImageView imageView;
private final Handler handler;
    private Thread timerThread;
    private Runnable refreshRunnable;
    private ImageView imageObject;
    private  long radius;
    private long elapsedTime;
    private MyClockView.State state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =(ImageView) findViewById(R.id.clockbg);
        state = new MyClockView.State();
        handler = new Handler(this.getMainLooper());
        setImageObject(imageView);
         clockView = new MyClockView(this.getApplicationContext(),imageView);
        runnableInitialisation();

    }


public void control(View v){
       clockView.start();
}





    public void formatTime(long time){
        // long tempSec = time / (1000 * 1000 * 1000);
        //long sec = tempSec % 60;
        //long min = (tempSec / 60) % 60;
        //long hour = (tempSec / (60 * 60)) % 24;
        long seconds = (time/ (1000*1000*1000))%60;
        long minutes = seconds %60;
        long hours = minutes%60;
        state.setSeconds(seconds);
        state.setMinutes(minutes);
        state.setHours(hours);
        Log.w("Time",state.getHours()+":"+state.getMinutes()+":"+state.getSeconds());
    }


    public ImageView getImageObject() {
        return imageObject;
    }

    public void setImageObject(ImageView imageObject) {
        this.imageObject = imageObject;
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    public Runnable getRefreshRunnable() {
        return refreshRunnable;
    }

    public void setRefreshRunnable(Runnable refreshRunnable) {
        this.refreshRunnable = refreshRunnable;
    }

    public void startChrono() {

        state.setStartTime(System.nanoTime());
        updateChronoOnUiThreadMethod();
    }

    public void refreshChrono() {
        formatTime(state.getElapsedTime());
        //state.getSec eTc...
    }


    public void stopChrono(){
        handler.removeCallbacks(getRefreshRunnable());
    }

    public void start() {
        startChrono();
    }


    public void runnableInitialisation() {
        refreshRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.interrupted()) {
                        refreshChrono();
                    }
                    try {
                        Thread.sleep(500);

                    } catch (InterruptedException e) {

                    }

                } catch (Exception e) {
                }
            }
        };
        setRefreshRunnable(refreshRunnable);

    }
    public void updateChronoOnUiThreadMethod() {
        handler.postDelayed(getRefreshRunnable(),1000);

    }

}
