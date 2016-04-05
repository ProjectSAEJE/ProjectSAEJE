package music.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Measure {
    public int measureNumber; //starts as 1 not 0 per music composition norms
    public ArrayList<Note> notes;
    private int numBeats;     //total number of beats per measure   3 \_  3/8 time
    private int beats;        //note value that gets the beat       8 /

    public Measure() {}

    public Measure(int measureNumber, ArrayList<Note> notes, int numBeats, int beats) {
        this.measureNumber = measureNumber;
        this.notes = notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

}
