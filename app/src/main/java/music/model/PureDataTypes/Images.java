package music.model.PureDataTypes;


import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

public class Images {

    public static ArrayList<Integer> noteImages = new ArrayList<>();
    public static ArrayList<Integer> flatImages = new ArrayList<>();
    public static ArrayList<Integer> sharpImages = new ArrayList<>();

    /*public Images() {
        noteImages = new ArrayList<>();
    }*/

    public void populateArrays() {
        populateNoteArraysArrays();
        populateFlatsArray();
        populateSharpsArray();
    }

    private static void populateNoteArraysArrays() {                         //Rhythmic Value:
        noteImages.add(R.drawable.sixteenth_note_single);                    //1
        noteImages.add(R.drawable.eighth_note_single);                       //2
        noteImages.add(R.drawable.eighth_note_dotted_single_line);           //3
        noteImages.add(R.drawable.quarter_note);                             //4
        noteImages.add(R.drawable.quarter_tied_sixteenth);                   //5
        noteImages.add(R.drawable.quarter_note_dotted_single_line);          //6
        noteImages.add(R.drawable.quarter_note_dotted_double_line);          //7
        noteImages.add(R.drawable.half_note);                                //8
        noteImages.add(R.drawable.half_tied_sixteenth);                      //9
        noteImages.add(R.drawable.half_tied_eighth);                         //10
        noteImages.add(R.drawable.half_tied_dotted_eighth);                  //11
        noteImages.add(R.drawable.half_note_dotted_single_line);             //12
        noteImages.add(R.drawable.dotted_half_tied_sixteenth);               //13
        noteImages.add(R.drawable.half_note_dotted_double_line);             //14
        noteImages.add(R.drawable.double_dotted_half_tied_sixteenth);        //15
        noteImages.add(R.drawable.whole_note);                               //16
    }

    private static void populateFlatsArray() {                               //Rhythmic Value:
        noteImages.add(R.drawable.sixteenth_note_flat);                      //1
        noteImages.add(R.drawable.eighth_note_flat);                         //2
        noteImages.add(R.drawable.eighth_note_dotted_single_flat);           //3
        noteImages.add(R.drawable.quarter_note_flat);                        //4
        noteImages.add(R.drawable.quarter_tied_sixteenth_flat);              //5
        noteImages.add(R.drawable.quarter_note_dotted_single_flat);          //6
        noteImages.add(R.drawable.quarter_note_dotted_double_flat);          //7
        noteImages.add(R.drawable.half_note_flat);                           //8
        noteImages.add(R.drawable.half_tied_sixteenth_flat);                 //9
        noteImages.add(R.drawable.half_tied_eighth_flat);                    //10
        noteImages.add(R.drawable.half_tied_dotted_eighth_flat);             //11
        noteImages.add(R.drawable.half_note_dotted_single_flat);             //12
        noteImages.add(R.drawable.single_dotted_half_tied_sixteenth_flat);   //13
        noteImages.add(R.drawable.half_note_dotted_double_flat);             //14
        noteImages.add(R.drawable.double_dotted_half_tied_sixteenth_flat);   //15
        noteImages.add(R.drawable.whole_note_flat);                          //16
    }

    private static void populateSharpsArray() {                               //Rhythmic Value:
        noteImages.add(R.drawable.sixteenth_note_sharp);                      //1
        noteImages.add(R.drawable.eighth_note_sharp);                         //2
        noteImages.add(R.drawable.eighth_note_dotted_single_sharp);           //3
        noteImages.add(R.drawable.quarter_note_sharp);                        //4
        noteImages.add(R.drawable.quarter_tied_sixteenth_sharp);              //5
        noteImages.add(R.drawable.quarter_note_dotted_single_sharp);          //6
        noteImages.add(R.drawable.quarter_note_dotted_double_sharp);          //7
        noteImages.add(R.drawable.half_note_sharp);                           //8
        noteImages.add(R.drawable.half_tied_sixteenth_sharp);                 //9
        noteImages.add(R.drawable.half_tied_eighth_sharp);                    //10
        noteImages.add(R.drawable.half_tied_dotted_eighth_sharp);             //11
        noteImages.add(R.drawable.half_note_dotted_single_sharp);             //12
        noteImages.add(R.drawable.single_dotted_half_tied_sixteenth_sharp);   //13
        noteImages.add(R.drawable.half_note_dotted_double_sharp);             //14
        noteImages.add(R.drawable.double_dotted_half_tied_sixteenth_sharp);   //15
        noteImages.add(R.drawable.whole_note_sharp);                          //16
    }
}


