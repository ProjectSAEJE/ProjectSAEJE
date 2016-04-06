package music.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Measure {
    //public int measureNumber; //starts as 1 not 0 per music composition norms
    public ArrayList<Note> notes;
    private int numBeats;     //total number of beats per measure   3 \_  3/8 time
    private int beats;        //note value that gets the beat       8 /

    public Measure() {}

    public Measure(/*int measureNumber,*/ ArrayList<Note> notes, int numBeats, int beats) {
        //this.measureNumber = measureNumber;
        this.notes = notes;
        this.numBeats = numBeats;
        this.beats = beats;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void setBeats(int beat){
        this.beats = beat;
    }
    public void setNumBeats(int numBeats){
        this.numBeats = numBeats;
    }

    public int valueTilMeasureFull() {
        int acc = 0;
        int target = (16/this.beats)*this.numBeats;

        for(Note item: this.notes) {
            acc += item.rhythmicValue;
        }

        return ((target - acc) - 1); //The minus 1 converts it from 1-16 to 0-15 to be in line with the RhythmicValue in AudioHandler
    }

    public void clear() {
        this.notes = new ArrayList<>();
    }

    @Override
    public String toString() {
        String acc = "";
        for(Note item: this.notes) {
            acc = acc + Integer.toString(item.tonalValue);
        }
        return acc;
    }
}
