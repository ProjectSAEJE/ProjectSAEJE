package music.model;

import android.graphics.Bitmap;

public abstract class MusicalSymbol {

    private Bitmap scaledBitmap;
    private int rhythmicValue;

    /* Format for rythmicValue integer:
        0: sixteenth note
        1: eighth note
        3: quarter note
        7: half note
        15: whole note
     */


    public MusicalSymbol(Bitmap scaledBitmap, int rhythmicValue) {
        this.rhythmicValue = rhythmicValue;
        this.scaledBitmap = scaledBitmap;
    }

    public Bitmap getScaledBitmap() {
        return this.scaledBitmap;
    }

    public int getRhythmicValue() {
        return this.rhythmicValue;
    }




}
