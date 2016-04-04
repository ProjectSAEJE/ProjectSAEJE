package projectsaeje;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

import com.example.woodev01.projectsaeje.R;

import graphics.*;
import music.model.*;

public class MainActivity extends Activity {

    public static Staff staff;

    private Boolean isClicked = false;
    public static DrawingView drawView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.staff = new Staff();

        drawView = (DrawingView)findViewById(R.id.drawing);
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

                item.setIcon(R.drawable.ic_stop);   //set record icon to stop icon
                item.setTitle(R.string.Pause);

                Intent intent = new Intent(this, AudioHandler.class);
                startActivity(intent);

                return true;

            case R.id.open:

                return true;

            case R.id.save:

                //staff.save();

                return true;

            case R.id.clear:
                isClicked = false;
                //clear the staff
                drawView.startNew();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}