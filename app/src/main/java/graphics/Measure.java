package graphics;

import android.graphics.Bitmap;

import java.util.ArrayList;

import music.Note;

public class Measure {
    private int number; //starts as 1 not 0 per music composition norms
    private ArrayList<Note> notes;
    private float xPos;
    private float yPos;
    private Bitmap staffImage; //THIS IS CURRENTLY A DUMMY VARIABLE. Rework the staff object so that the staff image is actually a bitmap object, not simply the background specified in xml?

    //Other possible fields:
    //private Activity activity?
    //private View view?
    //private Bitmap staffPNG?

    public Measure() {}

    public Measure(int number, ArrayList<Note> notes) {
        this.number = number;
        this.notes = notes;
        xPos = staffImage.getWidth() * (number - 1);
        yPos = 0;
    }

    public void addNote(Note note) {
        notes.add(note);
    }
}
