package graphics;

import android.util.Base64OutputStream;
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

    private int x = 50;

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
        MainActivity.staff.setCurrentMeasures();
        for(Measure aMeasure: MainActivity.staff.getCurrentMeasures()) {
            for(Note aNote: aMeasure.notes) {
                canvas.drawBitmap(aNote.scaledBitmap, x, getNoteY(aNote.tonalValue), null);
                x += aNote.rhythmicValue;
            }
        };
    }

    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void staffUpdatedDrawPlease() {
        invalidate();
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
