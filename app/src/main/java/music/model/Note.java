package music.model;

import android.graphics.Bitmap;

public class Note {


    public int tonalValue; //represents a piano note value 0-87

    /* Format for rythmicValue integer:
        1: whole note
        2: half note
        3: triplet quarter note
        4: quarter note
        6: triplet eighth note
        8: eighth note
        12: triplet sixteenth note
        16: sixteenth note
     */
    public int rhythmicValue;

    public Bitmap scaledBitmap; //Note's image


    public Note(int tonalValue, Bitmap scaledBitmap, int rhythmicValue) {
        this.tonalValue = tonalValue;
        this.scaledBitmap = scaledBitmap;
        this.rhythmicValue = rhythmicValue;
    }
}

