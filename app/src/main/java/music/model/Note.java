package music.model;

import android.graphics.Bitmap;

public class Note {


    public int tonalValue; //represents a piano note value 0-87

    /* Format for rythmicValue integer:
        0: sixteenth note
        1: eighth note
        3: quarter note
        7: half note
        15: whole note
     */

    public int rhythmicValue;

    public Bitmap scaledBitmap; //Note's image


    public Note(int tonalValue, Bitmap scaledBitmap, int rhythmicValue) {
        this.tonalValue = tonalValue;
        this.scaledBitmap = scaledBitmap;
        this.rhythmicValue = rhythmicValue;
    }
}

