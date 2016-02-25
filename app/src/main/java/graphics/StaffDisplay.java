package graphics;



import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import music.Measure;
import music.Staff;

public class StaffDisplay extends Drawable {
    
    public StaffDisplay(){};

    public static void displayStaff(android.graphics.Canvas canvas){
        chooseMeasures();
    };


    public Staff chooseMeasures() {
        Staff s = new Staff();
        return s;
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
