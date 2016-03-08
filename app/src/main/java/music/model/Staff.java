package music.model;

import android.app.Activity;

import java.lang.reflect.Array;
import java.util.ArrayList;


import music.model.*;


public class Staff {

    private String fileName;
    private ArrayList<Measure> currentMeasures;
    public Activity activity;

    public ArrayList<Measure> measures;

    public Staff() {}

    public Staff(ArrayList<Measure> measures){
        this.measures = measures;
    }

    public void moveUpCurrentMeasures() {
        this.currentMeasures.remove(0);
        this.currentMeasures.add(1, this.measures.get(-1));
    }

    public void moveBackCurrentMeasures() {
        this.currentMeasures.remove(-1);
        this.currentMeasures.add(0, this.measures.get(-2));
    }

    public void setCurrentMeasures() {
        this.currentMeasures = this.measures;

        ArrayList<Note> blankArray = new ArrayList<>();
        Measure newMeasure = new Measure(2, blankArray);

        this.currentMeasures.set(1, newMeasure);
    }

    public ArrayList<Measure> getCurrentMeasures() {
        return this.currentMeasures;
    }

    public void addMeasure(Measure newMeasure) {
        measures.add(newMeasure);
    }

//    public void save(){
//        SaveDialogBuilder builder = new SaveDialogBuilder(this.activity);
//        fileName = builder.showDialogAndReturnFileName();
//
//        for (Note aNote: this.notes) {
//            aNote.save(fileName);
//        }
//    }
}
