package graphics;

/**
 * Created by austinnash on 11/5/15.
 */

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;  // May or may not be pseudocode depending on what Drawable actually is, might be too abstract

public class Note extends Drawable { // As above, may be better to extend from an subclass of Drawable that supports images (for the notes, quarter, eighth, etc.)

    public float x, y; //for drawing to the 2D canvas in the UI/display
    public String type; //"whole", "half", "quarter", "eighth", "sixteenth"...
    public image Image; //An image of this type of note

    public Note() {
    }

    public Note(float x, float y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.image = Image(x, y, imageFileFor(type)); //the imageFor function would need to have access to a data structure (dictionary?) that maps strings -> images
    }

    @Override
    public void draw(Canvas canvas) {
        image.draw(canvas);
    }

    public void setX(float x) {
        this.x = x;
        this.image.setX(x);
    }

    public void setY(float y) {
        this.y = y;
        this.image.setY(y);
    }
}
