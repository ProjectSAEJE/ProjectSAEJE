package music.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

import audio.CaptureThread;
import music.model.Key;
import music.model.Note;
import projectsaeje.MainActivity;

/**
 * Created by woodev01 on 2/28/16.
 */

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

    public static Bitmap noteImageBuilder(int tonalValue,Key theKey, int rhythmicValue){

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

    public static void update(float freq) {

        Note aNote;

        int notesTone = NoteEvaluator(freq);

        if (firstNote)
            theKey = new Key(notesTone);
            firstNote = false;


        Bitmap notesImage = noteImageBuilder(notesTone, theKey, rhythmicValue);

        aNote = new Note(notesTone, notesImage);

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
