package com.example.woodev01.projectsaeje;

        import android.app.Activity;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.woodev01.projectsaeje.R;

        import audio.CaptureTask;
        import audio.CaptureThread;
        import graphics.DailView.DialFace.DialSurfaceView;
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
        dial = (DialView) findViewById(R.id.dial);
        t = (TextView) findViewById(R.id.textView1);

        updateTargetFrequency(); // Get radio button selection

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

        updateTargetFrequency(); // Get radio button selection

    }


    private void updateTargetFrequency() {
        // Grab the selected radio button tag.
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        int selected = rg.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(selected);
        targetFrequency = Float.parseFloat((String)rb.getTag());
    }

    public void updateDisplay(float frequency) {
        // Calculate difference between target and measured frequency,
        // given that the measured frequency can be a factor of target.
        float difference = 0;
        if (frequency > targetFrequency) {
            int divisions = (int) (frequency / targetFrequency);
            float modified = targetFrequency * (float) divisions;
            if (frequency - modified > targetFrequency / 2) {
                modified += targetFrequency;
                divisions++;
            }
            difference = (frequency - modified) / (float) divisions;
        } else {
            // If target is greater than measured, just use difference.
            difference = frequency - targetFrequency;
        }

        float relativeFrequency = targetFrequency + difference;

        // Update TextView
        if (relativeFrequency < 1000f)
            t.setText(String.format("%.1f Hz", relativeFrequency));
        else
            t.setText(String.format("%.2f kHz", relativeFrequency/1000));

        // Update DialView
        float value = difference / (targetFrequency / 2) * 90;
        dial.update(value);
    }

    public void onRadioButtonClicked(View v) {
        // Perform action on clicks
        RadioButton rb = (RadioButton) v;
        Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
        targetFrequency = Float.parseFloat((String)rb.getTag());
    }
}