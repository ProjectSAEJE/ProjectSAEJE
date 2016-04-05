package projectsaeje;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;

import android.content.Intent;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

import audio.CaptureThread;
import graphics.DrawingView;
import music.controller.RhythmicInterpreter;
import music.model.*;


public class AudioHandler extends Activity {

    private static boolean firstNote = true;

    public static Key theKey = null;

    public static ArrayList<Integer> bitmaps;
    public static Staff tempStaff;

    public static CaptureThread mCapture;
    private static RhythmicInterpreter rhythm_interp;

    public AudioHandler () {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        tempStaff = MainActivity.staff;

        populateArrays();

        captureNotes();
    }

    public void captureNotes(){
        Handler mHandler;

        mHandler = new Handler() {
            @Override
            public synchronized void handleMessage(Message m) {
                update(m.getData().getFloat("Freq"));
            }
        };

        mCapture = new CaptureThread(mHandler);
        mCapture.setRunning(true);
        mCapture.start();
    }

    public static void stopCapture(){

        mCapture.setRunning(false);

    }

    public static void destroy(){
        if (mCapture != null) {
            mCapture.setRunning(false);
            mCapture = null;
        }
    }

    private static void populateArrays() {
        bitmaps.add(R.drawable.sixteenth_note_single);              //1
        bitmaps.add(R.drawable.eighth_note_single);                 //2
        bitmaps.add(R.drawable.eighth_note_dotted_single_line);     //3
        bitmaps.add(R.drawable.quarter_note);                       //4

        bitmaps.add(R.drawable.quarter_note_dotted_single_line);    //6
        bitmaps.add(R.drawable.quarter_note_dotted_double_line);    //7
        bitmaps.add(R.drawable.half_note);                          //8
        bitmaps.add(R.drawable.half_tied_sixteenth);                //9
        bitmaps.add(R.drawable.half_tied_eighth);                   //10
        bitmaps.add(R.drawable.half_tied_dotted_eighth);            //11
        bitmaps.add(R.drawable.half_note_dotted_single_line);       //12
        bitmaps.add(R.drawable.dotted_half_tied_sixteenth);         //13
        bitmaps.add(R.drawable.half_note_dotted_double_line);       //14

        bitmaps.add(R.drawable.whole_note);                         //16
    }

    public int NoteEvaluator(float freq) {
        int pianoNoteNumber;
        double logCalcX = Math.log(freq / 440);
        double logCalcY = Math.log(2);

        pianoNoteNumber = (int) (12 * (logCalcX + 49) / logCalcY);
        return pianoNoteNumber;
    }

    public Bitmap noteImageBuilder(int tonalValue, Key theKey, int rhythmicValue){

        int noteType;
        int noteNumber = tonalValue%12;

        switch (rhythmicValue) {
            case 0:
                noteType = bitmaps.get(0); //Sixteenths
            case 1:
                noteType = bitmaps.get(1); //Eighths
            case 3:
                noteType = bitmaps.get(3); //Quarters
            case 7:
                noteType = bitmaps.get(7); //Halves
            case 15:
                noteType = bitmaps.get(15); //Wholes
            default:
                noteType = bitmaps.get(3); //default quarters
        }

        String noteName = theKey.key.get(noteNumber);
        String accidentalIdentifier = noteName.substring(1);

        int noteGet;

        switch (accidentalIdentifier) {
            case "#":
                noteGet = 0;
            case "b":
                noteGet = 1;
            default:
                noteGet = 2;
        }


        Bitmap theBitmap = BitmapFactory.decodeResource(this.getResources(), noteType);
        theBitmap = Bitmap.createScaledBitmap(theBitmap, 300, 300, false);

        return theBitmap;
    }

    public void update(float freq) {

        Note aNote;
        int rhythmicValue = 4;

        int notesTone = NoteEvaluator(freq);

        if (firstNote)
            theKey = new Key(notesTone);
            firstNote = false;

        //This section is semantically inadequate, but serves as a temporary debug: We would not be building a new note every time a tone is passed to the RhythmicInterpretter.
        //Also, the "5.33" type values of a dotted eighth note are lost in the conversion from float to integer.
        rhythm_interp.update(notesTone);
        //rhythmicValue = (int) (rhythm_interp.getRhythmicValueOfEndedNote());
        //

        Bitmap notesImage = noteImageBuilder(notesTone, theKey, rhythmicValue);

        aNote = new Note(notesTone, notesImage, rhythmicValue);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:

                //changes stop icon back to play icon on the record button
                item.setIcon(R.drawable.ic_play_arrow);
                item.setTitle(R.string.Resume);
                AudioHandler.stopCapture();

                return true;

            case R.id.open:

                return true;

            case R.id.save:

                return true;

            case R.id.clear:
                //clear the staff

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
