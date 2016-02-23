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

import audio.CaptureThread;
import graphics.DrawingView;
import music.Note;
import music.Staff;

public class MainActivity extends Activity {

    private CaptureThread mCapture;
    private Handler mHandler;
    private Boolean isClicked = false;
    public static Staff staff;
    public DrawingView drawView;
    public static Integer xVal = 0;
    public static Integer demoLoopCounter = 0;
    public static ArrayList<Integer> bmvals = new ArrayList<Integer>();
    public static ArrayList<Integer> svals = new ArrayList<Integer>();
    public static ArrayList<Integer> yvals = new ArrayList<Integer>();
    public Boolean arraysBuilt = false;

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

    public int NoteEvaluator(float freq) {
        double logCalcX = Math.log(freq / 440);
        double logCalcY = Math.log(2);

        int pianoNoteNumber = (int) (12 * (logCalcX + 49) / logCalcY);
        return pianoNoteNumber;
    }

    public void populateArrays(){
        int middle = drawView.drawCanvas.getHeight()/2;


        bmvals.add(R.drawable.ic_quarter_note_sharp_space);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note_sharp_line);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note_sharp_space);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note_sharp_line);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note_sharp_space);
        bmvals.add(R.drawable.ic_quarter_note);
        bmvals.add(R.drawable.ic_quarter_note);

        svals.add(400);
        svals.add(300);
        svals.add(350);
        svals.add(300);
        svals.add(300);
        svals.add(400);
        svals.add(300);
        svals.add(350);
        svals.add(300);
        svals.add(400);
        svals.add(300);
        svals.add(300);

        yvals.add(middle-365);
        yvals.add(middle-415);
        yvals.add(middle-435);
        yvals.add(middle+155);
        yvals.add(middle+62);
        yvals.add(middle+15);
        yvals.add(middle-30);
        yvals.add(middle-45);
        yvals.add(middle-125);
        yvals.add(middle-170);
        yvals.add(middle-225);
        yvals.add(middle-320);

        arraysBuilt = true;

    }

    public void updateDisplay(float freq){
        if (!arraysBuilt){
            populateArrays();
        }

        if (demoLoopCounter == 12) {
            xVal = 0;
            demoLoopCounter = 0;
            staff.notes.clear();
            drawView.startNew();
        }

        demoLoopCounter += 1;

        xVal += 130;
        Note newNote = new Note(0, xVal, "quarter", this);
        int screenNoteNumber = NoteEvaluator(freq)%12;



        try{

            int sbn = bmvals.get(screenNoteNumber); // Screen Bitmap Number
            int sns = svals.get(screenNoteNumber); // Screen Note Size
            int sny = yvals.get(screenNoteNumber); // Screen Note Y-value

            Bitmap b = BitmapFactory.decodeResource(this.getResources(),sbn);
            newNote.image = Bitmap.createScaledBitmap(b,sns,sns, false);
            newNote.y = sny;

        } catch(Exception e) {

            Bitmap b = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_quarter_rest);
            newNote.image = Bitmap.createScaledBitmap(b,300,300, false);
            newNote.y = drawView.drawCanvas.getHeight()/2 - 150;

        } finally {

            staff.notes.add(newNote);

            drawView.startNew();
            drawView.draw(drawView.drawCanvas);

        }

    }

    private MenuItem recordItem;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:
                if (!isClicked) {
                    recordItem = item;
                    //set record icon to stop icon
                    item.setIcon(R.drawable.ic_stop);
                    item.setTitle(R.string.Pause);

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
                    item.setTitle(R.string.Resume);
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
                //Stop recording
                mCapture.setRunning(false);
                xVal = 0;
                staff.notes.clear();
                recordItem.setTitle(R.string.Record);
                recordItem.setIcon(R.drawable.ic_play_arrow);
                isClicked = false;
                demoLoopCounter = 0;
                drawView.startNew();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}