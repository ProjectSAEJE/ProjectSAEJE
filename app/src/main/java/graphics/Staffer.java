package graphics;

/**
 * Created by woodev01 on 11/3/15.
 */



public class Staffer {
    public void updateDisplay(float freq) {
        double f = freq;
        double x = Math.log(f/440);
        double y = Math.log(2);

        int note = (int)(12 * (x + 49)/y);

    }
}
