package music.controller;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.net.URI;
import java.util.ArrayList;
import java.util.ResourceBundle;

import music.model.Notation.MusicalSymbols.MusicalSymbol;

/**
 * Created by austinnash on 4/24/16.
 */
public class psSoundPlayer {

    private Context context;
    private MediaPlayer mPlayer;
    private ArrayList<Integer> playbackQueue;
    private Handler mHandler;

    public psSoundPlayer(Context context) {
        this.context = context;

        mHandler = new Handler() {
            @Override
            public synchronized void handleMessage(Message m) {
                OutputTonalValueAtPosition(m.getData().getInt("Current_Beat_Precision"));
            }
        };

    }

    public void delete() {
        mPlayer.release();
        mPlayer = null;
    }

    public void playSound(int resource) {
        mPlayer = mPlayer.create(context, /*e.g. R.raw.sound_file_1 */ resource);
        mPlayer.start(); // no need to call prepare(); create() does that for you
    }

    public void addToPlaybackQueue(MusicalSymbol symbol) {
        //
        // If the musical symbol is x rhythmic precisions long, add the tonal value to the
        //playback queue x times.
        //
        int x = symbol.getRhythmicValue();
        for (int i = 0; i < x; ++i) {
            playbackQueue.add(symbol.getTonalValue());
        }
    }

    public void playBackComposition() {
        for (int aTonalValue : playbackQueue) {
            int resourceIdForThisTonalValue = getResourceIDForTonalValue(aTonalValue);
            //*** When Metronome signals the occurrence of a rhythmic precision ***///
            playSound(resourceIdForThisTonalValue);
        }
    }

    public int getResourceIDForTonalValue(int aTonalValue) {
        int ID = 0;
        //find the ID
        return ID;
    }

    public void OutputTonalValueAtPosition(int pos) {
        int tonalValue = this.playbackQueue.get(pos);
        int resourceId = getResourceIDForTonalValue(tonalValue);
        playSound(resourceId);
    }
}
