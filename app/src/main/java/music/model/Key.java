package music.model;

/**
 * Created by woodev01 on 2/28/16.
 */

import java.util.ArrayList;
import java.util.Hashtable;

public class Key {

    public static Hashtable<String, Integer> key;

    // An Array List of every key in the standard circle of fifths
    private static Hashtable<String,Integer> A = new Hashtable<>();
    private static Hashtable<String,Integer> B = new Hashtable<>();
    private static Hashtable<String,Integer> C = new Hashtable<>();
    private static Hashtable<String,Integer> D = new Hashtable<>();
    private static Hashtable<String,Integer> E = new Hashtable<>();
    private static Hashtable<String,Integer> F = new Hashtable<>();
    private static Hashtable<String,Integer> G = new Hashtable<>();

    private static Hashtable<String,Integer> AFlat = new Hashtable<>();
    private static Hashtable<String,Integer> BFlat = new Hashtable<>();
    private static Hashtable<String,Integer> CFlat = new Hashtable<>();
    private static Hashtable<String,Integer> DFlat = new Hashtable<>();
    private static Hashtable<String,Integer> EFlat = new Hashtable<>();
    private static Hashtable<String,Integer> GFlat = new Hashtable<>();

    private static Hashtable<String,Integer> CSharp = new Hashtable<>();
    private static Hashtable<String,Integer> FSharp = new Hashtable<>();

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


        AFlat.put("Ab", 11);
        AFlat.put("Bb", 1);
        AFlat.put("C", 3);
        AFlat.put("Db", 4);
        AFlat.put("Eb", 6);
        AFlat.put("F", 8);
        AFlat.put("G", 10);

        A.put("A", 0);
        A.put("B", 2);
        A.put("C#", 4);
        A.put("D", 5);
        A.put("E", 7);
        A.put("F#", 9);
        A.put("G#", 11);

        BFlat.put("Bb", 1);
        BFlat.put("C", 3);
        BFlat.put("D", 5);
        BFlat.put("Eb", 6);
        BFlat.put("F", 8);
        BFlat.put("G", 10);
        BFlat.put("A", 0);

        B.put("B", 2);
        B.put("C#", 4);
        B.put("D#", 6);
        B.put("E", 7);
        B.put("F#", 9);
        B.put("G#", 11);
        B.put("A#", 1);

        CFlat.put("Cb", 2);
        CFlat.put("Db", 4);
        CFlat.put("Eb", 6);
        CFlat.put("Fb", 7);
        CFlat.put("Gb", 9);
        CFlat.put("Ab", 11);
        CFlat.put("Bb", 1);

        C.put("C", 3);
        C.put("D", 5);
        C.put("E", 7);
        C.put("F", 8);
        C.put("G", 10);
        C.put("A", 0);
        C.put("B", 2);

        CSharp.put("C#", 4);
        CSharp.put("D#", 6);
        CSharp.put("E#", 8);
        CSharp.put("F#", 9);
        CSharp.put("G#", 11);
        CSharp.put("A#", 1);
        CSharp.put("B#", 3);



    }



}
