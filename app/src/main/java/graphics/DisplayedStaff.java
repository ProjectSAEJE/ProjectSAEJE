package graphics;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;

import music.model.Measure;
import music.model.Staff;
import music.model.Note;
import projectsaeje.MainActivity;


public class DisplayedStaff extends Drawable {

    public DisplayedStaff(){};


    public void displayStaff(Canvas canvas){
        Staff s = MainActivity.staff;
        int currentMeasure = (s.measures.get(s.measures.size()-1)).measureNumber;
        Staff boStaff = measureChooser(s, currentMeasure, 0);

        ArrayList<DisplayedNote> onScreenNotes = new ArrayList<>();

        for (Measure aMeasure : boStaff.measures){
            for(Note aNote : aMeasure.notes)
                onScreenNotes.add(new DisplayedNote(aNote, aBitmap, x, y)); //Converts the Note class to a Displayed Note with it's bitmap, x coor and y coor
        }

        for(DisplayedNote aDNote : onScreenNotes)
            aDNote.displayNote(canvas);

    };

    public Staff measureChooser(Staff theStaff, int currentMeasure, int swipe) {
        Staff displayedTwo = new Staff();

        if (swipe == 0)
            displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 1));
            displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 2));


        return displayedTwo;
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
