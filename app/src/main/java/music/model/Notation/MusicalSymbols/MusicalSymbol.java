package music.model.Notation.MusicalSymbols;

import android.graphics.Bitmap;
import music.model.Notation.*;
import projectsaeje.AudioHandler;

public abstract class MusicalSymbol extends Notation {

    private Bitmap scaledBitmap;
    private int tonalValue;
    private int rhythmicValue;
    private String name;

    /* Format for rythmicValue integer:
        0: sixteenth note
        1: eighth note
        3: quarter note
        7: half note
        15: whole note
     */

    public MusicalSymbol(int tonalValue, Bitmap scaledBitmap, int rhythmicValue) {
        this.tonalValue = tonalValue;
        this.rhythmicValue = rhythmicValue;
        this.scaledBitmap = scaledBitmap;
        this.name = "";
    }

    public MusicalSymbol(int tonalValue, Bitmap scaledBitmap, int rhythmicValue, String name) {
        this.tonalValue = tonalValue;
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

    public int getTonalValue() {
        return this.tonalValue;
    }

    public void setScaledBitmapToNull() {
        this.scaledBitmap = null;
    }

    public void setScaledBitmap(Bitmap aBitmap) {
        aBitmap = Bitmap.createScaledBitmap(aBitmap, 300, 300, false);
        this.scaledBitmap = aBitmap;
    }
}
