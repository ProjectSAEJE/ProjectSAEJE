package graphics;

import android.graphics.PorterDuff;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * Created by woodev01 on 2/24/15.
 */

public class DrawingView extends View {

    //drawing and canvas paint
    private Paint canvasPaint;

    //canvas
    private Canvas drawCanvas;

    //canvas bitmap
    private Bitmap canvasBitmap;


    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startNew() {
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
    }
}