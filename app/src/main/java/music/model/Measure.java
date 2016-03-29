package music.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Measure {
    public int measureNumber; //starts as 1 not 0 per music composition norms
    public ArrayList<Note> notes;
    private float xPos;
    private float yPos;
    private Bitmap staffImage; //Rework the staff object so that the staff image is actually a bitmap object, not simply the background specified in xml?

    //Other possible fields:
    //private Activity activity?
    //private View view?
    //private Bitmap staffPNG?

    public Measure() {}

    public Measure(int measureNumber, ArrayList<Note> notes) {
        this.measureNumber = measureNumber;
        this.notes = notes;
        xPos = staffImage.getWidth() * (measureNumber - 1);
        yPos = 0;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

}
