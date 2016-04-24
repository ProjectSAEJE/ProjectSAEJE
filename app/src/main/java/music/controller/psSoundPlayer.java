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
    private MediaPlayer m_player;
    private ArrayList<int> playback_queue;
    private Handler mHandler;

    public psSoundPlayer(Context context) {
        this.context = context;
    }

    public void delete() {
        m_player.release();
        m_player = null;
    }

    public void playSound(int resource) {
        m_player = m_player.create(context, /*e.g. R.raw.sound_file_1 */ resource);
        m_player.start(); // no need to call prepare(); create() does that for you
    }

    mHandler = new Handler() {
        @Override
        public synchronized void handleMessage(Message m) {
            OutputTonalValueAtPosition(m.getData().getInt("Current_Beat_Precision"));
        }
    };

    public void addToPlaybackQueue(MusicalSymbol symbol) {
        //
        // If the musical symbol is x rhythmic precisions long, add the tonal value to the
        //playback queue x times.
        //
        int x = symbol.getRhythmicValue();
        for (int i = 0; i < x; ++i) {
            playback_queue.add(symbol.getTonalValue());
        }
    }

    public void playBackComposition() {
        for (int a_tonal_value : playback_queue) {
            int resource_ID_for_this_tonal_value = getResourceIDForTonalValue(a_tonal_value);
            //*** When Metronome signals the occurrence of a rhythmic precision ***///
            playSound(resource_ID_for_this_tonal_value);
        }
    }

    public int getResourceIDForTonalValue(int a_tonal_value) {
        int ID = null;
        //find the ID
        return ID;
    }

    public void OutputTonalValueAtPosition(int pos) {
        int tonal_value = playback_queue[pos];
        int resource_id = getResourceIDForTonalValue(tonal_value);
        playSound(resource_id);
    }
}
