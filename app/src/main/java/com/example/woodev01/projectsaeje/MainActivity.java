package com.example.woodev01.projectsaeje;
//hello
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import audio.CaptureThread;
import graphics.Note;
import graphics.Staff;


public class MainActivity extends Activity {

    private CaptureThread mCapture;
    private Handler mHandler;
    private Boolean isClicked = false;
    private Staff staff;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        staff = new Staff();
        staff.addNote(new Note(0, "quarter"));  //This initiates a new note on a freq 0, so that we can change the y freq
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCapture != null) {
            mCapture.setRunning(false);
            mCapture = null;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        mCapture.setRunning(false);
    }

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
        exampleNote.updateYValue(freq);

        exampleNote.draw(ourCanvas);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:
                if (!isClicked) {
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
                return true;

            case R.id.open:

                return true;

            case R.id.save:

                staff.save();

                return true;

            case R.id.clear:

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

}