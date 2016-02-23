package music;

import android.media.MediaPlayer;

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
    private int[] timeSignature;
    private Boolean subdivide;
    private Boolean running;
    private MediaPlayer mPlayer;

    public Metronome() {}

    public Metronome(int bpm, int[] timeSignature, Boolean subdivide) {
        this.bpm = bpm;
        this.msPerBeat = (int)((float)(60) / (float)(bpm)) * 1000;   //milliseconds per beat = (seconds per beat) * 1000
        this.timeSignature = timeSignature;
        this.subdivide = subdivide;
        this.running = false;
        //mPlayer = new MediaPlayer("clickSound.wav");
    }

    public class TimeKeeper extends TimerTask {

        public void run() {
            running = true;
            clickSound();
            broadcastBeatOccurrence();
        }
    }

    public void start() {
        Timer timer = new Timer();
        //schedule to call TimeKeepers run() method every msPerBeat milliseconds.
        timer.scheduleAtFixedRate(new TimeKeeper(), 0, msPerBeat);
    }

    public void stop() {
        running = false;
    }

    private void clickSound() {
        mPlayer.start();
    }

    private void broadcastBeatOccurrence() {
        //broadcast that a beat has happened to the application
    }
}