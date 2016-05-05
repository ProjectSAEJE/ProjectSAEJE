package music.model.PureDataTypes;


import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

public class Images {

    public static ArrayList<Integer> noteImages = new ArrayList<>();
    public static ArrayList<Integer> flatImages = new ArrayList<>();
    public static ArrayList<Integer> sharpImages = new ArrayList<>();
    public static ArrayList<Integer> restImages = new ArrayList<>();

    /*public Images() {
        noteImages = new ArrayList<>();
    }*/

    public void populateArrays() {
        populateNoteArraysArrays();
        populateFlatsArray();
        populateSharpsArray();
        populateRestsArray();
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
        flatImages.add(R.drawable.sixteenth_note_flat);                      //1
        flatImages.add(R.drawable.eighth_note_flat);                         //2
        flatImages.add(R.drawable.eighth_note_dotted_single_flat);           //3
        flatImages.add(R.drawable.quarter_note_flat);                        //4
        flatImages.add(R.drawable.quarter_tied_sixteenth_flat);              //5
        flatImages.add(R.drawable.quarter_note_dotted_single_flat);          //6
        flatImages.add(R.drawable.quarter_note_dotted_double_flat);          //7
        flatImages.add(R.drawable.half_note_flat);                           //8
        flatImages.add(R.drawable.half_tied_sixteenth_flat);                 //9
        flatImages.add(R.drawable.half_tied_eighth_flat);                    //10
        flatImages.add(R.drawable.half_tied_dotted_eighth_flat);             //11
        flatImages.add(R.drawable.half_note_dotted_single_flat);             //12
        flatImages.add(R.drawable.single_dotted_half_tied_sixteenth_flat);   //13
        flatImages.add(R.drawable.half_note_dotted_double_flat);             //14
        flatImages.add(R.drawable.double_dotted_half_tied_sixteenth_flat);   //15
        flatImages.add(R.drawable.whole_note_flat);                          //16
    }

    private static void populateSharpsArray() {                               //Rhythmic Value:
        sharpImages.add(R.drawable.sixteenth_note_sharp);                      //1
        sharpImages.add(R.drawable.eighth_note_sharp);                         //2
        sharpImages.add(R.drawable.eighth_note_dotted_single_sharp);           //3
        sharpImages.add(R.drawable.quarter_note_sharp);                        //4
        sharpImages.add(R.drawable.quarter_tied_sixteenth_sharp);              //5
        sharpImages.add(R.drawable.quarter_note_dotted_single_sharp);          //6
        sharpImages.add(R.drawable.quarter_note_dotted_double_sharp);          //7
        sharpImages.add(R.drawable.half_note_sharp);                           //8
        sharpImages.add(R.drawable.half_tied_sixteenth_sharp);                 //9
        sharpImages.add(R.drawable.half_tied_eighth_sharp);                    //10
        sharpImages.add(R.drawable.half_tied_dotted_eighth_sharp);             //11
        sharpImages.add(R.drawable.half_note_dotted_single_sharp);             //12
        sharpImages.add(R.drawable.single_dotted_half_tied_sixteenth_sharp);   //13
        sharpImages.add(R.drawable.half_note_dotted_double_sharp);             //14
        sharpImages.add(R.drawable.double_dotted_half_tied_sixteenth_sharp);   //15
        sharpImages.add(R.drawable.whole_note_sharp);                          //16
    }

    private static void populateRestsArray() {                                 //Rhythmic Value:
        restImages.add(R.drawable.sixteenth_rest);                             //1
        restImages.add(R.drawable.eighth_rest);                                //2
        restImages.add(R.drawable.dotted_eighth_rest);                         //3
        restImages.add(R.drawable.quarter_rest);                               //4
        restImages.add(R.drawable.quarter_tied_sixteenth_rest);                //5
        restImages.add(R.drawable.single_dotted_quarter_rest);                 //6
        restImages.add(R.drawable.double_dotted_quarter_rest);                 //7
        restImages.add(R.drawable.half_rest);                                  //8
        restImages.add(R.drawable.half_tied_sixteenth_rest);                   //9
        restImages.add(R.drawable.half_tied_eighth_rest);                      //10
        restImages.add(R.drawable.half_tied_dotted_eighth_rest);               //11
        restImages.add(R.drawable.single_dotted_half_rest);                    //12
        restImages.add(R.drawable.dotted_half_tied_sixteenth_rest);            //13
        restImages.add(R.drawable.double_dotted_half_rest);                    //14
        restImages.add(R.drawable.double_dotted_half_tied_sixteenth_rest);     //15
        restImages.add(R.drawable.whole_rest);                                 //16
    }

}

