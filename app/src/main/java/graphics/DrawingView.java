package graphics;

import android.util.Base64OutputStream;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;

import java.util.ArrayList;

import music.model.*;
import projectsaeje.MainActivity;

public class DrawingView extends View {
    //drawing and canvas paint
    private Paint canvasPaint;
    //canvas
    public Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;

    private static int x = 50;

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
        int height = drawCanvas.getHeight();
        int width = drawCanvas.getWidth();
        Log.d("Height is: ", Integer.toString(height));
        Log.d("Width is: ", Integer.toString(width));
    }

    @Override
    public void onDraw(Canvas canvas) {
        x = 50;
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        MainActivity.staff.setCurrentMeasures();
        for(Measure aMeasure: MainActivity.staff.getCurrentMeasures()) {
            for(Note aNote: aMeasure.notes) {
                int y = set_y(getNoteY(aNote.tonalValue));
                canvas.drawBitmap(aNote.scaledBitmap, x, y, null);
                x += aNote.rhythmicValue * 80;
                //x += 50;
            }
        };
        this.invalidate();
    }

    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    private int set_y(int tonal_value) { // yes, this currently draws all the notes on the middle line(or it should)
        int height = drawCanvas.getHeight();

        switch(tonal_value%12) {
            case 0:
                return height/2;
            case 1:
                return height/2;
            case 2:
                return height/2;
            case 3:
                return height/2;
            case 4:
                return height/2;
            case 5:
                return height/2;
            case 6:
                return height/2;
            case 7:
                return height/2;
            case 8:
                return height/2;
            case 9:
                return height/2;
            case 10:
                return height/2;
            case 11:
                return height/2;
            default:
                return height/2;
        }
    }

    private int getNoteY(int tV) {

        int y = (tV * 10);

        return y;
    }

    /*public Staff measureChooser(Staff theStaff, int currentMeasure, int swipe) {
        Staff displayedTwo = new Staff();

        if (swipe == 0)
            displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 1));
        displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 2));


        return displayedTwo;
    }   */
}
