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
import music.Metronome;
import music.model.Key;
import music.model.Note;
import projectsaeje.MainActivity;
import graphics.DrawingView;
import music.controller.RhythmicInterpreter;
import music.model.*;


public class AudioHandler extends Activity {

    private static boolean firstNote = true;

    public static Key theKey = null;

    public static ArrayList<Integer> bitmaps;
    public static Staff tempStaff;

    public static CaptureThread mCapture;

    //Variables used for rhythmic interpretation
    private int previously_updated_tone;
    private int rhythmic_preciseness;
    private int starting_precision_of_note;
    private int length_in_sixteenths_of_ended_note;
    private Metronome metronome;

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
        metronome.start();
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
        bitmaps.add(R.drawable.sixteenth_note_single); //1
        bitmaps.add(R.drawable.eighth_note_single); //2
        bitmaps.add(R.drawable.eighth_note_dotted_single_line); //3
        bitmaps.add(R.drawable.quarter_note); //4

        bitmaps.add(R.drawable.quarter_note_dotted_single_line); //6
        bitmaps.add(R.drawable.quarter_note_dotted_double_line); //7
        bitmaps.add(R.drawable.half_note); //8

        bitmaps.add(R.drawable.whole_note); //16
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
        theBitmap = Bitmap.createScaledBitmap(theBitmap,300,300,false);

        return theBitmap;
    }

    private int updateRhythm(int new_tone) {
        //If the tone is the same tone as the previous tone, the note is still being sung and we simply return 0
        if (new_tone == previously_updated_tone) {
            return 0;  //return 0 to indicate that the note length is still being continued/determined
        }

        //If the tone is not the same, we change the previously updated tone to the new tone, calculate the length of the note, and reset the starting precision
        else {
            previously_updated_tone = new_tone;
            //The note has just ended, so the length of the note is the number of precisions that have passed since the one at which it began
            length_in_sixteenths_of_ended_note = metronome.get_rp_precision_counter() - starting_precision_of_note;
            //reset start time for the newest note
            return length_in_sixteenths_of_ended_note; //then return the length of the note in sixteenth precisions
        }
    }

    //returns 16 for 16th note, 5.33 for dotted-eighth note,  4 for quarter note, etc.
    private float getRhythmicValueOfEndedNoteWithLength(int length) {
        return ((float) rhythmic_preciseness) / ((float) length);
    }

    public void update(float freq) {

        Note aNote;
        int rhythmicValue = 4;

        int notesTone = NoteEvaluator(freq);

        if (firstNote)
            theKey = new Key(notesTone);
            firstNote = false;

        rhythmicValue = updateRhythm(notesTone);

        //If a note has recently ended, rhythmic value will be nonzero.
        //In other words, only construct the recently ended note if update has been called with a new tonal value.
        if (rhythmicValue != 0) {
            aNote = new Note(notesTone, notesImage, rhythmicValue);
        }
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
