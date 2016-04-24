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

        for(Notation aMeasure: MainActivity.staff.getCurrentMeasures()) {
            Measure myMeasure = (Measure)(aMeasure);

            for(Notation aNotation: myMeasure.getElements()) {
                Note aNote = (Note)(aNotation);
                canvas.drawBitmap(aNote.getScaledBitmap(), (int)(x), (int)(getNoteY(aNote)), null);
                x += (aNote.getRhythmicValue()+1) * 80;
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

    private double getNoteY(Note tN) {

        double y;
        double height = drawCanvas.getHeight();

        switch(tN.getTonalValue()%12) {
            case 0: // C
                y = (height*0.26128);
                return y;
            case 1: // D Flat
                y = (height*0.22222);
                return y;
            case 2: // D
                y = (height*0.22222);
                return y;
            case 3: // E Flat
                //y = (height*0.44705);
                y = (height*0.18576);
                return y;
            case 4: // E
                //y = (height*0.44705);
                y = (height*0.18576);
                return y;
            case 5: // F
                y = (height*0.41059);
                return y;
            case 6: // G Flat
                y = (height*0.37240);
                return y;
            case 7: // G
                y = (height*0.37240);
                return y;
            case 8: // A Flat
                y = (height*0.33594);
                return y;
            case 9: // A
                y = (height*0.33594);
                return y;
            case 10: // B Flat
                y = (height*0.29948);
                return y;
            case 11: // B
                y = (height*0.29948);
                return y;
            default:
                y = 100000;
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
