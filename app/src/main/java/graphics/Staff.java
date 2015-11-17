package graphics;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.View;
import android.widget.Toast;

import graphics.Note;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by woodev01 on 11/3/15.
 */


public class Staff extends Activity {

    public ArrayList<Note> notes;

    public Staff() {

    }

    public Staff(ArrayList<Note> notes){
        this.notes = notes;
    }


    private String NoteToString(){
        return String;
    }

    public void draw(Canvas canvas) {
        for (Note aNote : this.notes) {
            aNote.draw(canvas);
        }
    }

/*
    //saveData = data to be saved, saejeData = name of the internal file
    private void internalSave(String saveData, String saejeData){
        FileOutputStream fos = null;
        try{
            fos = openFileOutput(saejeData, this.MODE_PRIVATE);
            fos.write(saveData.getBytes());
            fos.close();

            preferencesEditor.putBoolean("isInternal", true).commit();

            buttonShowInternal.setVisibility(View.VISIBLE);
            buttonShowInternal.setOnClickListener(this);

        } catch (IOException e){
            Toast.makeText(this,"There's a problem saving to the internal file",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    /*
    String filename = "saeje_file";
    String string = "TESTING"; //replace with staff object
    FileOutputStream outputStream;

    try{
        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(string.getBytes());
        outputStream.close();
    } catch(Exception e){e.printStackTrace();} //change the Exception
    */

}




