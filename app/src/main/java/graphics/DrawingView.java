package graphics;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;

import music.model.Notation.MusicalSymbols.MusicalSymbol;
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
    private int numTimesDrawnSinceLastBeat;
    private static int numTimesToDrawBeatSignifier;
    public static boolean aBeatJustOccurred;
    public static int metronomeBeatNum;
    public static int timeSigTopNumber = 4;
    private Paint theCirclePaint;
    private Paint theTextPaint;
    public static boolean isDrawingViewSet = false;
    private static int beatSignifierCircleX;
    private static int beatSignifierCircleY;
    private static int beatSignifierTextX;
    private static int beatSignifierTextY;
    
    private static double x = 0;
    

    public DrawingView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupDrawing();
        this.numTimesDrawnSinceLastBeat = 0;
        numTimesToDrawBeatSignifier = 7;
        beatSignifierCircleX = 340;
        beatSignifierCircleY = 150;
        beatSignifierTextX = beatSignifierCircleX + 45;
        beatSignifierTextY = beatSignifierCircleY + 45;
        theTextPaint = new Paint();
        theTextPaint.setColor(Color.BLACK);
        theTextPaint.setTextSize(140);

        theCirclePaint = new Paint();
        theCirclePaint.setColor(Color.GREEN);

    }

    private void setupDrawing() { canvasPaint = new Paint(Paint.DITHER_FLAG); }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        int height = drawCanvas.getHeight();
        int width = drawCanvas.getWidth();
        //Log.d("Height is: ", Integer.toString(height));
        //Log.d("Width is: ", Integer.toString(width));
    }

    @Override
    public void onDraw(Canvas canvas) {
        int width = drawCanvas.getWidth();
        x = (width*0.03125);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);

        if(!isDrawingViewSet){
            MainActivity.staff.setCurrentMeasures();
            isDrawingViewSet = true;
        }

        for(Notation aMeasure: MainActivity.staff.getCurrentMeasures()) {
            Measure myMeasure = (Measure)(aMeasure);

            for(Notation aNotation: myMeasure.getElements()) {
                MusicalSymbol aNote = (MusicalSymbol)(aNotation);
                canvas.drawBitmap(aNote.getScaledBitmap(), (int)(x), (int)(getNoteY(aNote)), null);
                x += (aNote.getRhythmicValue()) * (width*0.03125);
                //x += 50;
            }
        };

        //Log.d("is_drawing_...: ", "" + this.aBeatJustOccurred);

        //The following logic is intended to draw a beat signifier (circle or some shape) if either
        // 1) A beat just occured or
        // 2) a beat occurred very recently and we still want to see the signifier.

        //boolean isDrawingBeatSignifier = false;

        //Log.d("", "aBeatJustOccurred: " + this.aBeatJustOccurred);
        if (aBeatJustOccurred) {
            this.numTimesDrawnSinceLastBeat = 0;
        }

        /*
        if ((this.aBeatJustOccurred) { ||
                ((0 <= this.numTimesDrawnSinceLastBeat) && (this.numTimesDrawnSinceLastBeat < this.numTimesToDrawBeatSignifier))) {
            isDrawingBeatSignifier = true;
        }
        */

        if (this.numTimesDrawnSinceLastBeat < numTimesToDrawBeatSignifier) {
            //Log.d("", "adding one to: " + this.numTimesDrawnSinceLastBeat);
            this.numTimesDrawnSinceLastBeat += 1;

            String textToDisplay = "" + (metronomeBeatNum % timeSigTopNumber);
            if (textToDisplay.equals("0")) {
                textToDisplay = "" + timeSigTopNumber;
            }
            if(textToDisplay.equals("1")) {
                theCirclePaint.setColor(Color.rgb(255, 220, 120));
            }
            else {
                theCirclePaint.setColor(Color.rgb(50, 255, 255));
            }

            //Log.d("", "Drawing circle b/c this.is_drawing = " + this.aBeatJustOccurred + " and num_X_has_been = " + this.numTimesDrawnSinceLastBeat);
            theCirclePaint.setAlpha((int) ((255) / numTimesDrawnSinceLastBeat));
            theTextPaint.setAlpha((int) ((255) / numTimesDrawnSinceLastBeat));
            canvas.drawCircle(width - beatSignifierCircleX, beatSignifierCircleY, 70, theCirclePaint);
            canvas.drawText(textToDisplay, width - beatSignifierTextX, beatSignifierTextY, this.theTextPaint);
            //Log.d("", "Drawing circle #: " + this.numTimesDrawnSinceLastBeat + " at: (" + this.beatSignifierCircleX + ", " + this.beatSignifierCircleY + ")" + " for beat #: " + this.metronomeBeatNum);
        }

        //Log.d("", "num_X_has_been: " + this.numTimesDrawnSinceLastBeat);

        this.invalidate();

        Paint lineTestPaint = new Paint();
        canvas.drawLine(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight(), lineTestPaint);

    }

    public void startNew() {
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    Note clickedNote = null;
    int y = 0;
    int posX = 0;

    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                posX = (int) event.getX();
                y = (int) event.getY();
                double sixteenthWidth = drawCanvas.getWidth() * .03125;
                int notePosition = (int)(event.getX() / sixteenthWidth);
                Log.d("Note Pos", "" + notePosition);

                for(Notation element: MainActivity.staff.getCurrentMeasures()) {
                    Measure aMeasure = (Measure) element;
                    int itr = 0;
                    if (MainActivity.beatNum * MainActivity.beats > notePosition){
                        for(Notation myElement: aMeasure.getElements()) {
                            itr+=1;
                            Note myNote = (Note)myElement;
                            notePosition -= myNote.getRhythmicValue();
                            Log.d("Note Pos 2", ""+notePosition);
                            if(notePosition <= 0){
                                Log.d("Note Number", "" + itr);
                                clickedNote = myNote;
                                return true;
                            }
                        }
                    } else {
                        notePosition -= MainActivity.beatNum * MainActivity.beats;
                    }
                }

                return true;

            case MotionEvent.ACTION_MOVE:

                return true;


            case MotionEvent.ACTION_UP:

                if (posX - (int) event.getX() > 50){
                    MainActivity.staff.moveUpCurrentMeasures(getContext());
                    return true;
                }
                if (posX - (int) event.getX() < -50) {
                    MainActivity.staff.moveBackCurrentMeasures(getContext());
                    return true;
                }

                if (clickedNote != null) {
                    y = y - (int) event.getY();
                    y = (int) (y/5);
                    Log.d("Y is: ", "" + y);
                    y = clickedNote.getTonalValue() + y;
                    clickedNote.setTonalValue(y);
                    this.draw(this.drawCanvas);
                    this.startNew();
                }
                return true;

        }
        return true;
    }


    public void changeX(int subtractor) {
        x -= subtractor;
    }

    private double getNoteY(MusicalSymbol tN) {

        double y;
        double height = drawCanvas.getHeight();

        switch(tN.getTonalValue()%12) {
            case 0:  // A Flat
                y = (height*0.33594);
                return y;
            case 1:  // A
                y = (height*0.33594);
                return y;
            case 2:  // B Flat
                y = (height*0.29948);
                return y;
            case 3:  // B
                y = (height*0.29948);
                return y;
            case 4:  // C
                y = (height*0.26128);
                return y;
            case 5:  // D Flat
                y = (height*0.22222);
                return y;
            case 6:  // D
                y = (height*0.22222);
                return y;
            case 7:  // E Flat
                //y = (height*0.44705);
                y = (height*0.18576);
                return y;
            case 8:  // E
                //y = (height*0.44705);
                y = (height*0.18576);
                return y;
            case 9:  // F
                y = (height*0.41059);
                return y;
            case 10: // G Flat
                y = (height*0.37240);
                return y;
            case 11: // G
                y = (height*0.37240);
                return y;
            default:
                y = (height*0.35877);
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
