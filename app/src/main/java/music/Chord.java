package music;

import java.util.*;
import music.Note;

/**
 * Created by jorgja02 on 2/11/16.
 */

public class Chord {

    public Note note;
    public ArrayList<ArrayList<Integer>> Chord_Dict = new ArrayList<ArrayList<Integer>>();

    //public Chord(Note root) { this.root = root;}
    public Chord(Note note) {
        this.tonalValue() = note;
    }

    /*
    Some basic notes in a chord below this comment:
	-root is the root of a chord
	-majthird is the third of a major chord
	-minthird is the third of a minor chord
	-fifth is the fifth of a major/minor chord
	-majseventh is the major seventh of a major/minor chord
	-minseventh is the minor seventh of a major/minor chord
    */

    public int root = note.tonalValue();
    public int majthird = note.tonalValue() + 4;
    public int minthird = note.tonalValue() + 3;
    public int fifth = note.tonalValue() + 7;
    public int majseventh = note.tonalValue() + 11;
    public int minseventh = note.tonalValue() + 10;

    /*
    In music theory, 6/5 signifies a 'first inversion chord,'
    or one with the desired note as the third of a chord.
    (I'll just add a 6 to the end of stuff for simplicity-sake)

    Thus, I added a '_M6' to the end of major chords in first
    inversion, and '_m6' to the end of minor chords in first
    inversion.
    */

    int root_M6 = note.tonalValue() - 4;
    int majthird_M6 = note.tonalValue();
    int fifth_M6 = note.tonalValue() + 3;
    int majseventh_M6 = note.tonalValue() + 7;
    int minseventh_M6 = note.tonalValue() + 6;

    int root_m6 = note.tonalValue() - 3;
    int minthird_m6 = note.tonalValue();
    int fifth_m6 = note.tonalValue() + 4;
    int majseventh_m6 = note.tonalValue() + 8;
    int minseventh_m6 = note.tonalValue() + 7;

    /*
    In music theory, 4/3 signifies a 'second inversion chord,'
    or one with the desired note on the bottom of a chord as
    the fifth of a chord. (I'll just add a 4 to the end of
    stuff for simplicity-sake)

    Thus, I added a '_M4' to the end of major chords in second
    inversion, and '_m4' to the end of minor chords in first
    inversion.
    */

    int root_M4 = note.tonalValue() - 7;
    int majthird_M4 = note.tonalValue() - 3;
    int fifth_M4 = note.tonalValue();
    int majseventh_M4 = note.tonalValue() + 4;
    int minseventh_M4 = note.tonalValue() + 3;

    int root_m4 = root_M4;
    int minthird_m4 = note.tonalValue() - 4;
    int fifth_m4 = note.tonalValue();
    int majseventh_m4 = majseventh_M4;
    int minseventh_m4 = minseventh_M4;

    /*
    ************************************************************
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    ************************************************************
    And now, to build a dictionary with all the chords inside
    using chord_dict. The code below builds individual lists
    to contain each element of a chord, then the list object
    will be inserted into the dictionary with differing keys.
    ************************************************************
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    ************************************************************
    */

    /*
    *********************************************
    *********************************************
    Below: given note as the root of the chord

    Will use MM7/Mm7/mM7/mm7 for root-position
    7 chords. Will also use "7" when putting
    them in the dictionary.
    *********************************************
    *********************************************
    */

    public void setMajorTriad() {
        ArrayList<Integer>Mtriad = new ArrayList<Integer>();
        Mtriad.add(root); Mtriad.add(majthird); Mtriad.add(fifth);
        Chord_Dict.add(Mtriad);
        //return Chord_Dict or Mtriad?
    }

    public void setMinorTriad() {
        ArrayList<Integer>mtriad = new ArrayList<Integer>();
        mtriad.add(root); mtriad.add(minthird); mtriad.add(fifth);
        Chord_Dict.add(mtriad);
        //
    }

    public void setMajorMajor7Chord() {
        ArrayList<Integer>MM7 = new ArrayList<Integer>();
        MM7.add(root); MM7.add(majthird); MM7.add(fifth); MM7.add(majseventh);
        Chord_Dict.add(MM7);
        //
    }

    public void setMajorMinor7Chord() {
        ArrayList<Integer>Mm7 = new ArrayList<Integer>();
        Mm7.add(root); Mm7.add(majthird); Mm7.add(fifth); Mm7.add(minseventh);
        Chord_Dict.add(Mm7);
        //
    }

    public void setMinorMajor7Chord() {
        ArrayList<Integer>mM7 = new ArrayList<Integer>();
        mM7.add(root); mM7.add(minthird); mM7.add(fifth); mM7.add(majseventh);
        Chord_Dict.add(mM7);
        //
    }

    public void setMinorMinor7Chord() {
        ArrayList<Integer>mm7 = new ArrayList<Integer>();
        mm7.add(root); mm7.add(minthird); mm7.add(fifth); mm7.add(minseventh);
        Chord_Dict.add(mm7);
        //
    }

    /*
    *********************************************
    *********************************************
    Below: given note as the third of the chord

    Will use MM6/Mm6/mM6/mm6 for first inversion
    7 chords. Will also use "6/5" when putting
    them in the dictionary.
    *********************************************
    *********************************************
    */

    public void setMajorTriad6() {
        ArrayList<Integer>Mtriad6 = new ArrayList<Integer>();
        Mtriad6.add(root_M6); Mtriad6.add(majthird_M6); Mtriad6.add(fifth_M6);
        Chord_Dict.add(Mtriad6);
        //
    }

    public void setMinorTriad6() {
        ArrayList<Integer>mtriad6 = new ArrayList<Integer>();
        mtriad6.add(root_m6); mtriad6.add(minthird_m6); mtriad6.add(fifth_m6);
        Chord_Dict.add(mtriad6);
        //
    }

    public void setMajorMajor6Chord() {
        ArrayList<Integer>MM6 = new ArrayList<Integer>();
        MM6.add(root_M6); MM6.add(majthird_M6); MM6.add(fifth_M6); MM6.add(majseventh_M6);
        Chord_Dict.add(MM6);
        //
    }

    public void setMajorMinor6Chord() {
        ArrayList<Integer>Mm6 = new ArrayList<Integer>();
        Mm6.add(root_M6); Mm6.add(majthird_M6); Mm6.add(fifth_M6); Mm6.add(minseventh_m6);
        Chord_Dict.add(Mm6);
        //
    }

    public void setMinorMajor6Chord() {
        ArrayList<Integer>mM6 = new ArrayList<Integer>();
        mM6.add(root_m6); mM6.add(minthird_m6); mM6.add(fifth_m6); mM6.add(majseventh_m6);
        Chord_Dict.add(mM6);
        //
    }

    public void setMinorMinor6Chord() {
        ArrayList<Integer>mm6 = new ArrayList<Integer>();
        mm6.add(root_m6); mm6.add(minthird_m6); mm6.add(fifth_m6); mm6.add(minseventh_m6);
        Chord_Dict.add(mm6);
        //
    }

    /*
    *********************************************
    *********************************************
    Below: given note as the fifth of the chord

    Will use MM4/Mm4/mM4/mm4 for second inversion
    7 chords. Will also use "4/3" when putting
    them in the dictionary.
    *********************************************
    *********************************************
    */

    public void setMajorTriad4() {
        ArrayList<Integer>Mtriad4 = new ArrayList<Integer>();
        Mtriad4.add(root_M4); Mtriad4.add(majthird_M4); Mtriad4.add(fifth_M4);
        Chord_Dict.add(Mtriad4);
        //
    }

    public void setMinorTriad4() {
        ArrayList<Integer>mtriad4 = new ArrayList<Integer>();
        mtriad4.add(root_m4); mtriad4.add(minthird_m4); mtriad4.add(fifth_m4);
        Chord_Dict.add(mtriad4);
        //
    }

    public void setMajorMajor4Chord() {
        ArrayList<Integer>MM4 = new ArrayList<Integer>();
        MM4.add(root_M4); MM4.add(majthird_M4); MM4.add(fifth_M4); MM4.add(majseventh_M4);
        Chord_Dict.add(MM4);
        //
    }

    public void setMajorMinor4Chord() {
        ArrayList<Integer>Mm4 = new ArrayList<Integer>();
        Mm4.add(root_M4); Mm4.add(majthird_M4); Mm4.add(fifth_M4); Mm4.add(minseventh_m4);
        Chord_Dict.add(Mm4);
        //
    }

    public void setMinorMajor4Chord() {
        ArrayList<Integer>mM4 = new ArrayList<Integer>();
        mM4.add(root_m4); mM4.add(minthird_m4); mM4.add(fifth_m4); mM4.add(majseventh_m4);
        Chord_Dict.add(mM4);
        //
    }

    public void setMinorMinor4Chord() {
        ArrayList<Integer>mm4 = new ArrayList<Integer>();
        mm4.add(root_m4); mm4.add(minthird_m4); mm4.add(fifth_m4); mm4.add(minseventh_m4);
        Chord_Dict.add(mm4);
        //
    }

    /*
    *********************************************
    *********************************************
    To Be Continued: root as the fifth of a chord

    Will use MM2/Mm2/mM2/mm2 for third inversion
    7 chords. Will also use "2" when putting
    them in the dictionary.

    (Would be a repeat of the other chord types,
    just alter some variables around)
    *********************************************
    *********************************************
    */

}
