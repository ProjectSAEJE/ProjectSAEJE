package graphics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
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

    private String m_Text;

    private void NoteToString(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

        for (Note aNote: this.notes) {
            aNote.save(m_Text);
        }
    }

}
