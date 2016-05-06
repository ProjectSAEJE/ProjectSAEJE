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
    private int num_times_drawn_since_beat_occurred;
    private static int num_times_beat_signifier_will_be_drawn;
    public static boolean a_beat_just_occurred;
    public static int beat_num_from_metronome;
    public static int time_signature_top_num_from_metronome = 4;
    private Paint the_circle_paint;
    private Paint the_text_paint;
    public static boolean isDrawingViewSet = false;
    private static int beat_signifier_circle_x;
    private static int beat_signifier_circle_y;
    private static int beat_signifier_text_x;
    private static int beat_signifier_text_y;
    
    private static double x = 0;
    

    public DrawingView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupDrawing();
        this.num_times_drawn_since_beat_occurred = 0;
        num_times_beat_signifier_will_be_drawn = 7;
        beat_signifier_circle_x = 340;
        beat_signifier_circle_y = 150;
        beat_signifier_text_x = beat_signifier_circle_x + 45;
        beat_signifier_text_y = beat_signifier_circle_y + 45;
        the_text_paint = new Paint();
        the_text_paint.setColor(Color.BLACK);
        the_text_paint.setTextSize(140);

        the_circle_paint = new Paint();
        the_circle_paint.setColor(Color.GREEN);

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

        //Log.d("is_drawing_...: ", "" + this.a_beat_just_occurred);

        //The following logic is intended to draw a beat signifier (circle or some shape) if either
        // 1) A beat just occured or
        // 2) a beat occurred very recently and we still want to see the signifier.

        //boolean is_drawing_beat_signifier = false;

        //Log.d("", "a_beat_just_occurred: " + this.a_beat_just_occurred);
        if (a_beat_just_occurred) {
            this.num_times_drawn_since_beat_occurred = 0;
        }

        /*
        if ((this.a_beat_just_occurred) { ||
                ((0 <= this.num_times_drawn_since_beat_occurred) && (this.num_times_drawn_since_beat_occurred < this.num_times_beat_signifier_will_be_drawn))) {
            is_drawing_beat_signifier = true;
        }
        */

        if (this.num_times_drawn_since_beat_occurred < num_times_beat_signifier_will_be_drawn) {
            //Log.d("", "adding one to: " + this.num_times_drawn_since_beat_occurred);
            this.num_times_drawn_since_beat_occurred += 1;

            String text_to_display = "" + (beat_num_from_metronome % time_signature_top_num_from_metronome);
            if (text_to_display.equals("0")) {
                text_to_display = "" + time_signature_top_num_from_metronome;
            }
            if(text_to_display.equals("1")) {
                the_circle_paint.setColor(Color.rgb(255, 220, 120));
            }
            else {
                the_circle_paint.setColor(Color.rgb(50, 255, 255));
            }

            //Log.d("", "Drawing circle b/c this.is_drawing = " + this.a_beat_just_occurred + " and num_X_has_been = " + this.num_times_drawn_since_beat_occurred);
            the_circle_paint.setAlpha((int) ((255) / num_times_drawn_since_beat_occurred));
            the_text_paint.setAlpha((int) ((255) / num_times_drawn_since_beat_occurred));
            canvas.drawCircle(width - beat_signifier_circle_x, beat_signifier_circle_y, 90, the_circle_paint);
            canvas.drawText(text_to_display, width - beat_signifier_text_x, beat_signifier_text_y, this.the_text_paint);
            //Log.d("", "Drawing circle #: " + this.num_times_drawn_since_beat_occurred + " at: (" + this.beat_signifier_circle_x + ", " + this.beat_signifier_circle_y + ")" + " for beat #: " + this.beat_num_from_metronome);
        }

        //Log.d("", "num_X_has_been: " + this.num_times_drawn_since_beat_occurred);

        this.invalidate();
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
