package graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

import music.model.Note;

public class DisplayedNote extends Drawable {

    public Bitmap image;
    public int x;
    public int y;

    public DisplayedNote(Note aNote, Bitmap b, int i, int g) {
        x = i;
        y = g;
        //Creates a ScaledBitmap to make quarter note a certain size
        image = Bitmap.createScaledBitmap(b, 300, 300, false);
    }

    public void displayNote(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
