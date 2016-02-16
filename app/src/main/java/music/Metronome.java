package music;

/**
 * Created by austinnash on 2/11/16.
 */

//Issue: This class needs to either have reference to an application loop (for keeping time) or run its own thread.

public class Metronome {

    private int bpm;
    private int[] timeSignature;
    private Boolean subdivide;
    private Boolean running;

    public Metronome(int bpm, int[] timeSignature, Boolean subdivide) {
        this.bpm = bpm;
        this.timeSignature = timeSignature;
        this.subdivide = subdivide;
        this.running = false;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }
}
