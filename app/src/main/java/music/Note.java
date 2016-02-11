package music;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.woodev01.projectsaeje.R;

import java.io.FileOutputStream;
import java.io.IOException;

import projectsaeje.MainActivity;

public class Note {


    public float x, y; //for drawing to the 2D canvas in the UI/display, y represents a piano note value 1-88
    public String type; //"whole", "half", "quarter", "eighth", "sixteenth"...
    public int imageID;
    public Bitmap image; //An image of this type of note
    public MainActivity myActivity;


    public Note(float y, float x, String type, MainActivity myActivity) {
        this.x = x;
        this.y = y;
        this.type = type;

        Bitmap b = BitmapFactory.decodeResource(myActivity.getResources(), R.drawable.ic_quarter_note);

        //Creates a ScaledBitmap to make quarter note a certain size
        this.image = Bitmap.createScaledBitmap(b, 300, 300, false);
    }

    public void save(String fileName) {
        String noteVal;

        noteVal = String.valueOf(this.x);
        noteVal += String.valueOf(this.y);
        noteVal += String.valueOf(this.type);
        noteVal += String.valueOf(this.imageID);

        FileOutputStream fos = null;
        try {
            fos = myActivity.openFileOutput(fileName, this.myActivity.MODE_PRIVATE);
            fos.write(noteVal.getBytes()); //turns the Note String into a group of bits
            fos.close();

        } catch (IOException e) {
            Toast.makeText(myActivity, "There's a problem saving to the internal file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /*
    Tentative load code, based on the code from the following website:
    http://www.tutorialspoint.com/android/android_internal_storage.htm
    Better code probably exists for this, I'll do what I can to find it
    as soon as possible.

    public void load(String fileName) {

       FileInputStream fin = openFileInput(fileName);
       int c;
       String temp="";
       while( (c = fin.read()) != -1){
       temp = temp + Character.toString((char)c);
       }
       //string temp contains all the data of the file.
       fin.close();


       Idea: view saved files, input a file name & it'll load the file?

    }

    //rough sketch of what delete should do; code will come later
    public void delete(String fileName){

        input file name
       if file name equals file:
           delete it
       else:
           Toast.makeTest([text input] + " was not found, so it can't be deleted!", Toast.LENGTH_SHORT).show();

         //Idea #2:

       input file name
       try{
           delete it
       }catch (IOException e){
          Toast.makeText([text input] + " was not found, so it can't be deleted!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
       }

       } */

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

}
