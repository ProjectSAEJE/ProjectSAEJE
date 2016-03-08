package music.model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Key {

    public Hashtable<Integer, String> key;

    // An Array List of every key in the standard circle of fifths
    private static Hashtable<Integer, String> A = new Hashtable<>();
    private static Hashtable<Integer, String> B = new Hashtable<>();
    private static Hashtable<Integer, String> C = new Hashtable<>();
    private static Hashtable<Integer, String> D = new Hashtable<>();
    private static Hashtable<Integer, String> E = new Hashtable<>();
    private static Hashtable<Integer, String> F = new Hashtable<>();
    private static Hashtable<Integer, String> G = new Hashtable<>();

    private static Hashtable<Integer, String> AFlat = new Hashtable<>();
    private static Hashtable<Integer, String> BFlat = new Hashtable<>();
    private static Hashtable<Integer, String> CFlat = new Hashtable<>();
    private static Hashtable<Integer, String> DFlat = new Hashtable<>();
    private static Hashtable<Integer, String> EFlat = new Hashtable<>();
    private static Hashtable<Integer, String> GFlat = new Hashtable<>();

    private static Hashtable<Integer, String> CSharp = new Hashtable<>();
    private static Hashtable<Integer, String> FSharp = new Hashtable<>();

    public static ArrayList<Hashtable> autoKeys = new ArrayList<>();
    public static ArrayList<Hashtable> possibleKeys = new ArrayList<>();




    public Key(int root){
        deriveKeys();
        this.key = autoKeys.get(root);

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


        AFlat.put(11, "Ab");
        AFlat.put(1, "Bb");
        AFlat.put(3, "C");
        AFlat.put(4, "Db");
        AFlat.put(6, "Eb");
        AFlat.put(8, "F");
        AFlat.put(10, "G");

        A.put(0, "A");
        A.put(2, "B");
        A.put(4, "C#");
        A.put(5, "D");
        A.put(7, "E");
        A.put(9, "F#");
        A.put(11, "G#");

        BFlat.put(1, "Bb");
        BFlat.put(3, "C");
        BFlat.put(5, "D");
        BFlat.put(6, "Eb");
        BFlat.put(8, "F");
        BFlat.put(10, "G");
        BFlat.put(0, "A");

        B.put(2, "B");
        B.put(4, "C#");
        B.put(6, "D#");
        B.put(7, "E");
        B.put(9, "F#");
        B.put(11, "G#");
        B.put(1, "A#");

        CFlat.put(2, "Cb");
        CFlat.put(4, "Db");
        CFlat.put(6, "Eb");
        CFlat.put(7, "Fb");
        CFlat.put(9, "Gb");
        CFlat.put(11, "Ab");
        CFlat.put(1, "Bb");

        C.put(3, "C");
        C.put(5, "D");
        C.put(7, "E");
        C.put(8, "F");
        C.put(10, "G");
        C.put(0, "A");
        C.put(2, "B");

        CSharp.put(4, "C#");
        CSharp.put(6, "D#");
        CSharp.put(8, "E#");
        CSharp.put(9, "F#");
        CSharp.put(11, "G#");
        CSharp.put(1, "A#");
        CSharp.put(3, "B#");

        DFlat.put(4, "Db");
        DFlat.put(6, "Eb");
        DFlat.put(8, "F");
        DFlat.put(9, "Gb");
        DFlat.put(11, "Ab");
        DFlat.put(1, "Bb");
        DFlat.put(3, "C");

        D.put(5, "D");
        D.put(7, "E");
        D.put(9, "F#");
        D.put(10, "G");
        D.put(0, "A");
        D.put(2, "B");
        D.put(4, "C#");

        EFlat.put(6, "Eb");
        EFlat.put(8, "F");
        EFlat.put(10, "G");
        EFlat.put(11, "Ab");
        EFlat.put(1, "Bb");
        EFlat.put(3, "C");
        EFlat.put(5, "D");

        E.put(7, "E");
        E.put(9, "F#");
        E.put(11, "G#");
        E.put(0, "A");
        E.put(2, "B");
        E.put(4, "C#");
        E.put(6, "D#");

        F.put(8, "F");
        F.put(10, "G");
        F.put(0, "A");
        F.put(1, "Bb");
        F.put(3, "C");
        F.put(5, "D");
        F.put(7, "E");

        FSharp.put(9, "F#");
        FSharp.put(11, "G#");
        FSharp.put(1, "A#");
        FSharp.put(2, "B");
        FSharp.put(4, "C#");
        FSharp.put(6, "D#");
        FSharp.put(8, "E#");

        GFlat.put(9, "Gb");
        GFlat.put(11, "Ab");
        GFlat.put(1, "Bb");
        GFlat.put(2, "Cb");
        GFlat.put(4, "Db");
        GFlat.put(6, "Eb");
        GFlat.put(8, "F");

        G.put(10, "G");
        G.put(0, "A");
        G.put(2, "B");
        G.put(3, "C");
        G.put(5, "D");
        G.put(7, "E");
        G.put(9, "F#");


    }



}
