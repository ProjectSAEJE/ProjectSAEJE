package music.model.Notation.MusicalSymbols;

import android.graphics.Bitmap;


public class Rest extends MusicalSymbol {

    public Rest(Bitmap scaledBitmap, int rhythmicValue) {
        super(0, scaledBitmap, rhythmicValue);
    }

    public Rest(Bitmap scaledBitmap, int rhythmicValue, String name) {
        super(0, scaledBitmap, rhythmicValue, name);

    }
}
