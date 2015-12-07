package graphics;

import java.util.ArrayList;

/**
 * Created by austinnash on 12/6/15.
 */

public class Measure {
    private int number;
    private ArrayList<Note> notes;

    //Other possible fields:
    //private Activity activity?
    //private View view?
    //private Vector position?
    //private Bitmap staffPNG?

    public Measure() {}

    public Measure(int number, ArrayList<Note> notes) {
        this.number = number;
        this.notes = notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }
}
