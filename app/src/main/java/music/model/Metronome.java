package music.model;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import graphics.DrawingView;
import music.ExtraTypes.Tuple2;
import projectsaeje.MainActivity;

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
    private int rpPrecisionCounter = 0;
    private int rhythmicPrecision = 0;
    private int beatNum = 0;
    private boolean isPlayingBack;
    private DrawingView dView;
    public boolean isTimeToDrawBeatSignifier = false;

    public Metronome() {}

    public Metronome(int bpm, Tuple2<Integer, Integer> timeSignature, Boolean subdivide, Activity activity, int rhythmicPrecision, DrawingView dView) {
        this.bpm = bpm;
        this.msPerBeat = (long)(((float)(60) / (float)(bpm)) * 1000);   //milliseconds per beat = (seconds per beat) * 1000
        this.timeSignature = timeSignature;
        this.subdivide = subdivide;
        this.running = false;
        this.activity = activity;
        this.rhythmicPrecision = rhythmicPrecision;
        this.dView = dView;
        sPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loaded = true;
            }
        });
        //clickSoundID = sPool.load(activity.getApplicationContext(), R.raw.tamb_down_441, 1);
        MainActivity.drawView.timeSigTopNumber = timeSignature._0;
    }

    public class TimeKeeper extends TimerTask {

        @Override
        public void run() {
            running = true;
            ++rpPrecisionCounter;

            //Begin by assuming that is not time to draw the beat signifier
            //Log.d("setting dView.is_drawing_... to false", " ");

            //If the rp counter modded by the lower numeral of the time signature is 0,
            // then a beat has occurred.
            if ((rpPrecisionCounter % timeSignature._1) == 0) {
                //Log.d(" ", "signaled beat occurence");
                signalBeatOccurrence();
            }
            else {
                //Log.d(" ", "did not signal beat occurence");
                MainActivity.drawView.aBeatJustOccurred = false;
                ;
            }

            if ((isPlayingBack)) {
                /*
                // Get current rpPrecisionCounter
                float current_rp_precision_num = rpPrecisionCounter;

                // Send it to psSoundPlayer to have it output the sound at that position in the composition.
                Message msg = mHandler.obtainMessage();
                Bundle b = new Bundle();
                b.putInt("Current_Beat_Precision", current_rp_precision_num);
                msg.setData(b);
                mHandler.sendMessage(msg);
                ;
                */
            }
        }
    }

    @Override
    public void run() {
        this.timer = new Timer();
        //schedule to call Signal_RP_Occerence "timer task" to run its run() method every msPerRpPrecision milliseconds.

        float rpValsPerQuarterNote = ((float) rhythmicPrecision) / ((float) 4);
        long msPerRpPrecision = (long) (msPerBeat / rpValsPerQuarterNote);

        //Log.d("in Metronome::run(), scheduling TimeKeeper for every ", msPerRpPrecision + "milliseconds");

        //schedule the TimeKeeper task to run every 16th precision
        timer.scheduleAtFixedRate(new TimeKeeper(), 0, (long)(msPerRpPrecision));
    }

    public void setRunningFalse() {
        running = false;
    }

    public int getRpPrecisionCounter() {
        return rpPrecisionCounter;
    }

    public void signalBeatOccurrence() {
        //draw something on the canvas to signal a beat occurrence
        this.beatNum++;
        //Log.d("Beat #", "" + this.beatNum);
        MainActivity.drawView.aBeatJustOccurred = true;
        MainActivity.drawView.metronomeBeatNum = beatNum;
    }

    public void pause() {
        timer.cancel();
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