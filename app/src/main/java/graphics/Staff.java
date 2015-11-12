package graphics;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by woodev01 on 11/3/15.
 */

public class Staff extends Activity {


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
