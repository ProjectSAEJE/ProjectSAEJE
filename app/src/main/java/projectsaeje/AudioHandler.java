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

import graphics.DrawingView;
import audio.CaptureThread;
import music.model.Metronome;
import music.model.Key;
import music.model.Note;

import music.model.*;


public class AudioHandler extends Activity {

    private static boolean firstNote = true;

    public static Key theKey = null;

    public static Measure tempMeasure;

    public static CaptureThread mCapture;

    //Variables used for rhythmic interpretation
    private int previously_updated_tone;
    private int rhythmic_preciseness;
    private int starting_precision_of_note;
    private int length_in_sixteenths_of_ended_note;
    private Metronome metronome = new Metronome();

    public AudioHandler () {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tempMeasure = new Measure();
        tempMeasure.setBeats(4);
        tempMeasure.setNumBeats(4);

        Images.populateArrays();

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

        noteType = Images.noteImages.get(rhythmicValue);

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
        int rhythmicValue = 3;
        Bitmap notesImage = null;
        Bitmap secondaryNotesImage = null;
        int valueTilMeasureFull = tempMeasure.valueTilMeasureFull();

        int notesTone = NoteEvaluator(freq);

        if (firstNote)
            theKey = new Key(notesTone);
            firstNote = false;

        //rhythmicValue = updateRhythm(notesTone);

        //If a note has recently ended, rhythmic value will be nonzero.
        //In other words, only construct the recently ended note if update has been called with a new tonal value.
        if (rhythmicValue != 0) {
            if (rhythmicValue < valueTilMeasureFull) {
                notesImage = noteImageBuilder(notesTone, theKey, rhythmicValue);
            } else {
                notesImage = noteImageBuilder(notesTone, theKey, valueTilMeasureFull);
                secondaryNotesImage = noteImageBuilder(notesTone, theKey, rhythmicValue-valueTilMeasureFull);
            }
        }

        if(secondaryNotesImage != null) {
            aNote = new Note(notesTone, notesImage, valueTilMeasureFull);

            tempMeasure.addNote(aNote);
            MainActivity.staff.addMeasure(tempMeasure);
            tempMeasure.clear();

            aNote = new Note(notesTone, secondaryNotesImage, rhythmicValue - valueTilMeasureFull);
            tempMeasure.addNote(aNote);

        } else {
            aNote = new Note(notesTone, notesImage, rhythmicValue);
            tempMeasure.addNote(aNote);
        }

        MainActivity.drawView.staffUpdatedDrawPlease();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:

                //changes stop icon back to play icon on the record button
                item.setIcon(R.drawable.ic_play_arrow);
                item.setTitle(R.string.Resume);
                stopCapture();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

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
