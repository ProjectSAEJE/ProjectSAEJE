package com.example.woodev01.projectsaeje;

        import android.app.Activity;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;

        import android.widget.TextView;
        import audio.CaptureThread;
        import graphics.DailView.DialFace.DialView;

public class MainActivity extends Activity {
    private DialView dial;
    private TextView t;
    //private CaptureTask capture;
    private float targetFrequency;
    private CaptureThread mCapture;
    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        mHandler = new Handler() {
            @Override
            public void handleMessage(Message m) {
                updateDisplay(m.getData().getFloat("Freq"));
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

}