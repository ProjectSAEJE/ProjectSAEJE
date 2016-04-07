package music.model;

import android.app.Activity;

import android.os.Parcelable;
import android.os.Parcel;

import java.lang.reflect.Array;
import java.util.ArrayList;


import music.model.*;


public class Staff{

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
        this.currentMeasures.remove(0);
        this.currentMeasures.add(1, this.measures.get(this.measures.size() - 1));
    }

    public void moveBackCurrentMeasures() {
        this.currentMeasures.remove(1);
        this.currentMeasures.add(0, this.measures.get(this.measures.size() - 2));
    }

    public void makeStartingCurrentMeasures() {
        this.currentMeasures = this.measures;

        ArrayList<Note> blankArray = new ArrayList<>();
        Measure newMeasure = new Measure(blankArray, 4, 4);

        this.currentMeasures.add(newMeasure);
        this.currentMeasures.add(newMeasure);
    }

    public void setCurrentMeasures() {
        if (this.measures.isEmpty()){
            makeStartingCurrentMeasures();
        } else if(this.measures.size() == 1) {
            ArrayList<Measure> mostRecentMeasures = new ArrayList<>();
            mostRecentMeasures.add(0, this.measures.get(0));
            mostRecentMeasures.add(1, new Measure(new ArrayList<Note>(), 4, 4));
            this.currentMeasures = mostRecentMeasures;
        } else {
            ArrayList<Measure> mostRecentMeasures = new ArrayList<>();
            mostRecentMeasures.add(0, this.measures.get(this.measures.size() - 2));
            mostRecentMeasures.add(1, this.measures.get(this.measures.size() - 1));
            this.currentMeasures = mostRecentMeasures;
        }
    }

    public ArrayList<Measure> getCurrentMeasures() {
        return this.currentMeasures;
    }

    public void addMeasure(Measure newMeasure) {
        measures.add(newMeasure);
    }
}
