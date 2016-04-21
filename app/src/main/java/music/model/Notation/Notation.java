package music.model.Notation;

import java.util.ArrayList;

public abstract class Notation {

    private ArrayList<Notation> elements;

    public Notation() {
        //elements = new ArrayList<>();
    }

    public Notation(ArrayList<Notation> elements){
        this.elements = elements;
    }

    public ArrayList<Notation> getElements(){
        return this.elements;
    }

    public void clear() { this.elements = new ArrayList<>(); }

    public int getNumElements() {
        return elements.size();
    }

}
