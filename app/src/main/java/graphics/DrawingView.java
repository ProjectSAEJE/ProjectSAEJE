package graphics;

import android.graphics.BitmapFactory;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;

import music.model.Notation.MusicalSymbols.Note;
import music.model.Notation.*;
import music.model.Notation.Measure;
import com.example.woodev01.projectsaeje.R;

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
            for(Notation aNotation: aMeasure.getElements()) {
                Note aNote = (Note)(aNotation);
                canvas.drawBitmap(aNote.getScaledBitmap(), (int)(x), (int)(getNoteY(aNote)), null);
                x += aNote.getRhythmicValue() * 80;
                //x += 50;
            }
        };
        this.invalidate();
    }

    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void changeX(int subtractor) {
        x -= subtractor;
    }

    private int getNoteY(Note tN) {

        int y;
        int height = drawCanvas.getHeight();
        y = (height/2) - 362;
        //return y;

        switch(tN.getTonalValue()%12) {
            case 0:
                y = (height/2)-270;
                return y;
            case 1:
                y = (height/2)-270;
                return y;
            case 2:
                y = (height/2)-270;
                return y;
            case 3:
                y = (height/2)-270;
                return y;
            case 4:
                y = (height/2)-270;
                return y;
            case 5:
                y = (height/2)-270;
                return y;
            case 6:
                y = (height/2)-270;
                return y;
            case 7:
                y = (height/2)-270;
                return y;
            case 8:
                y = (height/2)-270;
                return y;
            case 9:
                y = (height/2)-270;
                return y;
            case 10:
                y = (height/2)-270;
                return y;
            case 11:
                y = (height/2)-270;
                return y;
            default:
                y = (height/2)-270;
                return y;

        }
    }

    /*public Staff measureChooser(Staff theStaff, int currentMeasure, int swipe) {
        Staff displayedTwo = new Staff();

        if (swipe == 0)
            displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 1));
        displayedTwo.measures.add(theStaff.measures.get(currentMeasure - 2));


        return displayedTwo;
    }   */
}
