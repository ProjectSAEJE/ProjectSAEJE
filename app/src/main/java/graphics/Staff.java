package graphics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import graphics.Note;
import file.SaveDialogBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by woodev01 on 11/3/15.
 */

public class Staff {

    private String fileName;
    public Activity activity;

    public ArrayList<Note> notes; //*
    //* next: implement public ArrayList<Measure> measures instead of notes ---  each Measure object in measures contains its own array list of Notes

    public Staff() {}

    public Staff(ArrayList<Note> notes, Activity activity){
        this.notes = notes;
        this.activity = activity;
    }

    public void addNote(Note newNote) {
        notes.add(newNote);
    }

    public void save(){
        SaveDialogBuilder builder = new SaveDialogBuilder(this.activity);
        fileName = builder.showDialogAndReturnFileName();

        for (Note aNote: this.notes) {
            aNote.save(fileName);
        }
    }
}
