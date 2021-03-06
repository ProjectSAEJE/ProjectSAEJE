package projectsaeje;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import audio.CaptureThread;
import music.ExtraTypes.*;
import music.model.*;
import music.model.Notation.MusicalSymbols.*;
import music.model.PureDataTypes.*;
import music.model.Notation.*;

public class AudioHandler extends Activity {

    private static boolean firstNote = true;
    public static Key theKey = null;
    private Measure tempMeasure;
    public static CaptureThread mCapture;

    //Variables used for rhythmic interpretation
    private int previouslyUpdatedTone;
    private int rhythmicPrecision = 16;
    private int startingPrecisionOfNote;
    private int lengthInSixteenthsOfEndedNote;
    private Metronome metronome;

    //Variables used for tonal interpretation
    public static ArrayList<Integer> accidentals;

    public AudioHandler () {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.audiohandler);

        TextView textView = (TextView) findViewById(R.id.TextView);
        textView.setTextColor(Color.BLACK);
        textView.setText("BPM: " + MainActivity.bpm);

        this.tempMeasure = new Measure(new ArrayList<Notation>(), 4, 4);
        MainActivity.staff.addMeasure(this.tempMeasure);

        Images images = new Images();

        images.populateArrays();

        accidentals = new ArrayList<>();
        accidentals.add(0);
        accidentals.add(2);
        accidentals.add(5);
        accidentals.add(7);
        accidentals.add(10);

        Tuple2<Integer, Integer> timeSigntature = new Tuple2<>(4, 4);
        metronome = new Metronome(MainActivity.bpm, timeSigntature, false, this, rhythmicPrecision, MainActivity.drawView);
        captureNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items2, menu);
        return super.onCreateOptionsMenu(menu);
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
        mCapture.interrupt();
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

        pianoNoteNumber = (int) ((12 * (logCalcX / logCalcY)) + 49);
        //Log.d("Tonal value: ", " " + pianoNoteNumber);
        return pianoNoteNumber;
    }

    public Bitmap noteImageChooser(int tonalValue, int rhythmicValue) {

        int noteType;
        int noteNumber = tonalValue%12;

        String accidentalIdentifier;

        noteType = Images.noteImages.get(rhythmicValue - 1);

        String noteName = theKey.getKey();

        //Log.d("Key is: ", noteName);

        if(noteName.length() > 1) {
            accidentalIdentifier = noteName.substring(1);
        } else {
            accidentalIdentifier = "";
        }

        if (accidentals.contains(noteNumber)) {
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

    public Bitmap noteImageChooser(int tonalValue, int rhythmicValue, Context context) {

        int noteType;
        int noteNumber = tonalValue%12;

        String accidentalIdentifier;

        noteType = Images.noteImages.get(rhythmicValue - 1);

        String noteName = theKey.getKey();

        //Log.d("Key is: ", noteName);

        if(noteName.length() > 1) {
            accidentalIdentifier = noteName.substring(1);
        } else {
            accidentalIdentifier = "";
        }

        if (accidentals.contains(noteNumber)) {
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

        Bitmap theBitmap = BitmapFactory.decodeResource(context.getResources(), noteType);
        theBitmap = Bitmap.createScaledBitmap(theBitmap, noteWidth, noteHeight, false);

        return theBitmap;
    }

    private int updateRhythm(int newTone) {
        //If the tone is the same tone as the previous tone, the note is still being sung and we simply return 0
        if (newTone == previouslyUpdatedTone) {
            return -1;  //return -1 to indicate that the note length is still being continued/determined
        }

        //If the tone is not the same, we change the previously updated tone to the new tone, calculate the length of the note, and reset the starting precision
        else {
            previouslyUpdatedTone = newTone;
            //The note has just ended, so the length of the note is the number of precisions that have passed since the one at which it began
            lengthInSixteenthsOfEndedNote = metronome.getRpPrecisionCounter() - startingPrecisionOfNote;

            if (lengthInSixteenthsOfEndedNote == 0) {
                return -1;
            }

            //reset start time for the newest note
            startingPrecisionOfNote = metronome.getRpPrecisionCounter();

            //temporary ceiling value of 16
            if (lengthInSixteenthsOfEndedNote > 16) {
                return 16;
            }
            else {
                return lengthInSixteenthsOfEndedNote; //then return the length of the note in sixteenth precisions
            }
        }
    }

    private float getRhythmicValueOfEndedNoteWithLength(int length) {
        return ((float) rhythmicPrecision) / ((float) length);
    }

    public void rebuildNote(MusicalSymbol aNote, Context context) {
        aNote.setScaledBitmap(noteImageChooser(aNote.getTonalValue(), aNote.getRhythmicValue(), context));
    }

    public void update(float freq) {

        Note aNote;
        int rhythmicValue = -1;
        Bitmap notesImage = null;
        Bitmap secondaryNotesImage = null;
        int valueTilMeasureFull = this.tempMeasure.valueTilMeasureFull();

        int notesTone = NoteEvaluator(freq);

        if (firstNote)
            theKey = new Key(notesTone);
            firstNote = false;

        rhythmicValue = updateRhythm(notesTone);

        //Log.d("rhythmicValue: ", "" + rhythmicValue);
        //Log.d("Value:", "" + valueTilMeasureFull);

        //If a note has recently ended, rhythmic value will be nonzero.
        //In other words, only construct the recently ended note if update has been called with a new tonal value.

        if (rhythmicValue != -1) {
            if (rhythmicValue <= valueTilMeasureFull) {
                notesImage = noteImageChooser(notesTone, rhythmicValue);
                //Log.d("This", "One");
            } else {
                if(valueTilMeasureFull != 0) {
                    notesImage = noteImageChooser(notesTone, Math.abs(valueTilMeasureFull));
                }
                secondaryNotesImage = noteImageChooser(notesTone, rhythmicValue - Math.abs(valueTilMeasureFull));
                //Log.d("Or", "That One");
            }

            if (secondaryNotesImage != null) {
                if (notesImage != null) {
                    aNote = new Note(notesTone, notesImage, Math.abs(valueTilMeasureFull));
                    this.tempMeasure.addNote(aNote);
                }

                if (MainActivity.staff.getNumElements() < 2) {
                    MainActivity.staff.addMeasure(0, new Measure(this.tempMeasure.getElements(), 4, 4));
                } else {
                    MainActivity.staff.addMeasure((MainActivity.staff.getNumElements() - 1), new Measure(this.tempMeasure.getElements(), 4, 4));
                }

                this.tempMeasure.clear();

                aNote = new Note(notesTone, secondaryNotesImage, rhythmicValue - Math.abs(valueTilMeasureFull));
                this.tempMeasure.addNote(aNote);

                MainActivity.staff.setCurrentMeasures();
                //MainActivity.drawView.changeX(500);

            } else {
                aNote = new Note(notesTone, notesImage, rhythmicValue);
                this.tempMeasure.addNote(aNote);
            }
        }

        //Log.d("Testing the measures", "" + MainActivity.staff.getElements());
        //Log.d("TESTING", MainActivity.staff.getCurrentMeasures().toString());
        MainActivity.drawView.startNew();
        MainActivity.drawView.draw(MainActivity.drawView.drawCanvas);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.pause:

                destroy();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                metronome.pause();

                return true;

            case R.id.open:

                return true;

            case R.id.save:

                return true;

            case R.id.clear:
                MainActivity.staff.clear();
                destroy();
                MainActivity.drawView.startNew();
                MainActivity.drawView.draw(MainActivity.drawView.drawCanvas);

                intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                return true;

            //case R.id.metronome:
                //pull up menu for the metronome

                //show_metronome_menu();

                //bpm = get_user_bpm_input();
                //is_on = get_user_is_on_input();
                //

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
