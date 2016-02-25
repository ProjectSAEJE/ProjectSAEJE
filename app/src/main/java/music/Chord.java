package music;

import java.util.ArrayList;
import music.Note;
import java.util.ArrayList;
import music.Note;

/**
 * Created by jorgja02 on 2/11/16.
 */

public class Chord {

    public Note root;
    public ArrayList<Note> intervalNotes;

    public Chord(Note root) {
        this.root = root;
    }

    public void setToMajorChord(){
        int majThirdNum = root.getVal() + 4;
        int fifthNum = root.getVal() + 7;
        intervalNotes.set(0, new Note(majThirdNum));
        intervalNotes.set(1, new Note(fifthNum));

    }

    public void setToMinorChord(){
        int minThirdNum = root.getVal() + 3;
        int fifthNum = root.getVal() + 7;
        intervalNotes.set(0, new Note(minThirdNum));
        intervalNotes.set(1, new Note(fifthNum));

    }

    public void setToMajor7Chord(){
        int majThirdNum = root.getVal() + 4;
        int fifthNum = root.getVal() + 7;
        int seventhNum = root.getVal() + 10;
        intervalNotes.set(0, new Note(majThirdNum));
        intervalNotes.set(1, new Note(fifthNum));
        intervalNotes.set(2, new Note(seventhNum));

    }

    public void setToMinor7Chord(){
        int minThirdNum = root.getVal() + 3;
        int fifthNum = root.getVal() + 7;
        int seventhNum = root.getVal() + 10;
        intervalNotes.set(0, new Note(minThirdNum));
        intervalNotes.set(1, new Note(fifthNum));
        intervalNotes.set(2, new Note(seventhNum));

    }

    public setToMinorChord(){
        int thirdNum = root.getVal() + 3;
        int fifthNum = root.getVal() + 7;
        intervalNotes.set(0, new Note(thirdNum));
        intervalNotes.set(1, new Note(fifthNum));
    }

}
*/