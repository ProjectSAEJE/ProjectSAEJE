package music.controller;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.net.URI;
import java.util.ResourceBundle;

/**
 * Created by austinnash on 4/24/16.
 */
public class psSoundPlayer {

    private Context context;
    private MediaPlayer m_player;

    public psSoundPlayer(Context context) {
        this.context = context;
    }

    public void delete() {
        m_player.release();
        m_player = null;
    }

    public void playSound(Uri resource) {
        m_player = m_player.create(context, /*e.g. R.raw.sound_file_1 */ resource);
        m_player.start(); // no need to call prepare(); create() does that for you
    }

}
