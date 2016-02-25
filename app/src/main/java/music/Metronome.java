package music;

import android.media.AudioManager;
import android.media.SoundPool;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by austinnash on 2/11/16.
 */

public class Metronome {

    private int bpm;
    private int msPerBeat;
    private ArrayList<Integer> timeSignature;
    private Boolean subdivide;
    private Boolean running;
    private SoundPool sPool;
    int clickSoundID;
    private Timer timer;

    public Metronome() {}

    public Metronome(int bpm, ArrayList<Integer> timeSignature, Boolean subdivide) {
        this.bpm = bpm;
        this.msPerBeat = (int)((float)(60) / (float)(bpm)) * 1000;   //milliseconds per beat = (seconds per beat) * 1000
        this.timeSignature = timeSignature;
        this.subdivide = subdivide;
        this.running = false;
    }

    public class TimeKeeper extends TimerTask {

        @Override
        public void run() {
            running = true;
            clickSound();
            broadcastBeatOccurrence();
        }
    }

    public void start() {
        this.timer = new Timer();
        //schedule to call TimeKeepers run() method every msPerBeat milliseconds.
        TimeKeeper tk = new TimeKeeper();
        this.timer.scheduleAtFixedRate(tk, (long) 0, (long) msPerBeat);
    }

    public void stop() {
        running = false;
    }

    private void clickSound() {
        sPool.play(this.clickSoundID, (float).5, (float).5, 1, 0, (float) 1.0);
    }

    private void broadcastBeatOccurrence() {
        //broadcast that a beat has happened to the application
    }
}