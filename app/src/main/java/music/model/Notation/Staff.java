package music.model.Notation;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import music.model.Notation.MusicalSymbols.*;
import projectsaeje.AudioHandler;
import projectsaeje.MainActivity;


public class Staff extends Notation {

    private String fileName;
    private ArrayList<Notation> currentMeasures;
    public Activity activity;
    public int currentMeasureNum = 0;


    public Staff() {
        super(new ArrayList<Notation>());
    }

    public Staff(ArrayList<Notation> measures) {
        super(measures);
    }

    public void moveUpCurrentMeasures(Context context) {
        if (this.getNumElements() > 2) {
            if (currentMeasureNum < this.getNumElements()) {
                for (Notation element : currentMeasures.get(0).getElements()) {
                    MusicalSymbol aNote = (MusicalSymbol) element;
                    aNote.setScaledBitmapToNull();
                }
                currentMeasures.remove(0);
                currentMeasureNum += 1;
                Measure aMeasure = (Measure) this.getElements().get(currentMeasureNum - 1);
                for (Notation element : aMeasure.getElements()) {
                    MusicalSymbol aNote = (MusicalSymbol) element;
                    new AudioHandler().rebuildNote(aNote, context);
                }
                currentMeasures.add(1, aMeasure);
            }
        }
    }

    public void moveBackCurrentMeasures(Context context) {
        if (this.getNumElements() > 2) {
            if (currentMeasureNum > 2) {
                for (Notation element : currentMeasures.get(1).getElements()) {
                    MusicalSymbol aNote = (MusicalSymbol) element;
                    aNote.setScaledBitmapToNull();
                }
                currentMeasures.remove(1);
                currentMeasureNum -= 1;
                Measure aMeasure = (Measure) this.getElements().get(currentMeasureNum - 2);
                for (Notation element : aMeasure.getElements()) {
                    MusicalSymbol aNote = (MusicalSymbol) element;
                    new AudioHandler().rebuildNote(aNote, context);
                }
                currentMeasures.add(0, aMeasure);
            }
        }
    }

    public void setCurrentMeasures() {
        currentMeasureNum = this.getNumElements();
        if (this.getElements().isEmpty()){
            currentMeasures = new ArrayList<Notation>();
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
                    MusicalSymbol aNote = (MusicalSymbol)(element);
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
        currentMeasureNum += 1;
    }
    public void addMeasure(int placement, Measure newMeasure) {
        getElements().add(placement, newMeasure);
        currentMeasureNum += 1;
    }

    @Override
    public void clear() {
        super.clear();
        //this.setElements(new ArrayList<Notation>());
        setCurrentMeasures();
        currentMeasureNum = 0;
    }
}
