package projectsaeje;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Handler;
import android.os.Message;

import android.content.res.Resources;
import android.content.Context;

import com.example.woodev01.projectsaeje.R;

import java.util.ArrayList;

import audio.*;
import graphics.*;
import music.controller.*;
import music.model.*;

public class MainActivity extends Activity {

    private static Context context;



    private Boolean isClicked = false;
    public static DrawingView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.context = getApplicationContext();
        setContentView(R.layout.activity_main);

        drawView = (DrawingView)findViewById(R.id.drawing);

        AudioHandler.populateArrays();

    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AudioHandler.destroy();
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

    private MenuItem recordItem;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:
                if (!isClicked) {

                    recordItem = item;
                    item.setIcon(R.drawable.ic_stop);   //set record icon to stop icon
                    item.setTitle(R.string.Pause);

                    AudioHandler.captureNotes();
                    /*
                    ArrayList<Integer> timeSig = new ArrayList<Integer>();

                    timeSig.add(4);
                    timeSig.add(4);

                    Metronome metronome = new Metronome(120, timeSig, true, this);
                    */
                    isClicked = true;
                }
                else {

                    //changes stop icon back to play icon on the record button
                    item.setIcon(R.drawable.ic_play_arrow);
                    item.setTitle(R.string.Resume);
                    AudioHandler.stopCapture();
                    isClicked = false;
                }

                return true;

            case R.id.open:

                return true;

            case R.id.save:

                //staff.save();

                return true;

            case R.id.clear:
                //Stop recording
                AudioHandler.stopCapture();
                recordItem.setTitle(R.string.Record);
                recordItem.setIcon(R.drawable.ic_play_arrow);
                isClicked = false;
                drawView.startNew();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}