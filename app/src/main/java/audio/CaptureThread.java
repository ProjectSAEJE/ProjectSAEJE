package audio;

import android.graphics.Paint;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder.AudioSource;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.woodev01.projectsaeje.R;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import music.model.Metronome;

public class CaptureThread extends Thread {
    private Handler mHandler;
    private boolean isRunning = false;
    private int newBeat = 0;
    private int oldBeat = 0;
    private Metronome metronome;

    public CaptureThread(Handler handler) {
        this.mHandler = handler;
    }


    public CaptureThread(Handler handler, Metronome metronome) {
        this.mHandler = handler;
        this.metronome = metronome;
    }


    public void setRunning(boolean b) {
        isRunning = b;
    }

    @Override
    public void run() {
        int sRate = 44100;
        int bufferSize = 65536;
        bufferSize = 32768;

        AudioRecord recorder = new AudioRecord(AudioSource.MIC,
                sRate, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, bufferSize);

        // Create storage container for read data.
        byte buffer[] = new byte[bufferSize];

        recorder.startRecording();
        //metronome.start();

        //oldBeat = metronome.getRpBeatNum();
        while(isRunning) {

            //int newBeat = metronome.getRpBeatNum();
            //System.out.println("oldBeat = " + oldBeat + ", newBeat = " + newBeat);
            /*if (newBeat != oldBeat) { //a new beat has taken place.
                playAudioTrack();
                System.out.println("clicked");
            }
            oldBeat = (new Integer(newBeat)).intValue();*/


            // Read stream data into buffer container.
            // TODO: Put divide by 16 back in maybe
            int bytesRead = recorder.read(buffer, 0, bufferSize);

            if (bytesRead > 0) {
                // Create frequency spectrum.
                Spectrum spectrum = new Spectrum(buffer, sRate);
                float frequency = spectrum.getFrequency();

                // Send frequency update to UI.
                Message msg = mHandler.obtainMessage();
                Bundle b = new Bundle();
                b.putFloat("Freq", frequency);
                msg.setData(b);
                mHandler.sendMessage(msg);
            }
        }

        recorder.stop();
        recorder.release();
    }

    public void playAudioTrack() {

        int sampleFreq = 16000;
        File file = new File("~/AndroidStudioProjects/ProjectSAEJE/app/src/main/res/raw/tamb_down_441.mp3");

        int shortSizeInBytes = Short.SIZE / Byte.SIZE;

        int minBufferSize = AudioTrack.getMinBufferSize(sampleFreq, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        int bufferSizeInBytes = (int)(file.length() / shortSizeInBytes);

        final AudioTrack at = new AudioTrack(AudioManager.STREAM_MUSIC, sampleFreq,
                AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufferSize,
                AudioTrack.MODE_STREAM);
        int i = 0;
        byte[] s = new byte[bufferSizeInBytes];

        try {
            final FileInputStream fin = new FileInputStream("~/AndroidStudioProjects/ProjectSAEJE/app/src/main/res/raw/tamb_down_441.mp3");
            final DataInputStream dis = new DataInputStream(fin);
            at.setNotificationMarkerPosition((int)(file.length() / 2));

            at.play();
            while ((i = dis.read(s, 0, bufferSizeInBytes)) > -1) {
                at.write(s, 0, i);

            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (Exception e) {

        }

    }
}