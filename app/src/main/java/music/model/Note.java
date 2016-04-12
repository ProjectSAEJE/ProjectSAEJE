package music.model;

import android.graphics.Bitmap;

public class Note extends MusicalSymbol{


    private int tonalValue; //represents a piano note value 0-87

    public Note(int tonalValue, Bitmap scaledBitmap, int rhythmicValue) {
        super(scaledBitmap, rhythmicValue);
        this.tonalValue = tonalValue;
    }

    public int getTonalValue() {
        return this.tonalValue;
    }
}

