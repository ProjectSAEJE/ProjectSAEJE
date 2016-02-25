package graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import music.Measure;
import music.Staff;
import projectsaeje.MainActivity;


public class StaffDisplay extends Drawable {

    public StaffDisplay(){};


    public static void displayStaff(android.graphics.Canvas canvas){
        Staff s = MainActivity.staff;
        int currentMeasure = (s.measures.get(s.measures.size()-1)).measureNumber;
        Staff boStaff = measureChooser(s, currentMeasure, 0);
    };

    public static Staff measureChooser(Staff theStaff, int currentMeasure, int swipe) {
        Staff displayedTwo = new Staff();

        if (swipe == 0)  {
            displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 1));
            displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 2));
        }

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
