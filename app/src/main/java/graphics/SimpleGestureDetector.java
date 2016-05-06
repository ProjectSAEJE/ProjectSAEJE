package graphics;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import projectsaeje.MainActivity;

/**
 * Created by Evan on 5/5/2016.
 */

/*class SimpleGestureDetector implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    String TAG = "Checking the Fling";

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() < e2.getX()) {
            Log.d(TAG, "Left to Right swipe performed");
            MainActivity.staff.moveBackCurrentMeasures();
        }

        if (e1.getX() > e2.getX()) {
            Log.d(TAG, "Right to Left swipe performed");
            MainActivity.staff.moveUpCurrentMeasures();
        }

        if (e1.getY() < e2.getY()) {
            Log.d(TAG, "Up to Down swipe performed");
            e1.getX();
        }

        if (e1.getY() > e2.getY()) {
            Log.d(TAG, "Down to Up swipe performed");
        }
        return true;
    }
} */