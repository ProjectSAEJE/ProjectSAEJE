package music.model.Notation.MusicalSymbols;

import android.graphics.Bitmap;

public class Note extends MusicalSymbol {

    public Note(int tonalValue, Bitmap scaledBitmap, int rhythmicValue) {
        super(scaledBitmap, rhythmicValue, tonalValue);
    }

    public Note(int tonalValue, Bitmap scaledBitmap, int rhythmicValue, String name) {
        super(scaledBitmap, rhythmicValue, name, tonalValue);
    }

}

