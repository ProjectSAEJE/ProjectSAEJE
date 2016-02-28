package music.model;

import android.app.Activity;

import java.util.ArrayList;


public class Staff {

    private String fileName;
    public Activity activity;

    public ArrayList<Measure> measures;

    public Staff() {}

    public Staff(ArrayList<Measure> measures, Activity activity){
        this.measures = measures;
        this.activity = activity;
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
