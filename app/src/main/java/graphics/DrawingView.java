package graphics;

import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;

import java.util.ArrayList;

import music.model.Note;
import music.model.Staff;
import projectsaeje.MainActivity;

public class DrawingView extends View {
    //drawing and canvas paint
    private Paint canvasPaint;
    //canvas
    public Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;


    public DrawingView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
    }

    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    /*public void displayStaff(Canvas canvas){
        Staff s = MainActivity.staff;
        int currentMeasure = (s.measures.get(s.measures.size()-1)).measureNumber;
        Staff boStaff = measureChooser(s, currentMeasure, 0);

        ArrayList<DisplayedNote> onScreenNotes = new ArrayList<>();

        for(Note aNote : boStaff.measures)
            onScreenNotes.add(new DisplayedNote(aNote, aBitmap, x, y)); //Converts the Note class to a Displayed Note with it's bitmap, x coor and y coor

        for(DisplayedNote aDNote : onScreenNotes)
            aDNote.displayNote(canvas);

    };

    public Staff measureChooser(Staff theStaff, int currentMeasure, int swipe) {
        Staff displayedTwo = new Staff();

        if (swipe == 0)
            displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 1));
        displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 2));


        return displayedTwo;
    }*/
}
