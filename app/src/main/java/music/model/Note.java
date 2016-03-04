package music.model;

import android.graphics.Bitmap;

public class Note {


    public int tonalValue; //represents a piano note value 0-87
    public Bitmap scaledBitmap; //Note's image


    public Note(int tonalValue, Bitmap scaledBitmap) {
        this.tonalValue = tonalValue;
        this.scaledBitmap = scaledBitmap;
    }
}

