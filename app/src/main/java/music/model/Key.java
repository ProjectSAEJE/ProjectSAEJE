package music.model;

import java.util.ArrayList;
import java.util.Hashtable;

import music.ExtraTypes.NoteTuple;

public class Key {

    private String key;

    // An Array List of every key in the standard circle of fifths
    private static ArrayList<NoteTuple> A = new ArrayList<>();
    private static ArrayList<NoteTuple> B = new ArrayList<>();
    private static ArrayList<NoteTuple> C = new ArrayList<>();
    private static ArrayList<NoteTuple> D = new ArrayList<>();
    private static ArrayList<NoteTuple> E = new ArrayList<>();
    private static ArrayList<NoteTuple> F = new ArrayList<>();
    private static ArrayList<NoteTuple> G = new ArrayList<>();

    private static ArrayList<NoteTuple> AFlat = new ArrayList<>();
    private static ArrayList<NoteTuple> BFlat = new ArrayList<>();
    private static ArrayList<NoteTuple> CFlat = new ArrayList<>();
    private static ArrayList<NoteTuple> DFlat = new ArrayList<>();
    private static ArrayList<NoteTuple> EFlat = new ArrayList<>();
    private static ArrayList<NoteTuple> GFlat = new ArrayList<>();

    private static ArrayList<NoteTuple> CSharp = new ArrayList<>();
    private static ArrayList<NoteTuple> FSharp = new ArrayList<>();

    public static ArrayList<ArrayList> autoKeys = new ArrayList<>();
    public static ArrayList<ArrayList> possibleKeys = new ArrayList<>();




    public Key(int root){
        deriveKeys();
        ArrayList<NoteTuple> newKey = autoKeys.get(Math.abs(root%12));
        NoteTuple newNoteTuple = newKey.get(0);
        this.key = newNoteTuple.getNoteName();
    }

    public String getKey() {
        return this.key;
    }

    public void selectedKeys(){
        possibleKeys.add(A);
        possibleKeys.add(BFlat);
        possibleKeys.add(B);
        possibleKeys.add(CFlat);
        possibleKeys.add(C);
        possibleKeys.add(CSharp);
        possibleKeys.add(DFlat);
        possibleKeys.add(D);
        possibleKeys.add(EFlat);
        possibleKeys.add(E);
        possibleKeys.add(F);
        possibleKeys.add(FSharp);
        possibleKeys.add(GFlat);
        possibleKeys.add(G);
        possibleKeys.add(AFlat);
    }

    private void deriveKeys() {
        //Key can be accessed by their Root note piano Number
        autoKeys.add(A);      //0
        autoKeys.add(BFlat);  //1
        autoKeys.add(B);      //2
        autoKeys.add(C);      //3
        autoKeys.add(DFlat);  //4
        autoKeys.add(D);      //5
        autoKeys.add(EFlat);  //6
        autoKeys.add(E);      //7
        autoKeys.add(F);      //8
        autoKeys.add(FSharp); //9
        autoKeys.add(G);      //10
        autoKeys.add(AFlat);  //11


        AFlat.add(new NoteTuple(11, "Ab"));
        AFlat.add(new NoteTuple(1, "Bb"));
        AFlat.add(new NoteTuple(3, "C"));
        AFlat.add(new NoteTuple(4, "Db"));
        AFlat.add(new NoteTuple(6, "Eb"));
        AFlat.add(new NoteTuple(8, "F"));
        AFlat.add(new NoteTuple(10, "G"));

        A.add(new NoteTuple(0, "A"));
        A.add(new NoteTuple(2, "B"));
        A.add(new NoteTuple(4, "C#"));
        A.add(new NoteTuple(5, "D"));
        A.add(new NoteTuple(7, "E"));
        A.add(new NoteTuple(9, "F#"));
        A.add(new NoteTuple(11, "G#"));

        BFlat.add(new NoteTuple(1, "Bb"));
        BFlat.add(new NoteTuple(3, "C"));
        BFlat.add(new NoteTuple(5, "D"));
        BFlat.add(new NoteTuple(6, "Eb"));
        BFlat.add(new NoteTuple(8, "F"));
        BFlat.add(new NoteTuple(10, "G"));
        BFlat.add(new NoteTuple(0, "A"));

        B.add(new NoteTuple(2, "B"));
        B.add(new NoteTuple(4, "C#"));
        B.add(new NoteTuple(6, "D#"));
        B.add(new NoteTuple(7, "E"));
        B.add(new NoteTuple(9, "F#"));
        B.add(new NoteTuple(11, "G#"));
        B.add(new NoteTuple(1, "A#"));

        CFlat.add(new NoteTuple(2, "Cb"));
        CFlat.add(new NoteTuple(4, "Db"));
        CFlat.add(new NoteTuple(6, "Eb"));
        CFlat.add(new NoteTuple(7, "Fb"));
        CFlat.add(new NoteTuple(9, "Gb"));
        CFlat.add(new NoteTuple(11, "Ab"));
        CFlat.add(new NoteTuple(1, "Bb"));

        C.add(new NoteTuple(3, "C"));
        C.add(new NoteTuple(5, "D"));
        C.add(new NoteTuple(7, "E"));
        C.add(new NoteTuple(8, "F"));
        C.add(new NoteTuple(10, "G"));
        C.add(new NoteTuple(0, "A"));
        C.add(new NoteTuple(2, "B"));

        CSharp.add(new NoteTuple(4, "C#"));
        CSharp.add(new NoteTuple(6, "D#"));
        CSharp.add(new NoteTuple(8, "E#"));
        CSharp.add(new NoteTuple(9, "F#"));
        CSharp.add(new NoteTuple(11, "G#"));
        CSharp.add(new NoteTuple(1, "A#"));
        CSharp.add(new NoteTuple(3, "B#"));

        DFlat.add(new NoteTuple(4, "Db"));
        DFlat.add(new NoteTuple(6, "Eb"));
        DFlat.add(new NoteTuple(8, "F"));
        DFlat.add(new NoteTuple(9, "Gb"));
        DFlat.add(new NoteTuple(11, "Ab"));
        DFlat.add(new NoteTuple(1, "Bb"));
        DFlat.add(new NoteTuple(3, "C"));

        D.add(new NoteTuple(5, "D"));
        D.add(new NoteTuple(7, "E"));
        D.add(new NoteTuple(9, "F#"));
        D.add(new NoteTuple(10, "G"));
        D.add(new NoteTuple(0, "A"));
        D.add(new NoteTuple(2, "B"));
        D.add(new NoteTuple(4, "C#"));

        EFlat.add(new NoteTuple(6, "Eb"));
        EFlat.add(new NoteTuple(8, "F"));
        EFlat.add(new NoteTuple(10, "G"));
        EFlat.add(new NoteTuple(11, "Ab"));
        EFlat.add(new NoteTuple(1, "Bb"));
        EFlat.add(new NoteTuple(3, "C"));
        EFlat.add(new NoteTuple(5, "D"));

        E.add(new NoteTuple(7, "E"));
        E.add(new NoteTuple(9, "F#"));
        E.add(new NoteTuple(11, "G#"));
        E.add(new NoteTuple(0, "A"));
        E.add(new NoteTuple(2, "B"));
        E.add(new NoteTuple(4, "C#"));
        E.add(new NoteTuple(6, "D#"));

        F.add(new NoteTuple(8, "F"));
        F.add(new NoteTuple(10, "G"));
        F.add(new NoteTuple(0, "A"));
        F.add(new NoteTuple(1, "Bb"));
        F.add(new NoteTuple(3, "C"));
        F.add(new NoteTuple(5, "D"));
        F.add(new NoteTuple(7, "E"));

        FSharp.add(new NoteTuple(9, "F#"));
        FSharp.add(new NoteTuple(11, "G#"));
        FSharp.add(new NoteTuple(1, "A#"));
        FSharp.add(new NoteTuple(2, "B"));
        FSharp.add(new NoteTuple(4, "C#"));
        FSharp.add(new NoteTuple(6, "D#"));
        FSharp.add(new NoteTuple(8, "E#"));

        GFlat.add(new NoteTuple(9, "Gb"));
        GFlat.add(new NoteTuple(11, "Ab"));
        GFlat.add(new NoteTuple(1, "Bb"));
        GFlat.add(new NoteTuple(2, "Cb"));
        GFlat.add(new NoteTuple(4, "Db"));
        GFlat.add(new NoteTuple(6, "Eb"));
        GFlat.add(new NoteTuple(8, "F"));

        G.add(new NoteTuple(10, "G"));
        G.add(new NoteTuple(0, "A"));
        G.add(new NoteTuple(2, "B"));
        G.add(new NoteTuple(3, "C"));
        G.add(new NoteTuple(5, "D"));
        G.add(new NoteTuple(7, "E"));
        G.add(new NoteTuple(9, "F#"));
    }
}
