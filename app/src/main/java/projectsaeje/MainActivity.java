package projectsaeje;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

import graphics.*;
import music.model.Notation.Measure;
import music.model.Notation.MusicalSymbols.MusicalSymbol;
import music.model.Notation.MusicalSymbols.Note;
import music.model.Notation.Notation;
import music.model.Notation.Staff;
import music.controller.psSoundPlayer;
import music.model.PureDataTypes.Images;

public class MainActivity extends Activity {

    public static Staff staff = null;
    public static int beatNum = 4;
    public static int beats = 4;
    private static psSoundPlayer s_player;
    public static int bpm = 120;
    private static EditText editText;

    public static DrawingView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.EditText);
        editText.setText("" + bpm);

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

    public int getBeatNum() { return beatNum; }

    public int getBeats() { return beats; }

    public Bitmap noteImageChooser(int tonalValue, int rhythmicValue) {

        int noteType;
        int noteNumber = tonalValue%12;

        String accidentalIdentifier;

        noteType = Images.noteImages.get(rhythmicValue - 1);

        String noteName = AudioHandler.theKey.getKey();

        //Log.d("Key is: ", noteName);

        if(noteName.length() > 1) {
            accidentalIdentifier = noteName.substring(1);
        } else {
            accidentalIdentifier = "";
        }

        if (AudioHandler.accidentals.contains(noteNumber)) {
            noteType = Images.flatImages.get(rhythmicValue - 1);
        }

        if (noteNumber < 0) {
            noteType = Images.restImages.get(rhythmicValue - 1);
        }

/*
        switch (accidentalIdentifier) {
            case "#":
                Log.d("Note is a: ", "Sharp");
                noteType = Images.sharpImages.get(rhythmicValue);
            case "b":
                Log.d("Note is a: ", "Flat");
                noteType = Images.flatImages.get(rhythmicValue);
            default:
                Log.d("Note is a:", "Natural");
        }
*/
        int height = MainActivity.drawView.drawCanvas.getHeight();
        int noteHeight = (int)(height*0.26042);// Scales the note to fit the staff
        int noteWidth  = (int)(height*0.39063);// Ditto

        Bitmap theBitmap = BitmapFactory.decodeResource(this.getResources(), noteType);
        theBitmap = Bitmap.createScaledBitmap(theBitmap, noteWidth, noteHeight, false);

        return theBitmap;
    }



    @Override
    protected void onPause() { super.onPause(); }

    @Override
    protected void onResume() { super.onResume(); }

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
                bpm = Integer.parseInt("" + editText.getText());

                //Log.d("", "bpm_int: " + bpm_int);
                //String bpm_string = "" + editText.getText();

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
                staff.clear();
                staff.setElements(new ArrayList<Notation>());

                drawView.startNew();
                MainActivity.drawView.draw(MainActivity.drawView.drawCanvas);

                return true;

            //case R.id.playback_notes:
                //Play back all notes in the composition
                /*
                for (Notation a_measure_notation : staff.getElements()) {
                    for (Notation a_musical_symbol_notation : (a_measure_notation.getElements())) {
                        MusicalSymbol a_musical_symbol = (MusicalSymbol) a_musical_symbol_notation;
                        s_player.addToPlaybackQueue(a_musical_symbol);
                    }
                }

                s_player.playBackComposition();
                */

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}