package music.model.Notation.MusicalSymbols;

import android.graphics.Bitmap;

public class Note extends MusicalSymbol {


    public Note(int tonalValue, Bitmap scaledBitmap, int rhythmicValue) {
        super(tonalValue, scaledBitmap, rhythmicValue);
    }

    public Note(int tonalValue, Bitmap scaledBitmap, int rhythmicValue, String name) {
        super(tonalValue, scaledBitmap, rhythmicValue, name);
    }
}

