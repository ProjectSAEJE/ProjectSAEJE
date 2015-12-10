package projectsaeje;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Handler;
import android.os.Message;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;
import java.lang.Math;

import audio.CaptureThread;
import graphics.DrawingView;
import graphics.Note;
import graphics.Staff;

public class MainActivity extends Activity {

    private CaptureThread mCapture;
    private Handler mHandler;
    private Boolean isClicked = false;
    public static Staff staff;
    public DrawingView drawView;
    private Boolean noteMapsBuilt = false;

    // These will hold note mapping values:
    public static Integer xVal = new Integer(0);
    private ArrayList<Integer> yVals = new ArrayList<>();
    private ArrayList<Integer> sizes = new ArrayList<>();
    private ArrayList<Integer> noteImages = new ArrayList<>();

    private void buildNoteMaps() {
        if (yVals.isEmpty()) {
            // Builds the arrayList of Y values:
            yVals.add(drawView.drawCanvas.getHeight()/2-365);
            yVals.add(drawView.drawCanvas.getHeight()/2-415);
            yVals.add(drawView.drawCanvas.getHeight()/2-435);
            yVals.add(drawView.drawCanvas.getHeight()/2+155);
            yVals.add(drawView.drawCanvas.getHeight()/2+62);
            yVals.add(drawView.drawCanvas.getHeight()/2+15);
            yVals.add(drawView.drawCanvas.getHeight()/2-30);
            yVals.add(drawView.drawCanvas.getHeight()/2-45);
            yVals.add(drawView.drawCanvas.getHeight()/2-125);
            yVals.add(drawView.drawCanvas.getHeight()/2-170);
            yVals.add(drawView.drawCanvas.getHeight()/2-225);
            yVals.add(drawView.drawCanvas.getHeight()/2-320);
        }

        if (sizes.isEmpty()) {
            // Builds the arrayList of Note image size parameters:
            sizes.add(400);
            sizes.add(300);
            sizes.add(350);
            sizes.add(300);
            sizes.add(300);
            sizes.add(400);
            sizes.add(300);
            sizes.add(350);
            sizes.add(300);
            sizes.add(400);
            sizes.add(300);
            sizes.add(300);
        }

        if (noteImages.isEmpty()) {
            // Builds the arrayList of image resource names:
            noteImages.add(R.drawable.ic_quarter_note_sharp_space);
            noteImages.add(R.drawable.ic_quarter_note);
            noteImages.add(R.drawable.ic_quarter_note_sharp_line);
            noteImages.add(R.drawable.ic_quarter_note);
            noteImages.add(R.drawable.ic_quarter_note);
            noteImages.add(R.drawable.ic_quarter_note_sharp_space);
            noteImages.add(R.drawable.ic_quarter_note);
            noteImages.add(R.drawable.ic_quarter_note_sharp_line);
            noteImages.add(R.drawable.ic_quarter_note);
            noteImages.add(R.drawable.ic_quarter_note_sharp_space);
            noteImages.add(R.drawable.ic_quarter_note);
            noteImages.add(R.drawable.ic_quarter_note);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = (DrawingView)findViewById(R.id.drawing);
        this.staff = new Staff();

        Note note = new Note(250, 0, "quarter",this);
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(note);

        staff = new Staff(notes, this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCapture != null) {
            mCapture.setRunning(false);
            mCapture = null;
        }
    }

    /*@Override
    protected void onPause() {
        super.onPause();

        mCapture.setRunning(false);
    }*/

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void updateDisplay(float freq){
        xVal += 130;
        Note newNote = new Note(0, xVal, "quarter", this);
        int screenNoteNumber = Math.abs(newNote.updateYValue(freq)%12);

        // Retrieve values from arrayLists:
        int y = yVals.get(screenNoteNumber);
        int s = sizes.get(screenNoteNumber);
        int r = noteImages.get(screenNoteNumber);


        // Modify newNote with retrieved values:
        Bitmap b = BitmapFactory.decodeResource(this.getResources(), r);
        newNote.image = Bitmap.createScaledBitmap(b, s, s, false);
        newNote.y = y;

        /*
        switch (screenNoteNumber) {
            case 0: // C#
                // changes note resource image to that of a sharp note
                Bitmap c_sharp = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note_sharp_space);
                newNote.image = Bitmap.createScaledBitmap(c_sharp,400,400, false);
                newNote.y = drawView.drawCanvas.getHeight()/2-365;
                break;
            case 1: // D
                // changes note resource image to that of a natural note
                Bitmap d = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note);
                newNote.image = Bitmap.createScaledBitmap(d,300,300, false);
                newNote.y = drawView.drawCanvas.getHeight()/2-415;
                break;
            case 2: // D#
                Bitmap d_sharp = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note_sharp_line);
                newNote.image = Bitmap.createScaledBitmap(d_sharp,350,350, false);
                newNote.y = drawView.drawCanvas.getHeight()/2-435;
                break;
            case 3: // E
                Bitmap e = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note);
                newNote.image = Bitmap.createScaledBitmap(e,300,300, false);
                newNote.y = drawView.drawCanvas.getHeight()/2+155;
                break;
            case 4: // F
                Bitmap f = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note);
                newNote.image = Bitmap.createScaledBitmap(f,300,300, false);
                newNote.y = drawView.drawCanvas.getHeight()/2+62;
                break;
            case 5: // F#
                Bitmap f_sharp = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note_sharp_space);
                newNote.image = Bitmap.createScaledBitmap(f_sharp,400,400, false);
                newNote.y = drawView.drawCanvas.getHeight()/2+15;
                break;
            case 6: // G
                Bitmap g = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note);
                newNote.image = Bitmap.createScaledBitmap(g,300,300, false);
                newNote.y = drawView.drawCanvas.getHeight()/2-30;
                break;
            case 7: // G#
                Bitmap g_sharp = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note_sharp_line);
                newNote.image = Bitmap.createScaledBitmap(g_sharp,350,350, false);
                newNote.y = drawView.drawCanvas.getHeight()/2-45;
                break;
            case 8: // A
                Bitmap a = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note);
                newNote.image = Bitmap.createScaledBitmap(a,300,300, false);
                newNote.y = drawView.drawCanvas.getHeight()/2-125;
                break;
            case 9: // A#
                Bitmap a_sharp = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note_sharp_space);
                newNote.image = Bitmap.createScaledBitmap(a_sharp,400,400, false);
                newNote.y = drawView.drawCanvas.getHeight()/2-170;
                break;
            case 10: // B
                Bitmap b = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note);
                newNote.image = Bitmap.createScaledBitmap(b,300,300, false);
                newNote.y = drawView.drawCanvas.getHeight()/2-225;
                break;
            case 11: // C
                Bitmap c = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_note);
                newNote.image = Bitmap.createScaledBitmap(c,300,300, false);
                newNote.y = drawView.drawCanvas.getHeight()/2-320;
                break;
            default: // Shouldn't ever happen...
                break;
        }
        */
        staff.notes.add(newNote);


        drawView.startNew();
        drawView.draw(drawView.drawCanvas);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:
                if (!isClicked) {

                    if (!noteMapsBuilt) {
                        buildNoteMaps();
                    }

                    //set record icon to stop icon
                    item.setIcon(R.drawable.ic_stop);

                    mHandler = new Handler() {
                        @Override
                        public synchronized void handleMessage(Message m) {
                            updateDisplay(m.getData().getFloat("Freq"));
                        }
                    };

                    mCapture = new CaptureThread(mHandler);
                    mCapture.setRunning(true);
                    mCapture.start();

                    isClicked = true;
                }
                else
                {
                    //changes stop icon back to play icon on the record button
                    item.setIcon(R.drawable.ic_play_arrow);
                    mCapture.setRunning(false);
                    isClicked = false;
                }

                return true;

            case R.id.open:

                return true;

            case R.id.save:

                staff.save();

                return true;

            case R.id.clear:
                //Bitmap newBackgroundBitmap = Bitmap.createBitmap(BitmapFactory.decodeFile("~/AndroidStudioProjects/ProjectSAEJE/app/src/main/res/drawable/ic_staff.png"));
                //drawView.drawCanvas.drawBitmap(newBackgroundBitmap, 0, 0, null);
                //staff.notes.get(0).x = 0;
                xVal = 0;
                staff.notes.clear();

                drawView.startNew();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}