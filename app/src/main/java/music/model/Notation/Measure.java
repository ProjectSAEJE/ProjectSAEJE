package music.model.Notation;

import java.util.ArrayList;

import music.model.Notation.MusicalSymbols.MusicalSymbol;
import music.model.Notation.MusicalSymbols.Note;

public class Measure extends Notation {
    //public int measureNumber; //starts as 1 not 0 per music composition norms
    private int numBeats;     //total number of beats per measure   3 \_  3/8 time
    private int beats;        //note value that gets the beat       8 /

    public Measure() {}

    public Measure(ArrayList<Notation> notes, int numBeats, int beats) {
        super(notes);
        this.numBeats = numBeats;
        this.beats = beats;
    }

    public void addNote(Note note) {
        this.getElements().add(note);
    }

    public void setBeats(int beat){
        this.beats = beat;
    }
    public void setNumBeats(int numBeats) {
        this.numBeats = numBeats;
    }

    public int valueTilMeasureFull() {
        int acc = 0;
        int target = (16/this.beats)*this.numBeats;

        for(Notation item: this.getElements()) {
            MusicalSymbol nItem = (MusicalSymbol)(item);
            acc += nItem.getRhythmicValue();
        }

        return ((target - acc) - 1); //The minus 1 converts it from 1-16 to 0-15 to be in line with the RhythmicValue in AudioHandler
    }

    @Override
    public String toString() {
        String acc = "";
        for(Notation item: this.getElements()) {
            MusicalSymbol nItem = (MusicalSymbol)(item);
            acc = acc + "," + nItem.getName();
        }
        return acc;
    }
}