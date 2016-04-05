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

//test line for push

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
    private int rp_precision_counter = 0;
    private int rhythmic_preciseness = 0;

    public Metronome() {}

    public Metronome(int bpm, ArrayList<Integer> timeSignature, Boolean subdivide, Activity activity, int rhythmic_preciseness) {
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
        clickSoundID = sPool.load(activity.getApplicationContext(), R.raw.tamb_down_441, 1);
    }

    public class Signal_RP_Occurrence extends TimerTask {

        @Override
        public void run() {
            running = true;
            ++rp_precision_counter;
        }

    }

    @Override
    public void run() {
        this.timer = new Timer();
        //schedule to call Signal_RP_Occerence "timer task" to run its run() method every ms_per_rp_precision milliseconds.

        float rp_vals_per_quarter_note = ((float) rhythmic_preciseness) / ((float) 4);
        long ms_per_rp_precision = (long) (msPerBeat / rp_vals_per_quarter_note);

        //schedule the TimeKeeper task to run every 16th precision
        timer.scheduleAtFixedRate(new Signal_RP_Occurrence(), 0, ms_per_rp_precision);
    }

    public void setRunningFalse() {
        running = false;
    }

    public int get_rp_precision_counter() {
        return rp_precision_counter;
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