package music;

import android.app.Activity;

import music.Note;
import file.SaveDialogBuilder;

import java.util.ArrayList;


public class Staff {

    private String fileName;
    public Activity activity;

    public ArrayList<Note> notes;
    //* next: implement public ArrayList<Measure> measures instead of notes ---  each Measure object in measures contains its own array list of Notes

    public Staff() {}

    public Staff(ArrayList<Note> notes, Activity activity){
        this.notes = notes;
        this.activity = activity;
    }

    public void addNote(Note newNote) {
        notes.add(newNote);
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
