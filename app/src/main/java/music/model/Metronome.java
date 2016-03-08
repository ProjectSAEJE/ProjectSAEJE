package music;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by austinnash on 2/11/16.
 */

public class Metronome extends Thread {

    private int bpm;
    private long msPerBeat;
    private ArrayList<Integer> timeSignature;
    private Boolean subdivide;
    private Boolean running;
    private SoundPool sPool;
    int clickSoundID;
    private Timer timer;
    private Activity activity;
    private boolean loaded = false;
    private int clickNum = 0;

    public Metronome() {}

    public Metronome(int bpm, ArrayList<Integer> timeSignature, Boolean subdivide, Activity activity) {
        this.bpm = bpm;
        this.msPerBeat = (long)(((float)(60) / (float)(bpm)) * 1000);   //milliseconds per beat = (seconds per beat) * 1000
        this.timeSignature = timeSignature;
        this.subdivide = subdivide;
        this.running = false;
        this.activity = activity;
        sPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loaded = true;
            }
        });
        clickSoundID = sPool.load(activity.getApplicationContext(), R.raw.tamb_down_441, 1);
    }

    public class TimeKeeper extends TimerTask {

        @Override
        public void run() {
            running = true;
            clickSound();
            broadcastBeatOccurrence();
        }
    }

    @Override
    public void run() {
        this.timer = new Timer();
        //schedule to call TimeKeepers run() method every msPerBeat milliseconds.
        timer.scheduleAtFixedRate(new TimeKeeper(), 0, msPerBeat);
    }

    public void setRunningFalse() {
        running = false;
    }

    public int getClickNum() {
        return clickNum;
    }

    private void clickSound() {
        //System.out.println("clickSound() called");
        this.clickNum += 1;
        /*
        if (loaded) {
            sPool.play(this.clickSoundID, (float) .5, (float) .5, 1, 0, (float) 1.0);
            //System.out.println("*CLICK* " + "#" + clickNum);
        }
        */
    }

    private void broadcastBeatOccurrence() {
        //broadcast that a beat has happened to the application
    }
}