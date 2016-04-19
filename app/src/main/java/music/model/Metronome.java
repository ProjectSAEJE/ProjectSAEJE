package music.model;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import music.ExtraTypes.Tuple2;

/**
 * Created by austinnash on 2/11/16.
 */


public class Metronome extends Thread {

    private int bpm;
    private long msPerBeat;
    private Tuple2<Integer, Integer> timeSignature;
    private Boolean subdivide;
    private Boolean running;
    private SoundPool sPool;
    int clickSoundID;
    private Timer timer;
    private Activity activity;
    private boolean loaded = false;
    private int rp_precision_counter = 0;
    private int rhythmic_preciseness = 0;
    private int beatNum = 0;

    public Metronome() {}

    public Metronome(int bpm, Tuple2<Integer, Integer> timeSignature, Boolean subdivide, Activity activity, int rhythmic_preciseness) {
        this.bpm = bpm;
        this.msPerBeat = (long)(((float)(60) / (float)(bpm)) * 1000);   //milliseconds per beat = (seconds per beat) * 1000
        this.timeSignature = timeSignature;
        this.subdivide = subdivide;
        this.running = false;
        this.activity = activity;
        this.rhythmic_preciseness = rhythmic_preciseness;
        sPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loaded = true;
            }
        });
        //clickSoundID = sPool.load(activity.getApplicationContext(), R.raw.tamb_down_441, 1);

    }

    public class TimeKeeper extends TimerTask {

        @Override
        public void run() {
            running = true;
            ++rp_precision_counter;

            //If the rp counter modded by the lower numeral of the time signature is 0, then a beat has occurred.
            if ((rp_precision_counter % timeSignature._1) == 0) {
                signalBeatOccurrence();
            }
        }

    }

    @Override
    public void run() {
        this.timer = new Timer();
        //schedule to call Signal_RP_Occerence "timer task" to run its run() method every ms_per_rp_precision milliseconds.

        float rp_vals_per_quarter_note = ((float) rhythmic_preciseness) / ((float) 4);
        long ms_per_rp_precision = (long) (msPerBeat / rp_vals_per_quarter_note);

        //Log.d("in Metronome::run(), scheduling TimeKeeper for every ", ms_per_rp_precision + "milliseconds");

        //schedule the TimeKeeper task to run every 16th precision
        timer.scheduleAtFixedRate(new TimeKeeper(), 0, (long)(ms_per_rp_precision));
    }

    public void setRunningFalse() {
        running = false;
    }

    public int get_rp_precision_counter() {
        return rp_precision_counter;
    }

    public void signalBeatOccurrence() {
        //draw something on the canvas to signal a beat occurrence
        this.beatNum++;
        Log.d("Beat #", "" + this.beatNum);
    }

    /* //Wasn't working...
    private void clickSound() {
        //System.out.println("clickSound() called");
        this.clickNum += 1;

        if (loaded) {
            sPool.play(this.clickSoundID, (float) .5, (float) .5, 1, 0, (float) 1.0);
            //System.out.println("*CLICK* " + "#" + clickNum);
        }

    }
    */

}