package music.model.Notation;

import android.app.Activity;

import java.util.ArrayList;

import music.model.Notation.MusicalSymbols.*;
import projectsaeje.AudioHandler;


public class Staff extends Notation {

    private String fileName;
    private ArrayList<Notation> currentMeasures;
    public Activity activity;


    public Staff() {
        super(new ArrayList<Notation>());
    }

    public Staff(ArrayList<Notation> measures) {
        super(measures);
    }

    public void moveUpCurrentMeasures() {
        for(Notation element: currentMeasures.get(0).getElements()) {
            Note aNote = (Note) element;
            aNote.setScaledBitmapToNull();
        }
        currentMeasures.remove(0);
        Measure aMeasure = (Measure) this.getElements().get(this.getNumElements() - 1);
        for(Notation element: aMeasure.getElements()) {
            Note aNote = (Note) element;
            new AudioHandler().rebuildNote(aNote);
        }
        currentMeasures.add(1, aMeasure);
    }

    public void moveBackCurrentMeasures() {
        for(Notation element: currentMeasures.get(1).getElements()) {
            Note aNote = (Note) element;
            aNote.setScaledBitmapToNull();
        }
        currentMeasures.remove(1);
        Measure aMeasure = (Measure) this.getElements().get(this.getNumElements() - 2);
        for(Notation element: aMeasure.getElements()) {
            Note aNote = (Note) element;
            new AudioHandler().rebuildNote(aNote);
        }
        currentMeasures.add(0, aMeasure);
    }

    public void makeStartingCurrentMeasures() {
        currentMeasures = this.getElements();

        ArrayList<Notation> blankArray = new ArrayList<>();
        Measure newMeasure = new Measure(blankArray, 4, 4);

        currentMeasures.add(newMeasure);
        currentMeasures.add(newMeasure);
    }

    public void setCurrentMeasures() {
        if (this.getElements().isEmpty()){
            makeStartingCurrentMeasures();
            //Log.d("Testing...", this.measures.toString());
        } else if(getElements().size() == 1) {
            ArrayList<Notation> mostRecentMeasures = new ArrayList<>();
            mostRecentMeasures.add(0, this.getElements().get(0));
            mostRecentMeasures.add(1, new Measure(new ArrayList<Notation>(), 4, 4));
            currentMeasures = mostRecentMeasures;
            //Log.d("Testing...1", this.measures.toString());
        } else {
            ArrayList<Notation> mostRecentMeasures = new ArrayList<>();
            mostRecentMeasures.add(0, getElements().get(getElements().size() - 2));
            mostRecentMeasures.add(1, getElements().get(getElements().size() - 1));
            currentMeasures = mostRecentMeasures;

            if(getElements().size() > 2) {
                for(Notation element: getElements().get(getElements().size() - 3).getElements()) {
                    Note aNote = (Note)(element);
                    aNote.setScaledBitmapToNull();
                }
            }
            //Log.d("Testing...2", this.measures.toString());
        }
    }

    public ArrayList<Notation> getCurrentMeasures() {
        return this.currentMeasures;
    }

    public void addMeasure(Measure newMeasure) {
        getElements().add(newMeasure);
    }
    public void addMeasure(int placement, Measure newMeasure) { getElements().add(placement, newMeasure); }

    @Override
    public void clear() {
        super.clear();
        //this.setElements(new ArrayList<Notation>());
        setCurrentMeasures();
    }
}
