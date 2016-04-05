package music.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

import audio.CaptureThread;
import music.Metronome;
import music.model.Key;
import music.model.Note;
import projectsaeje.MainActivity;


public class AudioHandler {

    private static boolean firstNote = true;

    private static Key theKey = null;

    public static ArrayList<Integer> wholes = new ArrayList<>();
    public static ArrayList<Integer> halves = new ArrayList<>();
    public static ArrayList<Integer> quarters = new ArrayList<>();
    public static ArrayList<Integer> eighths = new ArrayList<>();
    public static ArrayList<Integer> sixteenths = new ArrayList<>();

    public static ArrayList<Integer> svals = new ArrayList<>();
    public static ArrayList<Integer> yvals = new ArrayList<>();

    public static CaptureThread mCapture;

    //Variables used for rhythmic interpretation
    private int previously_updated_tone;
    private int rhythmic_preciseness;
    private int starting_precision_of_note;
    private int length_in_sixteenths_of_ended_note;
    private Metronome metronome;

    public AudioHandler (){

    }

    public static void populateArrays(){
        int middle = MainActivity.drawView.drawCanvas.getHeight()/2;

        quarters.add(R.drawable.ic_quarter_note);
        quarters.add(R.drawable.ic_quarter_note_sharp_space);
        quarters.add(R.drawable.ic_quarter_note_sharp_line);

        svals.add(300);

        yvals.add(middle - 365);  //FIX THIS MESSY, GROSS CODE!!!
        yvals.add(middle - 415);
        yvals.add(middle - 435);
        yvals.add(middle + 155);
        yvals.add(middle + 62);
        yvals.add(middle + 15);
        yvals.add(middle - 30);
        yvals.add(middle - 45);
        yvals.add(middle - 125);
        yvals.add(middle - 170);
        yvals.add(middle - 225);
        yvals.add(middle - 320);
    }

    public static void captureNotes(){
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

    public static int NoteEvaluator(float freq) {
        int pianoNoteNumber;
        double logCalcX = Math.log(freq / 440);
        double logCalcY = Math.log(2);

        pianoNoteNumber = (int) (12 * (logCalcX + 49) / logCalcY);
        return pianoNoteNumber;
    }

    public static Bitmap noteImageBuilder(int tonalValue, Key theKey, int rhythmicValue){

        ArrayList<Integer> noteType;
        int noteNumber = tonalValue%12;

        switch (rhythmicValue) {
            case 0:
                noteType = wholes;
            case 1:
                noteType = halves;
            case 2:
                noteType = quarters;
            case 3:
                noteType = eighths;
            case 4:
                noteType = sixteenths;
            default:
                noteType = quarters;
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



        Bitmap theBitmap = BitmapFactory.decodeResource(MainActivity.getAppContext().getResources(), noteType.get(noteGet));
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

    public static void update(float freq) {

        Note aNote;
        int rhythmicValue = 4;

        int notesTone = NoteEvaluator(freq);

        if (firstNote)
            theKey = new Key(notesTone);
            firstNote = false;

        int rhythmicValue = updateRhythm(notesTone);

        //If a note has recently ended, rhythmic value will be nonzero.
        //In other words, only construct the recently ended note if update has been called with a new tonal value.
        if (rhythmicValue != 0) {

            Bitmap notesImage = noteImageBuilder(notesTone, theKey, rhythmicValue);

            aNote = new Note(notesTone, notesImage, rhythmicValue);

            //    Bitmap b = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_rest);
            //    newNote.image = Bitmap.createScaledBitmap(b,300,300, false);
            //    newNote.y = drawView.drawCanvas.getHeight()/2 - 150;

            //} finally {

            //    staff.notes.add(newNote);

            //    drawView.startNew();

            //   drawView.draw(drawView.drawCanvas);

            //}
        }
    }


}
