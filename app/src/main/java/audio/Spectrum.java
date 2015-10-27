package audio;

import android.util.Log;


public class Spectrum {

    double[] spectrum;
    double[] samples;
    int sampleRate;


    public Spectrum(byte[] data, int sampleRateInHz) {
        this.sampleRate = sampleRateInHz;
        buildSpectrum(data);
    }

    public double[] getSpectrum() {
        return spectrum.clone();
    }

    public double[] getSamples() {
        return samples.clone();
    }


    public void buildSpectrum(byte[] data) {
        // Create interlaced double array of complex numbers to hold sample data.
        samples = byteToDouble(data);

        // Apply window function to sample data.
        hanningWindow(samples);

        // Build FFT.
        FFT fft = new FFT(samples.length/2,-1);
        fft.transform(samples);

        // Build frequency spectrum from interlaced results data.
        spectrum = new double[samples.length/2];
        for (int i = 0; i < samples.length; i+=2)
            spectrum[i/2] = Math.sqrt(samples[i] * samples[i]+ samples[i+1] * samples[i+1]);
    }


    private double[] byteToDouble(byte[] data) {
        double[] sample = new double[data.length];


        for (int i = 0; i < data.length; i+=2)
            sample[i] = ((data[i] & 0xFF) | (data[i+1] << 8)) / 32768.0;

        return sample;
    }


    public static void hanningWindow(double[] samples) {
        for (int i = 0; i < samples.length; i+=2) {
            double hanning = 0.5 - 0.5 * Math.cos((2*Math.PI*(i/2)) / (samples.length/2) - 1);
            samples[i] *= hanning;
        }
    }


    public float getFrequency() {
        float frequency = 0;
        float peak = 0;

        double average = 0;
        double counter = 0;
        // search only the first half.
        for (int i = 1; i < spectrum.length/2; i++) {
            if (spectrum[i] > 1){
                if (spectrum[i] > spectrum[i-1] && spectrum[i] > spectrum[i+1]) {
                    average += spectrum[i];
                    counter++;
                }
            }
        }
        average /= counter;


        int max = 0;
        double largest = 1.0;
        // search only the first half.
        for (int i = 0; i < spectrum.length/2; i++) {
            if (spectrum[i] > largest) {
                largest = spectrum[i];
                max = i;
            }
        }


        if (max > 0) {
            peak = quadraticPeak(max);
        }


        if (largest > average*5) {
            // Frequency = Fs * i / N
            float freqFraction = peak / (float)spectrum.length; // The fraction of the sampling rate.
            frequency = (float) sampleRate * freqFraction;
        }
        return frequency;
    }


    public float getFrequency(float target) {
        float frequency = 0, peak = 0;

        // Calculate lower and upper range index values. Lower = 1/2 target, upper = target * 2.
        int lower = Math.round((target / 2) * spectrum.length / sampleRate) + 1;
        int upper = (int)((target * 2) * spectrum.length / sampleRate) - 1;

        // If the upper value is greater than the Nyquist limit set it to equal the limit.
        if (upper > (spectrum.length / 2)) {
            upper = (spectrum.length / 2);
        }

        // Determine the average magnitude of the spectrum peak values
        double average = 0;
        // search only the first half.
        for (int i = 1; i < spectrum.length/2; i++) {
            if (spectrum[i] > spectrum[i-1] && spectrum[i] > spectrum[i+1]) { // Is it a peak?
                average += spectrum[i];
            }
        }
        average /= spectrum.length/2;

        // Find the peak with highest magnitude within the given range.
        int max = -1;
        //largest *= 0.9; // A peak is not valid unless it is within a certain percentage.
        double largestInRange = 0;
        for (int i = lower; i < upper; i++) {
            if (spectrum[i] > spectrum[i-1] && spectrum[i] > spectrum[i+1]) { // Is it a peak?
                if (spectrum[i] > largestInRange) {
                    largestInRange = spectrum[i];
                    max = i;
                }
            }
        }

        // Calculate the quadratic interpolation peak index.
        if (max > 0) {
            peak = quadraticPeak(max);
        }


        if (largestInRange > average*2) {
            // Frequency = Fs * i / N
            float freqFraction = peak / (float)spectrum.length; // The fraction of the sampling rate.
            frequency = (float) sampleRate * freqFraction;
        }
        return frequency;
    }


    private float quadraticPeak(int index) {
        float alpha, beta, gamma, p, k;

        alpha = (float)spectrum[index-1];
        beta = (float)spectrum[index];
        gamma = (float)spectrum[index+1];

        p = 0.5f * ((alpha - gamma) / (alpha - 2*beta + gamma));

        k = (float)index + p;

        return k;
    }

    /**
     * Prints the spectrum values to the console, one value per line.
     */
    public void printSpectrum() {
        for (double d : spectrum) {
            System.out.println(d);
        }
    }
}