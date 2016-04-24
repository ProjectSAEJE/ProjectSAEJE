package music.model.Notation.MusicalSymbols;

import android.graphics.Bitmap;
import music.model.Notation.*;

public abstract class MusicalSymbol extends Notation {

    private Bitmap scaledBitmap;
    private int rhythmicValue;
    private String name;
    private int tonalValue; //represents a piano note value 0-87


    /* Format for rythmicValue integer:
        0: sixteenth note
        1: eighth note
        3: quarter note
        7: half note
        15: whole note
     */

    public MusicalSymbol(Bitmap scaledBitmap, int rhythmicValue, int tonalValue) {
        this.rhythmicValue = rhythmicValue;
        this.scaledBitmap = scaledBitmap;
        this.name = "";
        this.tonalValue = tonalValue;
    }

    public MusicalSymbol(Bitmap scaledBitmap, int rhythmicValue, String name, int tonalValue) {
        this.rhythmicValue = rhythmicValue;
        this.scaledBitmap = scaledBitmap;
        this.name = name;
        this.tonalValue = tonalValue;
    }

    public Bitmap getScaledBitmap() { return this.scaledBitmap; }

    public int getRhythmicValue() {
        return this.rhythmicValue;
    }
    public int getTonalValue() { return this.tonalValue; }

    public String getName() { return this.name; }
    public void setName(String newName) { this.name = newName; }
}
