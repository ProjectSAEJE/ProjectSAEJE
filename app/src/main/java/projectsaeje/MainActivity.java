package projectsaeje;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

import graphics.*;
import music.model.Notation.Measure;
import music.model.Notation.MusicalSymbols.MusicalSymbol;
import music.model.Notation.MusicalSymbols.Note;
import music.model.Notation.Notation;
import music.model.Notation.Staff;
import music.controller.psSoundPlayer;

public class MainActivity extends Activity {

    public static Staff staff = null;
    private static int beatNum = 4;
    private static int beats = 4;
    private static psSoundPlayer s_player;

    public static DrawingView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(staff == null) {
            staff = new Staff();
            drawView = (DrawingView)findViewById(R.id.drawing);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        AudioHandler.destroy();
    }

    public int getBeatNum() {
        return beatNum;
    }

    public int getBeats() {
        return beats;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:

                item.setIcon(R.drawable.ic_stop);   //set record icon to stop icon
                item.setTitle(R.string.Pause);

                Intent intent = new Intent(this, AudioHandler.class);
                startActivity(intent);

                return true;

            case R.id.open:

                return true;

            case R.id.save:

                //staff.save();

                return true;

            case R.id.clear:
                //clear the staff

                staff.setElements(new ArrayList<Notation>());
                staff.makeStartingCurrentMeasures();

                drawView.startNew();

                return true;

            //case R.id.metronome:
                //pull up menu for the metronome

                //show_metronome_menu();

                //bpm = get_user_bpm_input();
                //is_on = get_user_is_on_input();

            //case R.id.playback_notes:
                //Play back all notes in the composition
                for (Notation a_measure_notation : staff.getElements()) {
                    for (Notation a_musical_symbol_notation : a_measure_notation.getElements()) {
                        MusicalSymbol a_musical_symbol = (MusicalSymbol) a_musical_symbol_notation;
                        s_player.addToPlaybackQueue(a_musical_symbol);
                    }
                }

                s_player.playBackComposition();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}