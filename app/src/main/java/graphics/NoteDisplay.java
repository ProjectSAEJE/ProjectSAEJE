package graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

import com.example.woodev01.projectsaeje.R;
import projectsaeje.MainActivity;

public class NoteDisplay extends Drawable {

    public Bitmap image;

    public NoteDisplay(int imageId, MainActivity myActivity) {

        Bitmap b = BitmapFactory.decodeResource(myActivity.getResources(), imageId);

        //Creates a ScaledBitmap to make quarter note a certain size
        this.image = Bitmap.createScaledBitmap(b, 300, 300, false);
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
