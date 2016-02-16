package file;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

public class SaveDialogBuilder extends AlertDialog.Builder {

    //THIS part belongs in a class which will call the save builder... in theory

    //    public void save(String fileName) {
//        String noteVal;
//
//        noteVal = String.valueOf(this.x);
//        noteVal += String.valueOf(this.y);
//        noteVal += String.valueOf(this.type);
//        noteVal += String.valueOf(this.imageID);
//
//        FileOutputStream fos = null;
//        try {
//            fos = myActivity.openFileOutput(fileName, this.myActivity.MODE_PRIVATE);
//            fos.write(noteVal.getBytes()); //turns the Note String into a group of bits
//            fos.close();
//
//        } catch (IOException e) {
//            Toast.makeText(myActivity, "There's a problem saving to the internal file", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//    }

    private Activity activity;
    public String fileName;

    public SaveDialogBuilder(Activity activity) {
        super(activity);
    }

    public String showDialogAndReturnFileName() {

        this.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(this.activity);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        this.setView(input);

        // Set up the buttons
        this.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fileName = input.getText().toString();
            }
        });
        this.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        this.show();

        return fileName;
    }

}
