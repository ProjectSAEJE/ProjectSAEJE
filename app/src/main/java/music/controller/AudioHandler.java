package music.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

import music.model.Note;

/**
 * Created by woodev01 on 2/28/16.
 */
public class AudioHandler {

    public static ArrayList<Integer> bmvals = new ArrayList<Integer>();
    public static ArrayList<Integer> svals = new ArrayList<Integer>();
    public static ArrayList<Integer> yvals = new ArrayList<Integer>();

    public Boolean arraysBuilt = false;

    public AudioHandler (){

    }

    public void populateArrays(){
        int middle = drawView.drawCanvas.getHeight()/2;


        bmvals.add(R.drawable.ic_quarter_note_sharp_space);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note_sharp_line);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note_sharp_space);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note_sharp_line);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note_sharp_space);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note);

        svals.add(400);
        svals.add(300);
        svals.add(350);
        svals.add(300);
        svals.add(300);
        svals.add(400);
        svals.add(300);
        svals.add(350);
        svals.add(300);
        svals.add(400);
        svals.add(300);
        svals.add(300);

        yvals.add(middle-365);
        yvals.add(middle-415);
        yvals.add(middle-435);
        yvals.add(middle+155);
        yvals.add(middle+62);
        yvals.add(middle+15);
        yvals.add(middle-30);
        yvals.add(middle-45);
        yvals.add(middle-125);
        yvals.add(middle-170);
        yvals.add(middle-225);
        yvals.add(middle-320);

        arraysBuilt = true;

    }

    public int NoteEvaluator(float freq) {
        double logCalcX = Math.log(freq / 440);
        double logCalcY = Math.log(2);

        int pianoNoteNumber = (int) (12 * (logCalcX + 49) / logCalcY);
        return pianoNoteNumber;
    }

    public void update(float freq) {
        if (demoLoopCounter == 12) {
            xVal = 0;
            demoLoopCounter = 0;
            staff.measures.clear();
            drawView.startNew();
        }

        demoLoopCounter += 1;

        xVal += 130;
        Note newNote = new Note(0, xVal, "quarter", this);
        int screenNoteNumber = NoteEvaluator(freq)%12;



        try{

            int sbn = bmvals.get(screenNoteNumber); // Screen Bitmap Number
            int sns = svals.get(screenNoteNumber); // Screen Note Size
            int sny = yvals.get(screenNoteNumber); // Screen Note Y-value

            Bitmap b = BitmapFactory.decodeResource(this.getResources(), sbn);
            newNote.image = Bitmap.createScaledBitmap(b,sns,sns, false);
            newNote.y = sny;

        } catch(Exception e) {

            Bitmap b = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_rest);
            newNote.image = Bitmap.createScaledBitmap(b,300,300, false);
            newNote.y = drawView.drawCanvas.getHeight()/2 - 150;

        } finally {

            staff.notes.add(newNote);

            drawView.startNew();
            drawView.draw(drawView.drawCanvas);

        }
    }


}
