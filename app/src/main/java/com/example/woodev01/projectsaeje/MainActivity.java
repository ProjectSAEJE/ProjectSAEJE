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

public class MainActivity extends Activity {

    private CaptureThread mCapture;
    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message m) {
               m.getData().getFloat("Freq");
            }
        };

        mCapture = new CaptureThread(mHandler);
        mCapture.setRunning(true);
        mCapture.start();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.record:

                break;
        }
        switch (item.getItemId()) {
            case R.id.open:

                break;
        }
        switch (item.getItemId()) {
            case R.id.save:

                break;
        }
        switch (item.getItemId()){
            case R.id.clear:

                break;
        }
        return true;
    }

}