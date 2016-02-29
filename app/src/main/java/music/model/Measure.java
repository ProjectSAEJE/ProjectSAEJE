package music.model;

import java.util.ArrayList;

public class Measure {
    public int measureNumber; //starts as 1 not 0 per music composition norms
    public ArrayList<Note> notes; //an arrayList of Notes

    public Measure() {}

    public Measure(int measureNumber, ArrayList<Note> notes) {
        this.measureNumber = measureNumber;
        this.notes = notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

}