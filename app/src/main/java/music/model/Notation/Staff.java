package music.model.Notation;

import android.app.Activity;

import java.util.ArrayList;

import music.model.Notation.MusicalSymbols.*;


public class Staff extends Notation {

    private String fileName;
    private ArrayList<Measure> currentMeasures;
    public Activity activity;

    public ArrayList<Measure> measures;

    public Staff() {
    }

    public Staff(ArrayList<Measure> measures) {
        this.measures = measures;
    }

    public void moveUpCurrentMeasures() {
        currentMeasures.remove(0);
        currentMeasures.add(1, measures.get(measures.size() - 1));
    }

    public void moveBackCurrentMeasures() {
        currentMeasures.remove(1);
        currentMeasures.add(0, measures.get(measures.size() - 2));
    }

    public void makeStartingCurrentMeasures() {
        currentMeasures = measures;

        ArrayList<Notation> blankArray = new ArrayList<>();
        Measure newMeasure = new Measure(blankArray, 4, 4);

        currentMeasures.add(newMeasure);
        currentMeasures.add(newMeasure);
    }

    public void setCurrentMeasures() {

        if (this.measures.isEmpty()){
            makeStartingCurrentMeasures();

            //Log.d("Testing...", this.measures.toString());

        } else if(measures.size() == 1) {

            ArrayList<Measure> mostRecentMeasures = new ArrayList<>();
            mostRecentMeasures.add(0, measures.get(0));
            mostRecentMeasures.add(1, new Measure(new ArrayList<Notation>(), 4, 4));
            currentMeasures = mostRecentMeasures;

            //Log.d("Testing...1", this.measures.toString());

        } else {

            ArrayList<Measure> mostRecentMeasures = new ArrayList<>();
            mostRecentMeasures.add(0, measures.get(measures.size() - 2));
            mostRecentMeasures.add(1, measures.get(measures.size() - 1));
            currentMeasures = mostRecentMeasures;

            if(measures.size() > 2) {
                for(Notation element: measures.get(measures.size() - 3).getElements()) {
                    Note aNote = (Note)(element);
                    aNote.setScaledBitmapToNull();
                }
            }
            //Log.d("Testing...2", this.measures.toString());

        }
    }

    public ArrayList<Measure> getCurrentMeasures() {
        return this.currentMeasures;
    }

    public void addMeasure(Measure newMeasure) {
        measures.add(newMeasure);
    }
}
