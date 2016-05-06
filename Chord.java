package music.model;
import android.util.Log;
import java.util.*;
import music.model.Notation.MusicalSymbols.*;
import music.model.Notation.*;
/**
 * Created by jorgja02 on 2/11/16.
 *
 * Due to some pretty severe (and unfortunately-timed) technical difficulties, 
 * the chord class could not be fully implemented into the application at this 
 * time. Rather than letting this code go unseen, those curious about how the 
 * chord builder and analyzer should operate are entitled to check this process 
 * out for themselves.
 *
 */
public class Chord {
    public static ArrayList<String>Mtriad = new ArrayList<String>();
    public static ArrayList<String>mtriad = new ArrayList<String>();
    public static ArrayList<String>MM7 = new ArrayList<String>();
    public static ArrayList<String>Mm7 = new ArrayList<String>();
    public static ArrayList<String>mM7 = new ArrayList<String>();
    public static ArrayList<String>mm7 = new ArrayList<String>();
    public static ArrayList<String>Mtriad6 = new ArrayList<String>();
    public static ArrayList<String>mtriad6 = new ArrayList<String>();
    public static ArrayList<String>MM6 = new ArrayList<String>();
    public static ArrayList<String>Mm6 = new ArrayList<String>();
    public static ArrayList<String>mM6 = new ArrayList<String>();
    public static ArrayList<String>mm6 = new ArrayList<String>();
    public static ArrayList<String>Mtriad4 = new ArrayList<String>();
    public static ArrayList<String>mtriad4 = new ArrayList<String>();
    public static ArrayList<String>MM4 = new ArrayList<String>();
    public static ArrayList<String>Mm4 = new ArrayList<String>();
    public static ArrayList<String>mM4 = new ArrayList<String>();
    public static ArrayList<String>mm4 = new ArrayList<String>();

    public static ArrayList<Integer>SampleInput = new ArrayList<Integer>();
    public static ArrayList<String>NoteList = new ArrayList<String>();
    public static ArrayList<String>MostLikelyChords = new ArrayList<>();

    public void buildNoteDict(){
        /*
        This quickly builds a list of pitches to be inserted into
        the various chord lists throughout the program.
        */
        NoteList.add("A");NoteList.add("Bb");NoteList.add("B");
        NoteList.add("C");NoteList.add("C#");NoteList.add("D");
        NoteList.add("Eb");NoteList.add("E");NoteList.add("F");
        NoteList.add("F#");NoteList.add("G");NoteList.add("Ab");
    }

    public static void getSample(Staff aStaff){
        for (Notation elements: aStaff.getElements()){
            Measure aMeasure = (Measure) elements;
            for (Notation melements: aMeasure.getElements()){
                Note aNote = (Note) melements;
                if (aNote.getTonalValue() >= 0){
                SampleInput.add(aNote.getTonalValue());}
            }
            BuildMaster();
        }
    }
    public static void BuildMaster() {
        for (int n=0;n<(SampleInput.size());n++){
            int m = n+1;
            System.out.println("Possible chords for note " + m + " are:"); //omit
           /*
           Some basic notes in a chord below this comment:
          -root is the root of a chord
          -majthird is the third of a major chord
          -minthird is the third of a minor chord
          -fifth is the fifth of a major/minor chord
          -majseventh is the major seventh of a major/minor chord
          -minseventh is the minor seventh of a major/minor chord
           */
            int root = SampleInput.get(n);
            int majthird = root + 4;
            int minthird = root + 3;
            int fifth = root + 7;
            int majseventh = root + 11;
            int minseventh = root + 10;
           /*
           In music theory, 6/5 signifies a 'first inversion chord,'
           or one with the desired note as the third of a chord.
           (I'll just add a 6 to the end of stuff for simplicity-sake)
           Thus, I added a '_M6' to the end of major chords in first
           inversion, and '_m6' to the end of minor chords in first
           inversion.
           */
            int root_M6 = root - 4;
            int majthird_M6 = root;
            int fifth_M6 = root + 3;
            int majseventh_M6 = root + 7;
            int minseventh_M6 = root + 6;
            int root_m6 = root - 3;
            int minthird_m6 = root;
            int fifth_m6 = root + 4;
            int majseventh_m6 = root + 8;
            int minseventh_m6 = root + 7;
           /*
           In music theory, 4/3 signifies a 'second inversion chord,'
           or one with the desired note on the bottom of a chord as
           the fifth of a chord. (I'll just add a 4 to the end of
           stuff for simplicity-sake)
           Thus, I added a '_M4' to the end of major chords in second
           inversion, and '_m4' to the end of minor chords in first
           inversion.
           */
            int root_M4 = root - 7;
            int majthird_M4 = root - 3;
            int fifth_M4 = root;
            int majseventh_M4 = root + 4;
            int minseventh_M4 = root + 3;
            int root_m4 = root_M4;
            int minthird_m4 = root - 4;
            int fifth_m4 = root;
            int majseventh_m4 = majseventh_M4;
            int minseventh_m4 = minseventh_M4;

            /*
            The values above will be fed to a series of 'if' statements
            to ensure that everything in a hypothetical chord is within
            the range of audible notes on a piano. The values below mods
            everything by 11, or the number of chromatic pitches in 1
            octave of notes. Those values will later be used to assign
            pitch names to each numeric value (i.e. A is 0, Bb is 1,
            and so on, so forth)
            */
            int root2 = root%12;
            int majthird2 = majthird%12;
            int minthird2 = minthird%12;
            int fifth2 = fifth%12;
            int majseventh2 = majseventh%12;
            int minseventh2 = minseventh%12;
            int root_M62 = root_M6%12;
            int majthird_M62 = majthird_M6%12;
            int fifth_M62 = fifth_M6%12;
            int majseventh_M62 = majseventh_M6%12;
            int minseventh_M62 = minseventh_M6%12;
            int root_m62 = root_m6%12;
            int minthird_m62 = minthird_m6%12;
            int fifth_m62 = fifth_m6%12;
            int majseventh_m62 = majseventh_m6%12;
            int minseventh_m62 = minseventh_m6%12;
            int root_M42 = root_M4%12;
            int majthird_M42 = majthird_M4%12;
            int fifth_M42 = fifth_M4%12;
            int majseventh_M42 = majseventh_M4%12;
            int minseventh_M42 = minseventh_M4%12;
            int root_m42 = root_m4%12;
            int minthird_m42 = minthird_m4%12;
            int fifth_m42 = fifth_m4%12;
            int majseventh_m42 = majseventh_m4%12;
            int minseventh_m42 = minseventh_m4%12;
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
           *//*
          *********************************************
          *********************************************
          Below: given note as the root of the chord
          Will use MM7/Mm7/mM7/mm7 for root-position
          7 chords. Will also use "7" when putting
          them in the dictionary.
          *********************************************
          *********************************************
          */
            if (root>=0 && majthird>=0 && fifth>=0 && root<=87 && majthird<=87 && fifth<=87){
                Mtriad.add(NoteList.get(root2)); Mtriad.add(NoteList.get(majthird2)); Mtriad.add(NoteList.get(fifth2));}
            if (root>=0 && minthird>=0 && fifth>=0 && root<=87 && minthird<=87 && fifth<=87){
                mtriad.add(NoteList.get(root2)); mtriad.add(NoteList.get(minthird2)); mtriad.add(NoteList.get(fifth2));}
            if (root>=0 && majthird>=0 && fifth>=0 && majseventh>=0 && root<=87 && majthird<=87 && fifth<=87 && majseventh<=87){
                MM7.add(NoteList.get(root2)); MM7.add(NoteList.get(majthird2)); MM7.add(NoteList.get(fifth2)); MM7.add(NoteList.get(majseventh2));}
            if (root>=0 && majthird>=0 && fifth>=0 && minseventh>=0 && root<=87 && majthird<=87 && fifth<=87 && minseventh<=87){
                Mm7.add(NoteList.get(root2)); Mm7.add(NoteList.get(majthird2)); Mm7.add(NoteList.get(fifth2)); Mm7.add(NoteList.get(minseventh2));}
            if (root>=0 && minthird>=0 && fifth>=0 && majseventh>=0 && root<=87 && minthird<=87 && fifth<=87 && majseventh<=87){
                mM7.add(NoteList.get(root2)); mM7.add(NoteList.get(minthird2)); mM7.add(NoteList.get(fifth2)); mM7.add(NoteList.get(majseventh2));}
            if (root>=0 && minthird>=0 && fifth>=0 && minseventh>=0 && root<=87 && minthird<=87 && fifth<=87 && minseventh<=87){
                mm7.add(NoteList.get(root2)); mm7.add(NoteList.get(minthird2)); mm7.add(NoteList.get(fifth2)); mm7.add(NoteList.get(minseventh2));}
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
            if (root_M6>=0 && majthird_M6>=0 && fifth_M6>=0 && root_M6<=87 && majthird_M6<=87 && fifth_M6<=87){
                Mtriad6.add(NoteList.get(majthird_M62)); Mtriad6.add(NoteList.get(fifth_M62)); Mtriad6.add(NoteList.get(root_M62));}
            if (root_m6>=0 && minthird_m6>=0 && fifth_m6>=0 && root_m6<=87 && minthird_m6<=87 && fifth_m6<=87){
                mtriad6.add(NoteList.get(minthird_m62)); mtriad6.add(NoteList.get(fifth_m62)); mtriad6.add(NoteList.get(root_m62));}
            if (root_M6>=0 && majthird_M6>=0 && fifth_M6>=0 && majseventh_M6>=0 && root_M6<=87 && majthird_M6<=87 && fifth_M6<=87 && majseventh_M6<=87){
                MM6.add(NoteList.get(majthird_M62)); MM6.add(NoteList.get(fifth_M62)); MM6.add(NoteList.get(majseventh_M62)); MM6.add(NoteList.get(root_M62));}
            if (root_M6>=0 && majthird_M6>=0 && fifth_M6>=0 && minseventh_m6>=0 && root_M6<=87 && majthird_M6<=87 && fifth_M6<=87 && minseventh_m6<=87){
                Mm6.add(NoteList.get(minthird_m62)); Mm6.add(NoteList.get(fifth_M62)); Mm6.add(NoteList.get(minseventh_M62)); Mm6.add(NoteList.get(root_M62));}
            if (root_m6>=0 && minthird_m6>=0 && fifth_m6>=0 && majseventh_M6>=0 && root_m6<=87 && minthird_m6<=87 && fifth_m6<=87 && majseventh_M6<=87){
                mM6.add(NoteList.get(minthird_m62)); mM6.add(NoteList.get(fifth_m62)); mM6.add(NoteList.get(majseventh_m62)); mM6.add(NoteList.get(root_m62));}
            if (root_m6>=0 && minthird_m6>=0 && fifth_m6>=0 && minseventh_m6>=0 && root_m6<=87 && minthird_m6<=87 && fifth_m6<=87 && minseventh_m6<=87){
                mm6.add(NoteList.get(minthird_m62)); mm6.add(NoteList.get(fifth_m62)); mm6.add(NoteList.get(minseventh_m62)); mm6.add(NoteList.get(root_m62));}

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
            if (root_M4>=0 && majthird_M4>=0 && fifth_M4>=0 && root_M4<=87 && majthird_M4<=87 && fifth_M4<=87){
                Mtriad4.add(NoteList.get(fifth_M42)); Mtriad4.add(NoteList.get(root_M42)); Mtriad4.add(NoteList.get(majthird_M42));}
            if (root_m4>=0 && minthird_m4>=0 && fifth_m4>=0 && root_m4<=87 && minthird_m4<=87 && fifth_m4<=87){
                mtriad4.add(NoteList.get(fifth_m42)); mtriad4.add(NoteList.get(root_m42)); mtriad4.add(NoteList.get(minthird_m42));}
            if (root_M4>=0 && majthird_M4>=0 && fifth_M4>=0 && majseventh_M4>=0 && root_M4<=87 && majthird_M4<=87 && fifth_M4<=87 && majseventh_M4<=87){
                MM4.add(NoteList.get(fifth_M42)); MM4.add(NoteList.get(majseventh_M42)); MM4.add(NoteList.get(root_M42)); MM4.add(NoteList.get(majthird_M42));}
            if (root_M4>=0 && majthird_M4>=0 && fifth_M4>=0 && minseventh_m4>=0 && root_M4<=87 && majthird_M4<=87 && fifth_M4<=87 && minseventh_m4<=87){
                Mm4.add(NoteList.get(fifth_M42)); Mm4.add(NoteList.get(minseventh_m42)); Mm4.add(NoteList.get(root_M42)); Mm4.add(NoteList.get(majthird_M42));}
            if (root_m4>=0 && minthird_m4>=0 && fifth_m4>=0 && majseventh_M4>=0 && root_m4<=87 && minthird_m4<=87 && fifth_m4<=87 && majseventh_M4<=87){
                mM4.add(NoteList.get(fifth_m42)); mM4.add(NoteList.get(majseventh_m42)); mM4.add(NoteList.get(root_m42)); mM4.add(NoteList.get(minthird_m42));}
            if (root_m4>=0 && minthird_m4>=0 && fifth_m4>=0 && minseventh_m4>=0 && root_m4<=87 && minthird_m4<=87 && fifth_m4<=87 && minseventh_m4<=87){
                mm4.add(NoteList.get(fifth_m42)); mm4.add(NoteList.get(minseventh_m42)); mm4.add(NoteList.get(root_m42)); mm4.add(NoteList.get(minthird_m42));}

            /*
           *********************************************
           *********************************************
           More chords can be added using a similar
           process as with the existing chord types.
           *********************************************
           *********************************************
           */
            Analyze();
            Reset();
        }
    }
    public static void Reset(){
        Mtriad.clear();mtriad.clear();MM7.clear();Mm7.clear();mM7.clear();mm7.clear();
        Mtriad6.clear();mtriad6.clear();MM6.clear();Mm6.clear();mM6.clear();mm6.clear();
        Mtriad4.clear();mtriad4.clear();MM4.clear();Mm4.clear();mM4.clear();mm4.clear();
        SampleInput.clear();
    }
    public static void Analyze(){
        /*
        So a present_note variable is made to test whether each note in SampleOutput is
        a part of each chord/triad list, and if so, their respective counters get a point
        added to them. In the end, each counter with points inside them will reveal which
        chords are most to least likely to be, given the inputted notes.
        */
        String present_note;
        int Mtriad_counter = 0;
        int mtriad_counter = 0;
        int MM7_counter = 0;
        int Mm7_counter = 0;
        int mM7_counter = 0;
        int mm7_counter = 0;
        int Mtriad6_counter = 0;
        int mtriad6_counter = 0;
        int MM6_counter = 0;
        int Mm6_counter = 0;
        int mM6_counter = 0;
        int mm6_counter = 0;
        int Mtriad4_counter = 0;
        int mtriad4_counter = 0;
        int MM4_counter = 0;
        int Mm4_counter = 0;
        int mM4_counter = 0;
        int mm4_counter = 0;
        for (int n=0;n<(SampleInput.size());n++){
            present_note = NoteList.get((SampleInput.get(n))%12);
            
            if (Mtriad.contains(present_note)){Mtriad_counter++;}
            if (mtriad.contains(present_note)){mtriad_counter++;}
            if (MM7.contains(present_note)){MM7_counter++;}
            if (Mm7.contains(present_note)){Mm7_counter++;}
            if (mM7.contains(present_note)){mM7_counter++;}
            if (mm7.contains(present_note)){mm7_counter++;}
            if (Mtriad6.contains(present_note)){Mtriad6_counter++;}
            if (mtriad6.contains(present_note)){mtriad6_counter++;}
            if (MM6.contains(present_note)){MM6_counter++;}
            if (Mm6.contains(present_note)){Mm6_counter++;}
            if (mM6.contains(present_note)){mM6_counter++;}
            if (mm6.contains(present_note)){mm6_counter++;}
            if (Mtriad4.contains(present_note)){Mtriad4_counter++;}
            if (mtriad4.contains(present_note)){mtriad4_counter++;}
            if (MM4.contains(present_note)){MM4_counter++;}
            if (Mm4.contains(present_note)){Mm4_counter++;}
            if (mM4.contains(present_note)){mM4_counter++;}
            if (mm4.contains(present_note)){mm4_counter++;}
        }
        /*Likelihood of notes falling into each category.
        This begins with the Root Position chords and triads, then it progresses
        from first to second inversion chords and triads.*/
        if (MM7_counter >= 4) {MostLikelyChords.add(MM7.get(0) + " M7");}
        if (Mm7_counter >= 4) {MostLikelyChords.add(Mm7.get(0) + " 7");}
        if (mM7_counter >= 4) {MostLikelyChords.add(mM7.get(0) + "- M7");}
        if (mm7_counter >= 4) {MostLikelyChords.add(mm7.get(0) + "- 7");}
        if (MM6_counter >= 4) {MostLikelyChords.add(MM6.get(0) + " M6/5");}
        if (Mm6_counter >= 4) {MostLikelyChords.add(Mm6.get(0) + " 6/5");}
        if (mM6_counter >= 4) {MostLikelyChords.add(mM6.get(0) + "- M6/5");}
        if (mm6_counter >= 4) {MostLikelyChords.add(mm6.get(0) + "- 6/5");}
        if (MM4_counter >= 4) {MostLikelyChords.add(MM4.get(0) + " M4/2");}
        if (Mm4_counter >= 4) {MostLikelyChords.add(Mm4.get(0) + " 4/2");}
        if (mM4_counter >= 4) {MostLikelyChords.add(mM4.get(0) + "- M4/2");}
        if (mm4_counter >= 4) {MostLikelyChords.add(mm4.get(0) + "- 4/2");}
        if (Mtriad_counter == 3) {MostLikelyChords.add(Mtriad.get(0));}
        if (mtriad_counter == 3) {MostLikelyChords.add(mtriad.get(0));}
        for (int c=0;c<(MostLikelyChords.size());c++){
            //post to screen
            Log.d("Possible Chord: ", MostLikelyChords.get(c));
            //'toast' would have posted some chord ideas to the screen
        }
    }
}
