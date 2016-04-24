package music.model.Notation.MusicalSymbols;

import android.graphics.Bitmap;


public class Rest extends MusicalSymbol {

    public Rest(Bitmap scaledBitmap, int rhythmicValue) {
        super(-1, scaledBitmap, rhythmicValue);
    }

    public Rest(Bitmap scaledBitmap, int rhythmicValue, String name) {
        super(-1, scaledBitmap, rhythmicValue, name);

    }
}
