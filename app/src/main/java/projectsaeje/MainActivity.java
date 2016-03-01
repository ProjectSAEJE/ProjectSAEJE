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

import audio.*;
import graphics.*;
import music.controller.*;
import music.model.*;

public class MainActivity extends Activity {

    private CaptureThread mCapture;
    private Boolean isClicked = false;
    public static Staff staff;
    public static DrawingView drawView;
    public static Integer xVal = 0;
    public static Integer demoLoopCounter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = (DrawingView)findViewById(R.id.drawing);

        AudioHandler.populateArrays();
        //this.staff = new Staff();

        //Note note = new Note(250, 0, "quarter",this);
        //ArrayList<Note> notes = new ArrayList<>();
        //notes.add(note);

        //staff = new Staff(notes, this);
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
        AudioHandler.update(freq);
    }

    private MenuItem recordItem;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:
                if (!isClicked) {
                    Handler mHandler;
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
                staff.measures.clear();
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