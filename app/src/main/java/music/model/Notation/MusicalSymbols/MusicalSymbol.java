package music.model.Notation.MusicalSymbols;

import android.graphics.Bitmap;
import music.model.Notation.*;

public abstract class MusicalSymbol extends Notation {

    private Bitmap scaledBitmap;
    private int rhythmicValue;
    private String name;

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
        this.name = "";
    }

    public MusicalSymbol(Bitmap scaledBitmap, int rhythmicValue, String name) {
        this.rhythmicValue = rhythmicValue;
        this.scaledBitmap = scaledBitmap;
        this.name = name;
    }

    public Bitmap getScaledBitmap() { return this.scaledBitmap; }

    public int getRhythmicValue() {
        return this.rhythmicValue;
    }

    public String getName() { return this.name; }
    public void setName(String newName) { this.name = newName; }
}
