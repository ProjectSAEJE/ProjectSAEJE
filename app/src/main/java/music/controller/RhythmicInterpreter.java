package music.controller;

/**
 * Created by austinnash on 3/10/16.
 */
public class RhythmicInterpreter {
    private int previously_updated_tone;
    private int rp_segments_for_current_tone;
    private int rhythmic_precision;

    public RhythmicInterpreter() {}

    public RhythmicInterpreter(int rhythmic_precision) {
        this.rhythmic_precision = rhythmic_precision;
    }

    public void update(int new_tone) {
        //If the tone is the same tone as the previous tone, the note is longer and we simply increment rp_segments
        if (new_tone == previously_updated_tone) {
            rp_segments_for_current_tone++;
        }
        //If the tone is not the same, we change the previously updated tone to the new tone and set rp_segments to 1
        else if (new_tone != previously_updated_tone) {
            previously_updated_tone = new_tone;
            rp_segments_for_current_tone = 1;
        }
    }

    //returns 16 for 16th note, 5.33 for dotted-eighth note,  4 for quarter note, etc.

    public float getRhythmicValueOfEndedNote() {
        return ((float) rhythmic_precision) / ((float) rp_segments_for_current_tone);
    }

}
