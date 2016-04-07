package music.ExtraTypes;


public class NoteTuple {

    private int scaleDegree;
    private String noteName;

    public NoteTuple() { }

    public NoteTuple(int scaleDegree, String noteName) {
        this.scaleDegree = scaleDegree;
        this.noteName = noteName;
    }

    public int getScaleDegree() {
        return this.scaleDegree;
    }

    public String getNoteName() {
        return this.noteName;
    }



}
