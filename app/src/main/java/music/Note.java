package music;

public class Note {


    public float tonalValue; //represents a piano note value 0-87
    public int rhythmicValue; //represents the rhythmic value 0-4


    public Note(float tonalValue,int rhythmicValue) {
        this.tonalValue = tonalValue;
        this.rhythmicValue = rhythmicValue;
    }
}

