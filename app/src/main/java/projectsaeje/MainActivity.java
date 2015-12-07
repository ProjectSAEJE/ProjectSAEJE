package projectsaeje;

import android.app.Activity;
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
import graphics.Note;
import graphics.Staff;

public class MainActivity extends Activity {

    private CaptureThread mCapture;
    private Handler mHandler;
    private Boolean isClicked = false;
    public static Staff staff;
    private DrawingView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = (DrawingView)findViewById(R.id.drawing);
        this.staff = new Staff();

        Note note = new Note(250,"quarter",this,drawView);
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
        Note exampleNote = staff.notes.get(0);
        int screenNoteNumber = exampleNote.updateYValue(freq)%12;

        switch (screenNoteNumber) {
            case 0:
                exampleNote.y = (drawView.drawCanvas.getHeight())/3-225;
                break;
            case 1:
                exampleNote.y = 10000;
                break;
            case 2:
                exampleNote.y = 10000;
                break;
            case 3:
                exampleNote.y = 10000;
                break;
            case 4:
                exampleNote.y = 10000;
                break;
            case 5:
                exampleNote.y = 10000;
                break;
            case 6:
                exampleNote.y = 10000;
                break;
            case 7:
                exampleNote.y = 10000;
                break;
            case 8:
                exampleNote.y = 10000;
                break;
            case 9:
                exampleNote.y = 10000;
                break;
            case 10:
                exampleNote.y = drawView.drawCanvas.getHeight()/2-225;
                break;
            case 11:
                exampleNote.y = (drawView.drawCanvas.getHeight())/3-225;
                break;
            default:
                break;
        }

        exampleNote.x += 20;

        drawView.draw(drawView.drawCanvas);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:
                if (!isClicked) {

                    //set record icon to stop icon
                    item.setIcon(R.drawable.ic_stop);

                    mHandler = new Handler() {
                        @Override
                        public void handleMessage(Message m) {
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
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}