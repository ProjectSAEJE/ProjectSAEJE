package music.model;


import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

public class Images {

    public static ArrayList<Integer> noteImages = new ArrayList<>();

    /*public Images() {
        noteImages = new ArrayList<>();
    }*/

    public static void populateArrays() {
        populateNoteArraysArrays();
    }

    private static void populateNoteArraysArrays() {                   //Rhythmic Value:
        noteImages.add(R.drawable.sixteenth_note_single);              //1
        noteImages.add(R.drawable.eighth_note_single);                 //2
        noteImages.add(R.drawable.eighth_note_dotted_single_line);     //3
        noteImages.add(R.drawable.quarter_note);                       //4
        noteImages.add(R.drawable.quarter_tied_sixteenth);             //5
        noteImages.add(R.drawable.quarter_note_dotted_single_line);    //6
        noteImages.add(R.drawable.quarter_note_dotted_double_line);    //7
        noteImages.add(R.drawable.half_note);                          //8
        noteImages.add(R.drawable.half_tied_sixteenth);                //9
        noteImages.add(R.drawable.half_tied_eighth);                   //10
        noteImages.add(R.drawable.half_tied_dotted_eighth);            //11
        noteImages.add(R.drawable.half_note_dotted_single_line);       //12
        noteImages.add(R.drawable.dotted_half_tied_sixteenth);         //13
        noteImages.add(R.drawable.half_note_dotted_double_line);       //14
        noteImages.add(R.drawable.double_dotted_half_tied_sixteenth);  //15
        noteImages.add(R.drawable.whole_note);                         //16
    }
}
