package graphics;

/**
 * Created by austinnash on 11/5/15.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.widget.Toast;

import com.example.woodev01.projectsaeje.R;

import java.io.FileOutputStream;
import java.io.IOException;

public class Note extends Activity {

    public float x, y; //for drawing to the 2D canvas in the UI/display, y represents a piano note value 1-88
    public String type; //"whole", "half", "quarter", "eighth", "sixteenth"...
    public int imageID;
    public Bitmap image; //An image of this type of note

    public Note() {
    }

    public Note(float y, String type) {
        this.x = 1;
        this.y = y;
        this.type = type;
        this.imageID = R.drawable.quarter;    //An integer representation of dial image. Use with getDrawable(ImageID) in draw function to draw to canvas
    }

    public void draw(Canvas canvas) {
        image = BitmapFactory.decodeResource(getResources(), this.imageID);

    }

    public void save(String fileName) {
        String noteVal;

        noteVal = String.valueOf(this.x);
        noteVal += String.valueOf(this.y);
        noteVal += String.valueOf(this.type);
        noteVal += String.valueOf(this.imageID);

        FileOutputStream fos = null;
        try{
            fos = openFileOutput(fileName, this.MODE_PRIVATE);
            fos.write(noteVal.getBytes()); //turns the Note String into a group of bits
            fos.close();

        } catch (IOException e){
            Toast.makeText(this, "There's a problem saving to the internal file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void updateYValue(float freq) {
        double logCalcX = Math.log(freq/440);
        double logCalcY = Math.log(2);

        int note = (int)(12 * (logCalcX + 49)/logCalcY);
        this.y = note;
    }
}
